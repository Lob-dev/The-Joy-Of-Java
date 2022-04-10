package com.cache.demo.sample.controller.form;

import com.cache.demo.sample.domain.service.model.Sample;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
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
	@AllArgsConstructor
	public static class Add {
		private String title;
		private String description;

		public Sample toModel() {
			return new Sample(null, title, description);
		}
	}

	@Getter
	@NoArgsConstructor
	public static class Update {
		private String title;
		private String description;

		public Sample toModel(Long sampleId) {
			return new Sample(sampleId, title, description);
		}
	}
}
