package com.godel.project.javamasteryproject.services.interfaces;

import com.godel.project.javamasteryproject.controllers.dto.EmployeeDto;
import com.godel.project.javamasteryproject.dao.entities.EmployeeEntity;

public interface IEmployeeConverterToDtoOrEntity {

    EmployeeEntity convertToEntity(EmployeeDto employeeDto);

    EmployeeDto convertToDto(EmployeeEntity employeeEntity);
}
