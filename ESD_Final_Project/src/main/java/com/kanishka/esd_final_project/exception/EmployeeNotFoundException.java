package com.kanishka.esd_final_project.exception;


import lombok.Data;

@Data
public class EmployeeNotFoundException extends RuntimeException {
    private final String message;
}
