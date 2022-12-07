package rs.novacode.meetup.attendees.lottery.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class MeetupAttendee {

    private Long updated;

    @JsonProperty("member")
    private MeetupMember meetupMember;

    private int guests;

    private String response;

}
