package org.api.testing.demo.utils.common;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

public class JsonUtils {

    private JsonUtils() {
        // Nothing
    }

    public static DocumentContext parseDocumentContextFromString(String jsonString) {
        return JsonPath.using(Configuration.defaultConfiguration()).parse(jsonString);
    }

    public static JsonObject parseJsonObject(String jsonObjectString) {
        JsonElement jsonElement = JsonParser.parseString(jsonObjectString);
        return jsonElement.getAsJsonObject();
    }
}