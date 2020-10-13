package com.vg.hm.samples.usermanagementsvc.exception;

import lombok.Builder;
import lombok.Data;

/**
 * Error message model.
 */
@Data
@Builder
public class Error {
    private String errorMessage;
    private String errorCode;
    private int status;
    private String rootCause;
}
