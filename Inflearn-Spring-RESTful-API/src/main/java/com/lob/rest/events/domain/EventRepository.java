package com.lob.rest.events.domain;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @since 		2021.03.26
 * @author 		lob
 * @description EventRepository
 **********************************************************************************************************************/
public interface EventRepository extends JpaRepository<Event, Integer> {
}
