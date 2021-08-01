package com.godel.project.javaMasteryProject.controllers.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "employee class")
public class EmployeeDto {

    @ApiModelProperty(value = "employee id", example = "1")
    private long employeeId;

    @ApiModelProperty(value = "employee's first name", example = "Aleksandr")
    @NonNull
    private String firstName;

    @ApiModelProperty(value = "employee's second name", example = "Linkevich")
    @NonNull
    private String lastName;

    @ApiModelProperty(value = "employee's number of department", example = "1")
    private long departmentId;

    @ApiModelProperty(value = "employee's title of job", example = "developer")
    @NonNull
    private String jobTitle;

    @ApiModelProperty(value = "employee's gender", example = "male or female")
    @NonNull
    private String gender;

    @ApiModelProperty(value = "employee's date of birth", example = "dd-MM-yyyy or yyyy-MM-dd or dd/MM/yyyy")
    @NonNull
    private String dateOfBirth;
}
