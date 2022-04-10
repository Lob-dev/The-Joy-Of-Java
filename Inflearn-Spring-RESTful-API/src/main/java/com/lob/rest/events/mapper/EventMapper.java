package com.lob.rest.events.mapper;

import com.lob.rest.events.controller.EventForm.Request;
import com.lob.rest.events.controller.EventForm.Response;
import com.lob.rest.events.domain.Event;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * @since       2021.04.02
 * @author      lob
 * @description EventMapper
 **********************************************************************************************************************/
@Mapper(unmappedTargetPolicy=ReportingPolicy.IGNORE)
public interface EventMapper {

    EventMapper mapper = Mappers.getMapper(EventMapper.class);

    Event toEntity(Request.Add add);

    Response.FindOne toDto(Event event);

}
