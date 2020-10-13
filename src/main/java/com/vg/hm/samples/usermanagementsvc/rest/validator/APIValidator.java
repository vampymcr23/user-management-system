package com.vg.hm.samples.usermanagementsvc.rest.validator;

import com.vg.hm.samples.usermanagementsvc.exception.ApplicationException;

/**
 * Abstract class to validate API requests.
 * @param <T> Resource model of the request to be validated.
 */
public abstract class APIValidator<T> {

    /**
     * Validates the input object.
     * @param resourceModel resource model.
     */
    public abstract void validate(T resourceModel) throws ApplicationException;
}
