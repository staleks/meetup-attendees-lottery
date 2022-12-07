package rs.novacode.meetup.attendees.lottery.core;

import rs.novacode.meetup.attendees.lottery.model.MeetupMember;

import java.util.List;

public interface AttendieesLotteryUseCase {

    MeetupMember pickOneByLottery();

    List<MeetupMember> fetchAll();

}
