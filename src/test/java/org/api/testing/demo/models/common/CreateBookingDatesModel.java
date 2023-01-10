package org.api.testing.demo.models.common;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateBookingDatesModel {
    private String checkin;
    private String checkout;
}
