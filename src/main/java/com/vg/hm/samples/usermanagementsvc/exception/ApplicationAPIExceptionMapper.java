package com.vg.hm.samples.usermanagementsvc.exception;

import com.vg.hm.samples.usermanagementsvc.util.ApplicationConstants;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * This is the class that handles the mapping of any exception in our application to generate a well-formatted API response.
 */
@Log4j2
@Provider
public class ApplicationAPIExceptionMapper implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception e) {
        Error error = null;
        if (e instanceof ApplicationException) {
            ApplicationException applicationException = (ApplicationException) e;
            error = applicationException.getError();
            if (error.getStatus() <= 0) {
                error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            }
        } else {
            error = Error.builder()
                    .errorMessage(e.getMessage())
                    .errorCode(ApplicationConstants.UNKNOWN_ERROR_CODE)
                    .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .rootCause((e.getCause() != null) ? e.getCause().getMessage() : null)
                    .build();
        }

        return Response.status(error.getStatus()).entity(error).build();
    }
}
