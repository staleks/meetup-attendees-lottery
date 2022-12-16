package rs.novacode.meetup.attendees.lottery.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

import java.util.Map;

@Getter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MeetupMember {

    private Long id;

    private String name;

    @JsonProperty("web_actions")
    private Map<String, String> webActions;

    @JsonProperty("is_organizer")
    private boolean isOrganizer;

    @JsonProperty("is_member_plus_subscriber")
    private boolean isMemberPlusSubscriber;

    private Photo photo;

    private String role;

}
