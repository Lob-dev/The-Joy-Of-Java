package com.auth.demo.sample.domain.model;

import com.auth.demo.sample.domain.persistence.SampleEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Sample {

	private Long id;
	private String title;
	private String description;
	private String creator;

	@Builder
	public Sample(Long id, String title, String description, String creator) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.creator = creator;
	}

	public SampleEntity toEntity() {
		return new SampleEntity(id, title, description, creator);
	}
}