package com.godel.project.javamasteryproject.exceptions;

import lombok.Data;

@Data
public class ErrorResponse {
    private Integer status;
    private String message;
    private String error;
}
