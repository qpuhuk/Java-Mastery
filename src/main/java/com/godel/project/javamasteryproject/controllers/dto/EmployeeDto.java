package com.godel.project.javamasteryproject.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
    private long employeeId;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    private long departmentId;
    @NonNull
    private String jobTitle;
    @NonNull
    private String gender;
    @NonNull
    private String dateOfBirth;
}
