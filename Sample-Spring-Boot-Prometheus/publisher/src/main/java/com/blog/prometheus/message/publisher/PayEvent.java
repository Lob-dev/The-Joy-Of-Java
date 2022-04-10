package com.blog.prometheus.message.publisher;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PayEvent {
	private long userId;
	private String message;
	private String eventTime;

	protected PayEvent(long userId, String message, String eventTime) {
		this.userId = userId;
		this.message = message;
		this.eventTime = eventTime;
	}

	public static PayEvent of(long userId, String message, String eventTime) {
		return new PayEvent(userId, message, eventTime);
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
