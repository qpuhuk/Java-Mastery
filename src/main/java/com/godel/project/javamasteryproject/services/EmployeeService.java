package com.godel.project.javamasteryproject.services;

import com.godel.project.javamasteryproject.controllers.dto.EmployeeDto;
import com.godel.project.javamasteryproject.dao.EmployeeDao;
import com.godel.project.javamasteryproject.dao.entities.EmployeeEntity;
import com.godel.project.javamasteryproject.dao.interfaces.IEmployeeDao;
import com.godel.project.javamasteryproject.services.interfaces.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
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
        Optional<EmployeeEntity> employeeEntityOptional = employeeDao.getById(id);
        if (employeeEntityOptional.isPresent()) {
            return converter.convertToDto(employeeEntityOptional.get());
        } else {
            throw new NoSuchElementException();
        }
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
            throw new EmptyResultDataAccessException(1);
        }
        return entities.stream().map(converter::convertToDto).collect(Collectors.toList());
    }

    @Override
    public EmployeeDto update(EmployeeDto employeeDto, long id) {
        Optional<EmployeeEntity> checkEmployeeOptional = employeeDao.getById(id);
        if (checkEmployeeOptional.isPresent()) {
            EmployeeEntity entityForUpdate = employeeDao.updateById(converter.convertToEntity(employeeDto), id);
            return converter.convertToDto(entityForUpdate);
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public String delete(long id) {
        Optional<EmployeeEntity> checkEmployeeOptional = employeeDao.getById(id);
        if (checkEmployeeOptional.isPresent()) {
            employeeDao.deleteById(id);
            return "Employee deleted successfully";
        } else {
            throw new NoSuchElementException();
        }
    }
}
