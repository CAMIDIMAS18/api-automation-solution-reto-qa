package org.api.testing.demo.models.headers;


public enum HeaderValueModel {
    ACCEPT("Accept", "application/json"),
    AUTHORIZATION("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM="),
    CONTENT_TYPE("Content-Type", "application/json"),
    COOKIE("Cookie", "");

    private String header;
    private String value;

    HeaderValueModel(String header, String value) {
        this.header = header;
        this.value = value;
    }

    public String getHeader() {
        return header;
    }

    public String getValue() {
        return value;
    }
}
