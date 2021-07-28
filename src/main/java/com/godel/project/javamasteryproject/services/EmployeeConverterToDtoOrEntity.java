package com.godel.project.javamasteryproject.services;

import com.godel.project.javamasteryproject.controllers.dto.EmployeeDto;
import com.godel.project.javamasteryproject.dao.entities.EmployeeEntity;
import com.godel.project.javamasteryproject.enums.Gender;
import com.godel.project.javamasteryproject.services.interfaces.IEmployeeConverterToDtoOrEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Objects;

@Component
public class EmployeeConverterToDtoOrEntity implements IEmployeeConverterToDtoOrEntity {

    private final ConversionService conversionService;

    @Autowired
    public EmployeeConverterToDtoOrEntity(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public EmployeeEntity convertToEntity(EmployeeDto employeeDto) {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setEmployeeId(employeeDto.getEmployeeId());
        employeeEntity.setFirstName(employeeDto.getFirstName());
        employeeEntity.setLastName(employeeDto.getLastName());
        employeeEntity.setDepartmentId(employeeDto.getDepartmentId());
        employeeEntity.setGender(Objects.requireNonNull(conversionService.convert(employeeDto.getGender(), Gender.class)));
        employeeEntity.setDateOfBirth(Objects.requireNonNull(conversionService.convert(employeeDto.getDateOfBirth(), LocalDate.class)));
        employeeEntity.setJobTitle(employeeDto.getJobTitle());
        return employeeEntity;
    }

    @Override
    public EmployeeDto convertToDto(EmployeeEntity employeeEntity) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setEmployeeId(employeeEntity.getEmployeeId());
        employeeDto.setFirstName(employeeEntity.getFirstName());
        employeeDto.setLastName(employeeEntity.getLastName());
        employeeDto.setDepartmentId(employeeEntity.getDepartmentId());
        employeeDto.setGender(employeeEntity.getGender().toString());
        employeeDto.setDateOfBirth(employeeEntity.getDateOfBirth().toString());
        employeeDto.setJobTitle(employeeEntity.getJobTitle());
        return employeeDto;
    }
}
