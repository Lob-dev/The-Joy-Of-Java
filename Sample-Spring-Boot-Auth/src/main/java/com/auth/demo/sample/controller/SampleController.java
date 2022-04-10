package com.auth.demo.sample.controller;

import com.auth.demo.sample.controller.form.SampleRequest;
import com.auth.demo.sample.controller.form.SampleResponse;
import com.auth.demo.sample.domain.service.SampleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/samples")
@RequiredArgsConstructor
public class SampleController {

	private final SampleService sampleService;

	@GetMapping
	public Page<SampleResponse.FindOne> gets(@Valid @RequestBody SampleRequest.Find find) {
		return sampleService.gets(PageRequest.of(find.getPageNumber(), find.getPageSize())).map(SampleResponse.FindOne::from);
	}

	@GetMapping("/{sampleId}")
	public SampleResponse.FindOne get(@PathVariable Long sampleId) {
		return SampleResponse.FindOne.from(sampleService.getOne(sampleId));
	}

	@PostMapping
	public SampleResponse.Info add(
			@Valid @RequestBody SampleRequest.Add add,
			@AuthenticationPrincipal String username
	) {
		return SampleResponse.Info.from(sampleService.addSample(add.toModelWith(username)));
	}

	@PutMapping("/{sampleId}")
	public SampleResponse.FindOne update(
			@Valid @RequestBody SampleRequest.Update update,
			@PathVariable Long sampleId,
			@AuthenticationPrincipal String username
	) {
		return SampleResponse.FindOne.from(sampleService.updateSample(update.toModelWith(sampleId), username));
	}

	@DeleteMapping("/{sampleId}")
	public ResponseEntity<String> delete(
			@PathVariable Long sampleId,
			@AuthenticationPrincipal String username
	) {
		sampleService.deleteSample(sampleId, username);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("success");
	}
}