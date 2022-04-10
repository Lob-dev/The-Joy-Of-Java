package com.lob.rest.events.mapper;

import com.lob.rest.events.controller.EventForm.Request.Add;
import com.lob.rest.events.controller.EventForm.Response.FindOne;
import com.lob.rest.events.controller.EventForm.Response.FindOne.FindOneBuilder;
import com.lob.rest.events.domain.Event;
import com.lob.rest.events.domain.Event.EventBuilder;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-05-11T00:30:34+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.2 (Oracle Corporation)"
)
public class EventMapperImpl implements EventMapper {

    @Override
    public Event toEntity(Add add) {
        if ( add == null ) {
            return null;
        }

        EventBuilder event = Event.builder();

        event.name( add.getName() );
        event.description( add.getDescription() );
        event.beginEnrollmentDateTime( add.getBeginEnrollmentDateTime() );
        event.closeEnrollmentDateTime( add.getCloseEnrollmentDateTime() );
        event.beginEventDateTime( add.getBeginEventDateTime() );
        event.endEventDateTime( add.getEndEventDateTime() );
        event.location( add.getLocation() );
        if ( add.getBasePrice() != null ) {
            event.basePrice( add.getBasePrice() );
        }
        if ( add.getMaxPrice() != null ) {
            event.maxPrice( add.getMaxPrice() );
        }
        if ( add.getLimitOfEnrollment() != null ) {
            event.limitOfEnrollment( add.getLimitOfEnrollment() );
        }

        return event.build();
    }

    @Override
    public FindOne toDto(Event event) {
        if ( event == null ) {
            return null;
        }

        FindOneBuilder findOne = FindOne.builder();

        findOne.id( event.getId() );
        findOne.name( event.getName() );
        findOne.description( event.getDescription() );
        findOne.beginEnrollmentDateTime( event.getBeginEnrollmentDateTime() );
        findOne.closeEnrollmentDateTime( event.getCloseEnrollmentDateTime() );
        findOne.beginEventDateTime( event.getBeginEventDateTime() );
        findOne.endEventDateTime( event.getEndEventDateTime() );
        findOne.location( event.getLocation() );
        findOne.basePrice( event.getBasePrice() );
        findOne.maxPrice( event.getMaxPrice() );
        findOne.limitOfEnrollment( event.getLimitOfEnrollment() );
        findOne.offline( event.isOffline() );
        findOne.free( event.isFree() );
        findOne.eventStatus( event.getEventStatus() );

        return findOne.build();
    }
}
