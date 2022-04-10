package com.blog.prometheus.message.publisher.global.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

@Configuration
@EnableScheduling
public class ScheduledConfiguration implements SchedulingConfigurer {

	// Scheduled 과 같은 스케줄링 작업에 대한 구성 정보를 설정하는 Registrar
	@Override
	public void configureTasks(ScheduledTaskRegistrar registrar) {
		final ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
		taskScheduler.setPoolSize(3);
		taskScheduler.setThreadNamePrefix("event-");
		taskScheduler.initialize();
		registrar.setTaskScheduler(taskScheduler);
	}
}
