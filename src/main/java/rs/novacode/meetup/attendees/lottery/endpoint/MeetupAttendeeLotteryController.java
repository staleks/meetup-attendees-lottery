package rs.novacode.meetup.attendees.lottery.endpoint;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.novacode.meetup.attendees.lottery.core.AttendeesLotteryUseCase;
import rs.novacode.meetup.attendees.lottery.model.MeetupMember;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@Slf4j
@RestController
@AllArgsConstructor
public class MeetupAttendeeLotteryController {

    private final String FETCH_ALL_MEETUP_ATTENDEES_ENDPOINT = "/attendees-all";
    private final String PICK_ONE_ATTENDEE_BY_LOTTERY = "/pick-one";
    private final AttendeesLotteryUseCase attendeesLotteryUseCase;

    /**
     * REST API method to fetch all Meetup Attendees in certain group.
     * It is the list where Meetup Members opted for RSVP in a group.
     * Fetched list populates in-memory data structure later used to Randomly pick one Meetup Member.
     *
     * @return {@link List} list of Meetup Members, attendees of certain Meetup Group
     */
    @GetMapping(value = FETCH_ALL_MEETUP_ATTENDEES_ENDPOINT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MeetupMember>> findAll() {
        return ok(attendeesLotteryUseCase.fetchAll());
    }

    /**
     * REST API method to pick one Randomly selected Meetup Member of certain group.
     *
     * @return {@link MeetupMember} lottery winner
     */
    @GetMapping(value = PICK_ONE_ATTENDEE_BY_LOTTERY, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MeetupMember> pickOne() {
        return ok(attendeesLotteryUseCase.pickOneByLottery());
    }

}
