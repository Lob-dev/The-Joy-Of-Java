package com.cache.demo.sample.controller;

import com.cache.demo.sample.controller.form.SampleRequest.Add;
import com.cache.demo.sample.controller.form.SampleRequest.Find;
import com.cache.demo.sample.controller.form.SampleRequest.Update;
import com.cache.demo.sample.controller.form.SampleResponse.FindOne;
import com.cache.demo.sample.controller.form.SampleResponse.Info;
import com.cache.demo.sample.domain.service.SampleService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.cache.demo.global.RedisConfiguration.CacheKey.SAMPLE_KEY;

@RestController
@RequestMapping("/samples")
@RequiredArgsConstructor
public class SampleController {

	private final SampleService sampleService;

	/*
	desc  : @Cacheable(value = SAMPLE_KEY, key = "#find.pageSize-#find.pageNumber", sync = true) -> 실패
	refer : https://www.freecodecamp.org/news/how-to-implement-cacheable-pagination-of-frequently-changing-content-c8ddc8269e81/
	 */
	@GetMapping
	public Page<FindOne> gets(@Valid @RequestBody Find find) {
		return sampleService.gets(PageRequest.of(find.getPageNumber(), find.getPageSize())).map(FindOne::from);
	}

	@Cacheable(value = SAMPLE_KEY, key = "#sampleId", unless = "#result == null")
	@GetMapping("/{sampleId}")
	public FindOne get(@PathVariable Long sampleId) {
		return FindOne.from(sampleService.getOne(sampleId));
	}

	@PostMapping
	public Info add(@Valid @RequestBody Add add) {
		return Info.from(sampleService.addSample(add.toModel()));
	}

	@CachePut(value = SAMPLE_KEY, key = "#sampleId", unless = "#result == null")
	@PutMapping("/{sampleId}")
	public FindOne update(@Valid @RequestBody Update update, @PathVariable Long sampleId) {
		return FindOne.from(sampleService.updateSample(update.toModel(sampleId)));
	}

	@CacheEvict(value = SAMPLE_KEY, key = "#sampleId")
	@DeleteMapping("/{sampleId}")
	public ResponseEntity<String> delete(@PathVariable Long sampleId) {
		sampleService.deleteSample(sampleId);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("success");
	}
}
