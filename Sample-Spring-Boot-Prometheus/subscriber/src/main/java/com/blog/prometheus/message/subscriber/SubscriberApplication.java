package com.blog.prometheus.message.subscriber;

import com.blog.prometheus.message.subscriber.global.domain.PayEvent;
import com.blog.prometheus.message.subscriber.global.domain.PayEventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@RequiredArgsConstructor
@SpringBootApplication
public class SubscriberApplication {

	private final PayEventRepository payEventRepository;

	public static void main(String[] args) {
		SpringApplication.run(SubscriberApplication.class, args);
	}

	@RabbitListener(queues = "event-queue")
	public void subscribeEvent(PayEvent payEvent) {
		log.info("{}", payEvent);
		payEventRepository.save(payEvent);
	}
}
