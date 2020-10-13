package com.vg.hm.samples.usermanagementsvc.service.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * User model.
 */
@Builder
@Data
@ToString
public class User {
    private String email;
    private String firstName;
    private String lastName;
    private LocalDateTime creationDate;
    private LocalDateTime lastTimeUpdated;
}
