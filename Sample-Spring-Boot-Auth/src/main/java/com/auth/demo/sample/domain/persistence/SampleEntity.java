package com.auth.demo.sample.domain.persistence;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Entity
@Table(name = "Sample")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SampleEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "title", nullable = false)
	private String title;

	@Column(name = "decription", nullable = false)
	private String description;

	@Column(name = "creator", nullable = false)
	private String creator;

	public void updateStates(String title, String description) {
		this.title = title;
		this.description = description;
	}

	public boolean isNotOwner(String username) {
		return !this.creator.equals(username);
	}

	public SampleEntity(Long id, String title, String description, String creator) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.creator = creator;
	}
}