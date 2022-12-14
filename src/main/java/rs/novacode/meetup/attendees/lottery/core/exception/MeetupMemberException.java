package rs.novacode.meetup.attendees.lottery.core.exception;

public class MeetupMemberException extends RuntimeException {
    private static final long serialVersionUID = -3400676467449892764L;

    public static final String CAN_NOT_ACCESS_MEETUP_API = "Can not access Meetup API.";

    public MeetupMemberException(final String message) {
        super(message);
    }

}
