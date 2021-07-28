package com.godel.project.javamasteryproject.services.interfaces;

import com.godel.project.javamasteryproject.controllers.dto.EmployeeDto;

import java.util.List;

public interface IEmployeeService {

    EmployeeDto getById(long id);

    EmployeeDto create(EmployeeDto employeeDto);

    List<EmployeeDto> getAll();

    EmployeeDto update(EmployeeDto employeeDto, long id);

    String delete(long id);
}
