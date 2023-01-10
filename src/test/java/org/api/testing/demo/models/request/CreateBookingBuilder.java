/*
package org.api.testing.demo.models.request;

import org.api.testing.demo.models.common.CreateBookingDatesModel;
import org.api.testing.demo.models.common.CreateBookingModel;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateBookingBuilder {

    private final String name;

    public CreateBookingBuilder(String name) {
       this.name = name;
    }
    
    

    public static CreateBookingModel requestCreateBooking(String name, String lastname, int totalprice, Date checkInDate, Date checkOutDate, String additionalNeeds) {
        SimpleDateFormat formatterDate = new SimpleDateFormat("YYYY-MM-dd");

        return CreateBookingModel.builder()
                .firstname(name)
                .lastname(lastname)
                .totalPrice(totalprice)
                .depositPaid(true)
                .bookingdates(CreateBookingDatesModel.builder()
                        .checkin(formatterDate.format(checkInDate))
                        .checkout(formatterDate.format(checkOutDate))
                        .build())
                .additionalNeeds(additionalNeeds)
                .build();
    }

}
*/
