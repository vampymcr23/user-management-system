package com.vg.hm.samples.usermanagementsvc.exception;

/**
 * Custom exception.
 */
public class ApplicationException extends RuntimeException {
    private Error error;

    /**
     * Constructor to send an error message.
     *
     * @param message message.
     */
    public ApplicationException(String message) {
        error = Error.builder()
                .errorMessage(message)
                .build();
    }

    /**
     * Constructor for application exception.
     *
     * @param error error model.
     */
    public ApplicationException(Error error) {
        this.error = error;
    }

    public Error getError() {
        return error;
    }

}
