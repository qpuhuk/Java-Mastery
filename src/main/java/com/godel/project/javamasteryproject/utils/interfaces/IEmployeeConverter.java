package com.godel.project.javamasteryproject.utils.interfaces;

import com.godel.project.javamasteryproject.controllers.dto.EmployeeDto;
import com.godel.project.javamasteryproject.dao.entities.EmployeeEntity;

public interface IEmployeeConverter {

    EmployeeEntity toEntity(EmployeeDto employeeDto);

    EmployeeDto toDto(EmployeeEntity employeeEntity);
}
