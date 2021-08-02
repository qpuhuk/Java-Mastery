package com.godel.project.javaMasteryProject.services.interfaces;

import com.godel.project.javaMasteryProject.controllers.dto.EmployeeDto;

import java.util.List;

public interface IEmployeeService {

    EmployeeDto getById(long id);

    EmployeeDto create(EmployeeDto employeeDto);

    List<EmployeeDto> getAll();

    EmployeeDto update(EmployeeDto employeeDto, long id);

    void delete(long id);
}
