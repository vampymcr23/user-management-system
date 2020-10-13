package com.vg.hm.samples.usermanagementsvc.rest.validator;

import com.vg.hm.samples.usermanagementsvc.rest.model.UserResource;
import com.vg.hm.samples.usermanagementsvc.util.ErrorUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

/**
 * Validator for Create User v1 API.
 */
@Component
public class UpdateUserV1Validator extends APIValidator<UserResource> {

    @Override
    public void validate(UserResource resource) {
        if (StringUtils.isBlank(resource.getUserEmail())) {
            ErrorUtil.throwMissingFieldException("userEmail");
        }
        if (StringUtils.isBlank(resource.getUserFirstName())) {
            ErrorUtil.throwMissingFieldException("userFirstName");
        }
        if (StringUtils.isBlank(resource.getUserLastName())) {
            ErrorUtil.throwMissingFieldException("userLastName");
        }
    }
}
