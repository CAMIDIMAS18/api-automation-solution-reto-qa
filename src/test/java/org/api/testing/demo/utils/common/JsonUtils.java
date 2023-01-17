package org.api.testing.demo.utils.common;

import com.google.gson.*;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.api.testing.demo.utils.exceptions.GenericRuntimeException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;

public class JsonUtils {

    private JsonUtils() {
        // Nothing
    }

    public static DocumentContext parseDocumentContextFromString(String jsonString) {
        return JsonPath.using(Configuration.defaultConfiguration()).parse(jsonString);
    }

    public static DocumentContext parseDocumentContextFromFile(String jsonFilePath) {
        File jsonObjectFile = new File(jsonFilePath);

        DocumentContext documentContext;

        try {
            documentContext = JsonPath.using(Configuration.defaultConfiguration()).parse(jsonObjectFile);
        } catch (IOException e) {
            throw new GenericRuntimeException(e);
        }

        return documentContext;
    }

    public static JsonObject parseJsonObject(String jsonObjectString) {
        JsonElement jsonElement = JsonParser.parseString(jsonObjectString);
        return jsonElement.getAsJsonObject();
    }

    public static JsonArray parseJsonArray(String jsonArrayString) {
        JsonElement jsonElement = JsonParser.parseString(jsonArrayString);
        return jsonElement.getAsJsonArray();
    }

    public static JSONObject parseJSONObject(String jsonObjectString) {
        try {
            return (JSONObject) new JSONParser().parse(jsonObjectString);
        } catch (ParseException e) {
            throw new GenericRuntimeException(e);
        }
    }

    public static JSONArray parseJSONArray(String jsonArrayString) {
        try {
            return (JSONArray) new JSONParser().parse(jsonArrayString);
        } catch (ParseException e) {
            throw new GenericRuntimeException(e);
        }
    }

    public static String getPrettyJsonElement(String jsonElement) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(JsonParser.parseString(jsonElement));
    }

    public static boolean isJsonStringValid(String jsonString) {
        try {
            JsonParser.parseString(jsonString);
        } catch (JsonSyntaxException e) {
            return false;
        }
        return true;
    }
}
