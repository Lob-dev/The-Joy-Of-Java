package com.auth.demo.sample.domain.service;

import com.auth.demo.account.domain.exception.AccountIsNotOwnerException;
import com.auth.demo.sample.domain.model.Sample;
import com.auth.demo.sample.domain.persistence.SampleEntity;
import com.auth.demo.sample.domain.persistence.SampleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static java.lang.String.format;

@Slf4j
@Service
@RequiredArgsConstructor
public class SampleService {

	private final SampleRepository sampleRepository;

	public Page<SampleEntity> gets(Pageable pageable) {
		return sampleRepository.findAll(pageable);
	}

	public SampleEntity getOne(Long sampleId) {
		return sampleRepository.findById(sampleId).orElseThrow(RuntimeException::new);
	}

	public SampleEntity addSample(Sample sample) {
		return sampleRepository.save(sample.toEntity());
	}

	public SampleEntity updateSample(Sample sample, String username) {
		final SampleEntity targetSample = getOne(sample.getId());
		if (targetSample.isNotOwner(username)) {
			log.info(format("This account is not owner || targetId = %s || name = %s || date = %s", sample.getId(), username, LocalDateTime.now()));
			throw new AccountIsNotOwnerException("This account is not owner");
		}
		targetSample.updateStates(sample.getTitle(), sample.getDescription());
		return targetSample;
	}

	public void deleteSample(Long sampleId, String username) {
		final SampleEntity targetSample = getOne(sampleId);
		if (targetSample.isNotOwner(username)) {
			log.info(format("This account is not owner || targetId = %s || name = %s || date = %s", sampleId, username, LocalDateTime.now()));
			throw new  AccountIsNotOwnerException("This account is not owner");
		}
		sampleRepository.delete(targetSample);
	}
}