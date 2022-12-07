package rs.novacode.meetup.attendees.lottery.core;

import rs.novacode.meetup.attendees.lottery.model.MeetupMember;

import java.util.List;

public interface AttendeesLotteryUseCase {

    MeetupMember pickOneByLottery();

    List<MeetupMember> fetchAll();

}
