package rs.novacode.meetup.attendees.lottery.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Photo {

    private Long id;

    @JsonProperty("highres_link")
    private String highResLink;

    @JsonProperty("photo_link")
    private String photoLink;

    @JsonProperty("thumb_link")
    private String thumbLink;

}
