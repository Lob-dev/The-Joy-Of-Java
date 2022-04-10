package com.auth.demo.sample.controller.form;

import com.auth.demo.sample.domain.model.Sample;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SampleRequest {

	@Getter
	@NoArgsConstructor
	public static class Find {
		private Integer pageNumber;
		private Integer pageSize;
	}

	@Getter
	@NoArgsConstructor
	public static class Add {
		private String title;
		private String description;

		public Sample toModelWith(String creator) {
			return Sample.builder()
					.title(title)
					.description(description)
					.creator(creator)
					.build();
		}
	}

	@Getter
	@NoArgsConstructor
	public static class Update {
		private String title;
		private String description;

		public Sample toModelWith(Long sampleId) {
			return Sample.builder()
					.id(sampleId)
					.title(title)
					.description(description)
					.build();
		}
	}
}