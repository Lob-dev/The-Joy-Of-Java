package com.cache.demo.sample.controller.form;

import com.cache.demo.sample.domain.persistence.SampleEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SampleResponse {

	@Getter
	@AllArgsConstructor
	public static class FindOne implements Serializable {
		private final String title;
		private final String description;

		public static FindOne from(SampleEntity sample) {
			return new FindOne(sample.getTitle(), sample.getDescription());
		}
	}

	@Getter
	@AllArgsConstructor
	public static class Info {
		private final Long id;
		private final String title;

		public static Info from(SampleEntity sample) {
			return new Info(sample.getId(), sample.getTitle());
		}
	}
}
