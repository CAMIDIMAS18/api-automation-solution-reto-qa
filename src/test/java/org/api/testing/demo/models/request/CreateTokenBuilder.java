package org.api.testing.demo.models.request;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONObject;

public class CreateTokenBuilder {

    private DocumentContext requestBody = JsonPath.parse(new JSONObject());

    public String getRequestBody() {
        return requestBody.jsonString();
    }

    public static class Builder {
        private CreateTokenBuilder createTokenBuilder = new CreateTokenBuilder();

        private Builder() {
            // initialize without attributes
        }

        public static Builder andRequestBody() { //start building the model
            return new Builder();
        }

        public Builder withUsername(String username) {
            createTokenBuilder.requestBody.put("$", "username", username);
            return this;
        }

        public Builder andPassword(String password) {
            createTokenBuilder.requestBody.put("$", "password", password);
            return this;
        }

        public CreateTokenBuilder build() { // Returns an instance of the model
            return createTokenBuilder;
        }
    }
}
