package org.api.testing.demo.models.common;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateBookingModel {
    private String firstname;
    private String lastname;
    private int totalPrice;
    private boolean depositPaid;
    private CreateBookingDatesModel bookingdates;
    private String additionalNeeds;
}

