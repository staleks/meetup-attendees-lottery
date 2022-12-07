package rs.novacode.meetup.attendees.lottery.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;
import rs.novacode.meetup.attendees.lottery.core.AttendieesLotteryService;
import rs.novacode.meetup.attendees.lottery.core.AttendieesLotteryUseCase;
import rs.novacode.meetup.attendees.lottery.endpoint.MeetupAttendeeLotteryController;

@Import({
    RestClientConfig.class
})
@Configuration
public class AttendeesLotteryConfig {

    @Bean
    public ObjectMapper objectMapper() {
        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return objectMapper;
    }

    @Bean
    public AttendieesLotteryUseCase attendieesLotteryUseCase(final RestTemplate restTemplate) {
        return new AttendieesLotteryService(restTemplate);
    }

    @Bean
    public MeetupAttendeeLotteryController meetupAttendeeLotteryController(final AttendieesLotteryUseCase attendieesLotteryUseCase) {
        return new MeetupAttendeeLotteryController(attendieesLotteryUseCase);
    }

}
