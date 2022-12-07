package rs.novacode.meetup.attendees.lottery.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import rs.novacode.meetup.attendees.lottery.config.AttendeesLotteryConfig;

@Import(AttendeesLotteryConfig.class)
@SpringBootApplication
public class AttendeesLotteryApplication {
	public static void main(String[] args) {
		SpringApplication.run(AttendeesLotteryApplication.class, args);
	}

}
