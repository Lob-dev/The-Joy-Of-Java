package com.cache.demo.sample.domain.service.model;

import com.cache.demo.sample.domain.persistence.SampleEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Sample {

	private Long id;
	private String title;
	private String description;

	public Sample(Long id, String title, String description) {
		this.id = id;
		this.title = title;
		this.description = description;
	}

	public SampleEntity toEntity() {
		return new SampleEntity(id, title, description);
	}
}
