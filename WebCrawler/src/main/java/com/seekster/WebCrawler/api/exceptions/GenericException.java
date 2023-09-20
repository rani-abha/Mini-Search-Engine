package com.seekster.WebCrawler.api.exceptions;

/**
 * A generic custom exception class that can be used for various purposes.
 */
public class GenericException extends Exception {

    /**
     * Constructs a new GenericException with no specified detail message.
     */
    public GenericException() {
        super();
    }

    /**
     * Constructs a new GenericException with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method).
     */
    public GenericException(String message) {
        super(message);
    }

    /**
     * Constructs a new GenericException with the specified detail message and cause.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method).
     * @param cause   the cause (which is saved for later retrieval by the getCause() method).
     */
    public GenericException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new GenericException with the specified cause and a detail message of (cause==null ? null : cause.toString()) (which typically contains the class and detail message of cause).
     *
     * @param cause the cause (which is saved for later retrieval by the getCause() method).
     */
    public GenericException(Throwable cause) {
        super(cause);
    }
}
