package com.lob.rest.events;

import com.lob.rest.events.controller.EventForm;
import com.lob.rest.events.domain.Event;
import com.lob.rest.events.domain.EventStatus;

import java.time.LocalDateTime;

/**
 * @author lob
 * @description EventTestHelper
 * @since 2021.03.26
 **********************************************************************************************************************/
public class EventTestHelper {

    public static EventForm.Request.Add getEventRequest() {
        return new EventForm.Request.Add(
                "Spring",
                "REST API Development with Spring",
                LocalDateTime.of(2018, 11, 23, 14, 21),
                LocalDateTime.of(2018, 11, 24, 14, 21),
                LocalDateTime.of(2018, 11, 25, 14, 21),
                LocalDateTime.of(2018, 11, 26, 14, 21),
                100,
                200,
                100,
                "강남 D2 스타텁 팩토리");
    }

    public static Event getEventBadRequest() {
        return Event.builder()
                .name("Spring")
                .description("REST API Development with Spring")
                .beginEnrollmentDateTime(LocalDateTime.of(2018, 11, 23, 14, 21))
                .closeEnrollmentDateTime(LocalDateTime.of(2018, 11, 24, 14, 21))
                .beginEventDateTime(LocalDateTime.of(2018, 11, 25, 14, 21))
                .endEventDateTime(LocalDateTime.of(2018, 11, 26, 14, 21))
                .basePrice(100)
                .maxPrice(200)
                .limitOfEnrollment(100)
                .location("강남 D2 스타텁 팩토리")
                .free(false)
                .offline(false)
                .eventStatus(EventStatus.PUBLISHED)
                .build()
                ;
    }

}
