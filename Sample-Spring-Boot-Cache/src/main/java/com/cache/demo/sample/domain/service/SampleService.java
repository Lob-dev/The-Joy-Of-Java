package com.cache.demo.sample.domain.service;

import com.cache.demo.sample.domain.persistence.SampleEntity;
import com.cache.demo.sample.domain.persistence.SampleRepository;
import com.cache.demo.sample.domain.service.model.Sample;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

	public SampleEntity updateSample(Sample sample) {
		final SampleEntity targetSample = getOne(sample.getId());
		targetSample.updateStates(sample.getTitle(), sample.getDescription());
		return targetSample;
	}

	public void deleteSample(Long sampleId) {
		sampleRepository.delete(getOne(sampleId));
	}
}
