package com.sravani.cr.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@NoArgsConstructor
@AllArgsConstructor
public class ApiError {
    private Date timestamp;
    private String message;
    private String details;
}
