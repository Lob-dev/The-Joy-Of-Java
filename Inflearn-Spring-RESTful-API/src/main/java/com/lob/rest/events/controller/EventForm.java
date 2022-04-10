package com.lob.rest.events.controller;

import com.lob.rest.events.domain.EventStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @since       2021.04.02
 * @author      lob
 * @description EventForm
 **********************************************************************************************************************/
public class EventForm {

    @NoArgsConstructor(access=AccessLevel.PRIVATE)
    public static class Request {

        @Getter
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Add {

            @NotBlank
            private String name;

            @NotBlank
            private String description;

            @NotNull
            private LocalDateTime beginEnrollmentDateTime;

            @NotNull
            private LocalDateTime closeEnrollmentDateTime;

            @NotNull
            private LocalDateTime beginEventDateTime;

            @NotNull
            private LocalDateTime endEventDateTime;

            @NotNull
            @Min(value=0)
            private Integer limitOfEnrollment;

            @NotNull
            @Min(value=0)
            private Integer basePrice;

            @NotNull
            @Min(value=0)
            private Integer maxPrice;

            @NotBlank
            private String location;

        }

    }

    @NoArgsConstructor(access=AccessLevel.PRIVATE)
    public static class Response {

        @Getter
        @Builder
        public static class FindOne extends RepresentationModel<FindOne> {

            private final Integer id;
            private final String name;
            private final String description;
            private final LocalDateTime beginEnrollmentDateTime;
            private final LocalDateTime closeEnrollmentDateTime;
            private final LocalDateTime beginEventDateTime;
            private final LocalDateTime endEventDateTime;
            private final String location;
            private final int basePrice;
            private final int maxPrice;
            private final int limitOfEnrollment;
            private final boolean offline;
            private final boolean free;
            private final EventStatus eventStatus;

        }

    }

}
