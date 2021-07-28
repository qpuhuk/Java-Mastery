package com.godel.project.javamasteryproject.dao.interfaces;

import com.godel.project.javamasteryproject.dao.entities.EmployeeEntity;

import java.util.List;

public interface IEmployeeDao {

    EmployeeEntity create(EmployeeEntity employeeEntity);

    EmployeeEntity getById(long id);

    List<EmployeeEntity> getAll();

    EmployeeEntity updateById(EmployeeEntity employeeEntity, long id);

    void deleteById(long id);
}
