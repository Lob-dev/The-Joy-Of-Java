package com.blog.prometheus.message.subscriber.global.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PayEvent {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private long userId;
	private String message;
	private String eventTime;

	public PayEvent(long userId, String message, String eventTime) {
		this.userId = userId;
		this.message = message;
		this.eventTime = eventTime;
	}

	@Override
	public String toString() {
		return "PayEvent{" +
				"userId=" + userId +
				", message='" + message + '\'' +
				", eventTime='" + eventTime + '\'' +
				'}';
	}
}