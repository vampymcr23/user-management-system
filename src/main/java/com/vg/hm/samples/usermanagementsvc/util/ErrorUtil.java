package com.vg.hm.samples.usermanagementsvc.util;

import com.vg.hm.samples.usermanagementsvc.exception.ApplicationException;
import com.vg.hm.samples.usermanagementsvc.exception.Error;
import org.springframework.http.HttpStatus;

/**
 * Utility class to throw exceptions.
 */
public class ErrorUtil {

    /**
     * Throws a custom exception.
     * @param errorMessage Error message.
     * @param errorCode error code.
     * @param status status.
     * @throws ApplicationException
     */
    public static void throwException(String errorMessage, String errorCode, int status) throws ApplicationException {
        Error error = Error.builder()
                .errorCode(errorCode)
                .errorMessage(errorMessage)
                .status(status)
                .build();
        throw new ApplicationException(error);
    }

    /**
     * Throws a custom exception.
     * @param field Field.
     * @throws ApplicationException
     */
    public static void throwMissingFieldException(String field) throws ApplicationException {
        Error error = Error.builder()
                .errorCode(ApplicationConstants.CLIENT_ERROR_CODE)
                .errorMessage(String.format("The field '%s' is required. Please make sure to provide a valid value.", field))
                .status(HttpStatus.BAD_REQUEST.value())
                .build();
        throw new ApplicationException(error);
    }
}
