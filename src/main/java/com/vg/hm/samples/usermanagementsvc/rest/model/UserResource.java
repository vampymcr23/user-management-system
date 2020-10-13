package com.vg.hm.samples.usermanagementsvc.rest.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UserResource {
    private String userEmail;
    private String userFirstName;
    private String userLastName;
    private LocalDateTime creationDate;
    private LocalDateTime lastUpdateDate;
}
