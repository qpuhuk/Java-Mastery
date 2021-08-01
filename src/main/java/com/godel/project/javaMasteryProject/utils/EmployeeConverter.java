package com.godel.project.javaMasteryProject.utils;

import com.godel.project.javaMasteryProject.controllers.dto.EmployeeDto;
import com.godel.project.javaMasteryProject.dao.entities.EmployeeEntity;
import com.godel.project.javaMasteryProject.enums.Gender;
import com.godel.project.javaMasteryProject.utils.interfaces.IEmployeeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Objects;

@Component
public class EmployeeConverter implements IEmployeeConverter {

    private final ConversionService conversionService;

    @Autowired
    public EmployeeConverter(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public EmployeeEntity toEntity(EmployeeDto employeeDto) {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setEmployeeId(employeeDto.getEmployeeId());
        employeeEntity.setFirstName(employeeDto.getFirstName());
        employeeEntity.setLastName(employeeDto.getLastName());
        employeeEntity.setDepartmentId(employeeDto.getDepartmentId());
        employeeEntity.setGender(Gender.valueOf(employeeDto.getGender().toUpperCase()));
        employeeEntity.setDateOfBirth(Objects.requireNonNull(conversionService.convert(employeeDto.getDateOfBirth(), LocalDate.class)));
        employeeEntity.setJobTitle(employeeDto.getJobTitle());
        return employeeEntity;
    }

    @Override
    public EmployeeDto toDto(EmployeeEntity employeeEntity) {
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
