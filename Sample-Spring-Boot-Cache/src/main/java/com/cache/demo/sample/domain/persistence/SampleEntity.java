package com.cache.demo.sample.domain.persistence;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Entity
@Table(name = "Sample")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SampleEntity implements Serializable {

	@Id
	@GeneratedValue
	@Column(name = "sample_id")
	private Long id;

	@Column(name = "title", nullable = false)
	private String title;

	@Column(name = "decription", nullable = false)
	private String description;

	public void updateStates(String title, String description) {
		this.title = title;
		this.description = description;
	}

	public SampleEntity(String title, String description) {
		this.title = title;
		this.description = description;
	}

	public SampleEntity(Long id, String title, String description) {
		this.id = id;
		this.title = title;
		this.description = description;
	}
}
