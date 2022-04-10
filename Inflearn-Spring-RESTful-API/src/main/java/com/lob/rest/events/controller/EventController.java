package com.lob.rest.events.controller;

import com.lob.rest.events.controller.EventForm.Request;
import com.lob.rest.events.controller.EventForm.Response;
import com.lob.rest.events.domain.Event;
import com.lob.rest.events.domain.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

import static com.lob.rest.events.mapper.EventMapper.mapper;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

/**
 * @since       2021.04.02
 * @author      lob
 * @description EventController
 **********************************************************************************************************************/
@Controller
@RequestMapping(value="/api/events", produces=MediaTypes.HAL_JSON_VALUE+";charset=UTF-8")
@RequiredArgsConstructor
public class EventController {

    private final EventRepository eventRepository;
    //private final EventValidator eventValidator;
    //private final ModelMapper modelMapper;


    @PostMapping
    public ResponseEntity<Response.FindOne> create(@Valid @RequestBody Request.Add add) {
        /*
        Spring 은 errors 에 error message 를 저장
        @Valid - Errors or @Valid - BindingResult

        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }

        eventValidator.validate(crate, errors);
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }

        Event event = modelMapper.map(add, Event.class);
        */

        Event save = eventRepository.save(mapper.toEntity(add));
        save.update();

        WebMvcLinkBuilder link = linkTo(EventController.class);
        Response.FindOne findOne = mapper.toDto(save)
                .add(link.withSelfRel())
                .add(link.slash(save.getId()).withRel("query-events"))
                .add(link.slash(save.getId()).withRel("update-event"));

        return ResponseEntity.created(link.slash(findOne.getId())
                                        .toUri())
                                    .body(findOne);
    }
}
