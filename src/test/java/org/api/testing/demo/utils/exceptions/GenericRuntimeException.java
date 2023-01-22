package org.api.testing.demo.utils.exceptions;

public class GenericRuntimeException extends RuntimeException {

    public GenericRuntimeException() {
        super();
    }

    public GenericRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public GenericRuntimeException(String message) {
        super(message, null);
    }

    public GenericRuntimeException(Exception exception) {
        super(exception.getMessage(), null);
    }
}
