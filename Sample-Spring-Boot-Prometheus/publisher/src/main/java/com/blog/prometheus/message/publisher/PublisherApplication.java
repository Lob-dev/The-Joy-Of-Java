package com.blog.prometheus.message.publisher;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.util.Random;

@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
public class PublisherApplication {

	private final RabbitTemplate rabbitTemplate;
	private static final Random generator;

	static {
		generator = new Random();
	}

	public static void main(String[] args) {
		SpringApplication.run(PublisherApplication.class, args);
	}

	@Scheduled(fixedRate = 5000) // fixedRate 는 작업을 실행한 시점부터 다음 작업 수행 시간을 측정한다.
	public void publishEvent() {
		final int userId = generator.nextInt(10);
		final LocalDateTime eventTime = LocalDateTime.now();

		final PayEvent payEvent = PayEvent.of(userId, "Transaction finished", eventTime.toString());
		log.info("{}", payEvent);
		rabbitTemplate.convertAndSend("event", "event-pay", payEvent);
	}
}
