package com.godel.project.javaMasteryProject.utils.interfaces;

import com.godel.project.javaMasteryProject.controllers.dto.EmployeeDto;
import com.godel.project.javaMasteryProject.dao.entities.EmployeeEntity;

public interface IEmployeeConverter {

    EmployeeEntity toEntity(EmployeeDto employeeDto);

    EmployeeDto toDto(EmployeeEntity employeeEntity);
}
