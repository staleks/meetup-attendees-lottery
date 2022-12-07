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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class AttendieesLotteryService implements AttendieesLotteryUseCase {

    @Value("${meetup.baseUrl}")
    private String meetupUrl;

    private Map<Long, MeetupMember> meetupAttendees;

    private final RestTemplate restTemplate;

    public AttendieesLotteryService(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.meetupAttendees = new HashMap<>();
    }

    @Override
    public MeetupMember pickOneByLottery() {
        List<MeetupMember> attendeesForLottery = new ArrayList<>();
        for (Long keyValue: meetupAttendees.keySet()) {
            MeetupMember meetupMemberItem = meetupAttendees.get(keyValue);
            if (!meetupMemberItem.isOrganizer()) {
                attendeesForLottery.add(meetupMemberItem);
            }
        }

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

            List<MeetupMember> meetupMembersList = response.getBody().getResponses().get(0).getValue().stream()
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
