package com.godel.project.javamasteryproject.services;

import com.godel.project.javamasteryproject.controllers.dto.EmployeeDto;
import com.godel.project.javamasteryproject.dao.EmployeeDao;
import com.godel.project.javamasteryproject.dao.entities.EmployeeEntity;
import com.godel.project.javamasteryproject.dao.interfaces.IEmployeeDao;
import com.godel.project.javamasteryproject.services.interfaces.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService implements IEmployeeService {

    private final IEmployeeDao employeeDao;
    private final EmployeeConverterToDtoOrEntity converter;

    @Autowired
    public EmployeeService(EmployeeDao employeeDao, EmployeeConverterToDtoOrEntity converter) {
        this.employeeDao = employeeDao;
        this.converter = converter;
    }

    @Override
    public EmployeeDto getById(long id) {
        EmployeeEntity employeeEntity = employeeDao.getById(id);
        return converter.convertToDto(employeeEntity);
    }

    @Override
    public EmployeeDto create(EmployeeDto employeeDto) {
        EmployeeEntity employeeEntity = employeeDao.create(converter.convertToEntity(employeeDto));
        return converter.convertToDto(employeeEntity);
    }

    @Override
    public List<EmployeeDto> getAll() {
        List<EmployeeEntity> entities = employeeDao.getAll();
        if (entities.isEmpty()) {
            return null;
        }
        return entities.stream().map(converter::convertToDto).collect(Collectors.toList());
    }

    @Override
    public EmployeeDto update(EmployeeDto employeeDto, long id) {
        EmployeeEntity entityForUpdate = employeeDao.updateById(converter.convertToEntity(employeeDto), id);
        if (entityForUpdate == null) {
            return null;
        }
        return converter.convertToDto(entityForUpdate);
    }

    @Override
    public String delete(long id) {
        try {
            employeeDao.getById(id);
        } catch (DataAccessException ex) {
            return String.format("The employee with id = %s not exist", id);
        }
        employeeDao.deleteById(id);
        return String.format("The employee with id = %s deleted successfully", id);
    }
}
