package rs.novacode.meetup.attendees.lottery.core;

import rs.novacode.meetup.attendees.lottery.model.MeetupMember;

import java.util.List;

public interface AttendeesLotteryUseCase {

    /**
     * Use this method in order to randomly pick one Meetup member
     * as lottery winner
     * @return
     */
    MeetupMember pickOneByLottery();

    /**
     * Use this method to pre-populate in-memory Map of all Meetup members that
     * responded as `coming` to Meetup event.
     * @return
     */
    List<MeetupMember> fetchAll();

}
