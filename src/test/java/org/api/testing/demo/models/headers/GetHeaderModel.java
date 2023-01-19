package org.api.testing.demo.models.headers;

import java.util.HashMap;
import java.util.Map;

import static org.api.testing.demo.steps.hooks.Actors.CAMILA;
import static org.api.testing.demo.utils.constants.Constants.TOKEN;

public class GetHeaderModel {

    private static final Map<String, String> headers = new HashMap<>();

    private GetHeaderModel() {
    }

    public static Map<String, String> headersDefault() {
        headers.put(HeaderValueModel.CONTENT_TYPE.getHeader(), HeaderValueModel.CONTENT_TYPE.getValue());
        headers.put(HeaderValueModel.ACCEPT.getHeader(), HeaderValueModel.ACCEPT.getValue());
        return headers;
    }

    public static Map<String, String> headersAuthentication() {
        headers.put(HeaderValueModel.CONTENT_TYPE.getHeader(), HeaderValueModel.CONTENT_TYPE.getValue());
        headers.put(HeaderValueModel.ACCEPT.getHeader(), HeaderValueModel.ACCEPT.getValue());
        headers.put(HeaderValueModel.COOKIE.getHeader(), CAMILA.recall(TOKEN));
        return headers;
    }
}
