package com.godel.project.javaMasteryProject.services;

import com.godel.project.javaMasteryProject.controllers.dto.EmployeeDto;
import com.godel.project.javaMasteryProject.dao.EmployeeDao;
import com.godel.project.javaMasteryProject.dao.entities.EmployeeEntity;
import com.godel.project.javaMasteryProject.dao.interfaces.IEmployeeDao;
import com.godel.project.javaMasteryProject.services.interfaces.IEmployeeService;
import com.godel.project.javaMasteryProject.utils.EmployeeConverter;
import io.swagger.annotations.ApiOperation;
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
    private final EmployeeConverter converter;

    @Autowired
    public EmployeeService(EmployeeDao employeeDao, EmployeeConverter converter) {
        this.employeeDao = employeeDao;
        this.converter = converter;
    }

    @Override
    public EmployeeDto getById(long id) {
        Optional<EmployeeEntity> employeeEntityOptional = employeeDao.getById(id);
        if (employeeEntityOptional.isPresent()) {
            return converter.toDto(employeeEntityOptional.get());
        } else {
            throw new NoSuchElementException("Get this employee impossible. The such employee not exist");
        }
    }

    @Override
    public EmployeeDto create(EmployeeDto employeeDto) {
        EmployeeEntity employeeEntity = employeeDao.create(converter.toEntity(employeeDto));
        return converter.toDto(employeeEntity);
    }

    @Override
    public List<EmployeeDto> getAll() {
        List<EmployeeEntity> entities = employeeDao.getAll();
        if (entities.isEmpty()) {
            throw new EmptyResultDataAccessException("at least 1", 1);
        }
        return entities.stream().map(converter::toDto).collect(Collectors.toList());
    }

    @Override
    public EmployeeDto update(EmployeeDto employeeDto, long id) {
        Optional<EmployeeEntity> checkEmployeeOptional = employeeDao.getById(id);
        if (checkEmployeeOptional.isPresent()) {
            EmployeeEntity entityForUpdate = employeeDao.updateById(converter.toEntity(employeeDto), id);
            return converter.toDto(entityForUpdate);
        } else {
            throw new NoSuchElementException("Update this employee impossible. The such employee not exist");
        }
    }

    @Override
    public void delete(long id) {
        Optional<EmployeeEntity> checkEmployeeOptional = employeeDao.getById(id);
        if (checkEmployeeOptional.isPresent()) {
            employeeDao.deleteById(id);

        } else {
            throw new NoSuchElementException("Delete this employee impossible. The such employee not exist\"");
        }
    }
}
