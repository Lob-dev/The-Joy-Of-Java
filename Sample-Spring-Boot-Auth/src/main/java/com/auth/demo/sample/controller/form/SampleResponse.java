package com.auth.demo.sample.controller.form;

import com.auth.demo.sample.domain.persistence.SampleEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SampleResponse {

	@Getter
	@AllArgsConstructor
	public static class FindOne {
		private final String title;
		private final String description;
		private final String creator;

		public static FindOne from(SampleEntity sample) {
			return new FindOne(sample.getTitle(), sample.getDescription(), sample.getCreator());
		}
	}

	@Getter
	@AllArgsConstructor
	public static class Info {
		private final Long id;
		private final String title;
		private final String creator;

		public static Info from(SampleEntity sample) {
			return new Info(sample.getId(), sample.getTitle(), sample.getCreator());
		}
	}
}