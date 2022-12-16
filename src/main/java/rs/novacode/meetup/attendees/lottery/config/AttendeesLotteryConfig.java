package rs.novacode.meetup.attendees.lottery.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;
import rs.novacode.meetup.attendees.lottery.core.AttendeesLotteryService;
import rs.novacode.meetup.attendees.lottery.core.AttendeesLotteryUseCase;
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
    public AttendeesLotteryUseCase attendeesLotteryUseCase(final RestTemplate restTemplate) {
        return new AttendeesLotteryService(restTemplate);
    }

    @Bean
    public MeetupAttendeeLotteryController meetupAttendeeLotteryController(final AttendeesLotteryUseCase attendeesLotteryUseCase) {
        return new MeetupAttendeeLotteryController(attendeesLotteryUseCase);
    }

}
