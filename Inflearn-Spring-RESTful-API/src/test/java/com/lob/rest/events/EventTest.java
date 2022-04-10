package com.lob.rest.events;

import com.lob.rest.events.domain.Event;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {

    @Test
    void builder() {
        // Given - Then
        Event event = Event.builder().build();

        // When
        assertNotNull(event);
    }

    @Test
    void builder_argument() {
        // Given - Then
        Event event = Event.builder()
                .name("Inflearn Spring REST API")
                .description("REST API Development with Spring")
                .build();

        // When
        assertNotNull(event);
    }

    /*
     4 + params 기준 @Parameters(method = "testParams")

     private Object[] testParams() {
        return new Object[] {
            new Object[] (0, 10),
            new Object[] (0, 100),
            new Object[] (5, 10),
            new Object[] (50, 100)
        }
     }
     */
    @ParameterizedTest
    @CsvSource({"0, 10", "0, 100", "5, 10", "50, 100"})
    void testIsNotFree(int basePrice, int maxPrice) {

        // Given
        Event event = Event.builder()
                .basePrice(basePrice)
                .maxPrice(maxPrice)
                .build();

        // When
        event.update();

        // Then
        assertFalse(event.isFree());
    }

}