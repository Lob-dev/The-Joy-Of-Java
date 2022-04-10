package com.cache.demo.sample.controller;

import com.cache.demo.sample.controller.form.SampleRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
class SampleApiDocumentation {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void t01_gets() throws Exception {
		mockMvc.perform(get("/samples")
						.contentType(APPLICATION_JSON)
						.content("{  \"pageNumber\" : 0,  \"pageSize\" : 10 }"))
				.andDo(print())
				.andDo(document("Sample/gets",
						preprocessRequest(prettyPrint()),
						preprocessResponse(prettyPrint()),
						requestFields(
								fieldWithPath("pageNumber").description("The page to retrieve"),
								fieldWithPath("pageSize").description("The number of elements within a single page")
						),
						responseFields(
								fieldWithPath("content.[].title").description("Sample resource title"),
								fieldWithPath("content.[].description").description("Sample resource description"),
								fieldWithPath("pageable.sort.sorted").description("Pagination sort status"),
								fieldWithPath("pageable.sort.unsorted").description("Pagination un sort status"),
								fieldWithPath("pageable.sort.empty").description("Pagination sort key exist status"),
								fieldWithPath("pageable.offset").description("Pagination find resource offset value"),
								fieldWithPath("pageable.pageSize").description("Pagination find resource size"),
								fieldWithPath("pageable.pageNumber").description("Pagination chunk number"),
								fieldWithPath("pageable.unpaged").description("Pagination un chunk status"),
								fieldWithPath("pageable.paged").description("Pagination chunk status"),
								fieldWithPath("last").description("Pagination last chunk status"),
								fieldWithPath("totalElements").description("all resources number"),
								fieldWithPath("totalPages").description("all pagination chunk number"),
								fieldWithPath("size").description("Pagination chunk size"),
								fieldWithPath("number").description("Pagination chunk number"),
								fieldWithPath("first").description("Pagination first chunk status"),
								fieldWithPath("numberOfElements").description("Pagination chunk elements count"),
								fieldWithPath("empty").description("Pagination chunk empty status"),
								fieldWithPath("sort.sorted").description("Pagination sort resource sort status"),
								fieldWithPath("sort.unsorted").description("Pagination sort resource un sort status"),
								fieldWithPath("sort.empty").description("Pagination sort resource exist status")
						)
				)).andExpect(status().isOk());
	}


	@Test
	void t02_get() throws Exception {
		mockMvc.perform(get("/samples/{sampleId}", 1)
						.contentType(APPLICATION_JSON))
				.andDo(print())
				.andDo(document("Sample/get",
						preprocessRequest(prettyPrint()),
						preprocessResponse(prettyPrint()),
						pathParameters(
								parameterWithName("sampleId").description("Sample Resource Id")
						),
						responseFields(
								fieldWithPath("title").description("Sample Title"),
								fieldWithPath("description").description("Sample Description")
						)
				)).andExpect(status().isOk());
	}


	@Test
	void t03_add() throws Exception {

		final SampleRequest.Add add = new SampleRequest.Add("title", "content");

		mockMvc.perform(post("/samples")
						.contentType(APPLICATION_JSON)
						.content(toJson(add)))
				.andDo(print())
				.andDo(document("Sample/post",
						preprocessRequest(prettyPrint()),
						preprocessResponse(prettyPrint()),
						requestFields(
								fieldWithPath("title").description("Sample Title"),
								fieldWithPath("description").description("Sample Description")
						),
						responseFields(
								fieldWithPath("id").description("Sample Resource Id"),
								fieldWithPath("title").description("Sample Title")
						)
						)).andExpect(status().isOk());
	}

	private String toJson(Object object) throws JsonProcessingException {
		return objectMapper.writeValueAsString(object);
	}
}