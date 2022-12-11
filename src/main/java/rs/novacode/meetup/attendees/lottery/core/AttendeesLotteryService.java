package rs.novacode.meetup.attendees.lottery.core;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.math3.random.RandomDataGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import rs.novacode.meetup.attendees.lottery.core.exception.MeetupMemberException;
import rs.novacode.meetup.attendees.lottery.model.MeetupAttendeeResponses;
import rs.novacode.meetup.attendees.lottery.model.MeetupMember;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class AttendeesLotteryService implements AttendeesLotteryUseCase {

    private static final Integer MEETUP_ATTENDEES_ZERO_BASED_RESPONSE_INDEX = 0;

    private static final String ORGANIZER_ROLE = "organizer";
    private static final String CO_ORGANIZER_ROLE = "coorganizer";

    @Value("${meetup.baseUrl}")
    private String meetupUrl;

    @Value("${meetup.presenterId}")
    private Long presenterId;

    private Map<Long, MeetupMember> meetupAttendees;

    private final RestTemplate restTemplate;

    public AttendeesLotteryService(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.meetupAttendees = new HashMap<>();
    }

    @Override
    public MeetupMember pickOneByLottery() {
        List<MeetupMember> attendeesForLottery = meetupAttendees.values()
            .stream()
            //~ filter out organizers
            .filter(m -> !m.isOrganizer())
            .filter(m -> !ORGANIZER_ROLE.equals(m.getRole()))
            //~ filter out co-organizerss
            .filter(m -> !CO_ORGANIZER_ROLE.equals(m.getRole()))
            //~ filter out myself
            .filter(m -> !presenterId.equals(m.getId()))
            .collect(Collectors.toList());

        log.info("Size of filtered list: {}", attendeesForLottery);

        RandomDataGenerator randomDataGenerator = new RandomDataGenerator();
        int randomNumber = randomDataGenerator.nextInt(0, attendeesForLottery.size()-1);
        log.info("Random generated number is: {}", randomNumber);

        MeetupMember lotteryWinner = attendeesForLottery.get(randomNumber);
        log.info("And the winner is: {}", lotteryWinner);

        return lotteryWinner;
    }

    @Override
    public List<MeetupMember> fetchAll() {
        try {
            ResponseEntity<MeetupAttendeeResponses> response = restTemplate.getForEntity(meetupUrl, MeetupAttendeeResponses.class);

            List<MeetupMember> meetupMembersList = response.getBody()
                .getResponses()
                .get(MEETUP_ATTENDEES_ZERO_BASED_RESPONSE_INDEX).getValue()
                .stream()
                .map(e -> e.getMeetupMember())
                .collect(Collectors.toList());
            log.info("Size of the list: {}", meetupMembersList.size());
            for (MeetupMember meetupAttendee: meetupMembersList) {
                meetupAttendees.put(meetupAttendee.getId(), meetupAttendee);
            }
            return meetupMembersList;
        } catch (final RestClientException rcEx) {
            throw new MeetupMemberException(MeetupMemberException.CAN_NOT_ACCESS_MEETUP_API);
        }
    }

}
