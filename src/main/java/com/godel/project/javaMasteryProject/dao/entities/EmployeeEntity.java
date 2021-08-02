package com.godel.project.javaMasteryProject.dao.entities;

import com.godel.project.javaMasteryProject.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeEntity {
    private long employeeId;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    private long departmentId;
    @NonNull
    private String jobTitle;
    @NonNull
    private Gender gender;
    @NonNull
    private LocalDate dateOfBirth;
}
