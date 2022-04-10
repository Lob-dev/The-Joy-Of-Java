package com.lob.rest.events.domain;

import com.lob.rest.events.controller.EventForm.Request;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import java.time.LocalDateTime;

/**
 * @since       2021.05.04
 * @author      lob
 * @description EventValidator
 **********************************************************************************************************************/
@Component
public class EventValidator {

    public void validate(Request.Add create, Errors errors) {
        if (create.getBasePrice() > create.getMaxPrice() && create.getMaxPrice() > 0) {
            errors.rejectValue("basePrice", "wrongValue", "basePrice is wrong.");
            errors.rejectValue("maxPrice", "wrongValue", "maxPrice is wrong.");
        }

        LocalDateTime endEventDateTime = create.getEndEventDateTime();
        if (endEventDateTime.isBefore(create.getBeginEventDateTime()) || endEventDateTime.isBefore(create.getCloseEnrollmentDateTime())
        || endEventDateTime.isBefore(create.getBeginEventDateTime())) {
            errors.rejectValue("endEventDateTime", "wrongValue", "endEventDateTime is wrongValue.");
        }


    }

}


