package rs.novacode.meetup.attendees.lottery.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class MeetupRequestMeta {

    private String endpoint;

    private int statusCode;

}
