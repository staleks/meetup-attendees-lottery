package rs.novacode.meetup.attendees.lottery.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

import java.util.List;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class MeetupAttendeeResponses {

    private List<MeetupAttendeeResponse> responses;

}
