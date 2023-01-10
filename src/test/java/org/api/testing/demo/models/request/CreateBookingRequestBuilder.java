package org.api.testing.demo.models.request;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONObject;

public class CreateBookingRequestBuilder {

    private DocumentContext requestBody = JsonPath.parse(new JSONObject());

    public String getRequestBody() {
        return requestBody.jsonString();
    }

    public static class Builder {
        private CreateBookingRequestBuilder createBookingRequestBuilder = new CreateBookingRequestBuilder();

        private Builder() {
            // initialize without attributes
        }

        public static Builder postApi() { //inicia la construccion del modelo
            return new Builder();
        }

        public Builder withFirstName(String firstName) { //arma json
            createBookingRequestBuilder.requestBody.put("$", "firstname", firstName);
            return this;
        }

        public Builder andLastName(String lastName) {
            createBookingRequestBuilder.requestBody.put("$", "lastname", lastName);
            return this;
        }

        public Builder andTotalPrice(Integer totalPrice) {
            createBookingRequestBuilder.requestBody.put("$", "totalprice", totalPrice);
            return this;
        }

        public Builder andDepositPaid(Boolean depositPaid) {
            createBookingRequestBuilder.requestBody.put("$", "depositpaid", depositPaid);
            return this;
        }

        public Builder andCheckInBookingDate(String checkIn) {

            createBookingRequestBuilder.requestBody.put("$", "bookingdates.checkin", checkIn);
            return this;
        }

        public Builder andCheckOutBookingDate(String checkOut) {
            createBookingRequestBuilder.requestBody.put("$", "bookingdates.checkout", checkOut);
            return this;
        }

        public Builder andAdditionalNeeds(String additionalNeeds) {
            createBookingRequestBuilder.requestBody.put("$", "additionalneeds", additionalNeeds);
            return this;
        }

        public CreateBookingRequestBuilder build() { // Entrega una instancia del modelo
            return createBookingRequestBuilder;
        }
    }
}
