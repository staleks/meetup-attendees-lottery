package rs.novacode.meetup.attendees.lottery.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

import java.util.Set;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class MeetupAttendeeResponse {

    private String type;

    private String ref;

    private Set<MeetupAttendee> value;

    private MeetupRequestMeta meta;

}
