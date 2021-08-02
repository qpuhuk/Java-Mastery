package com.godel.project.javaMasteryProject.dao.interfaces;

import com.godel.project.javaMasteryProject.dao.entities.EmployeeEntity;

import java.util.List;
import java.util.Optional;

public interface IEmployeeDao {

    EmployeeEntity create(EmployeeEntity employeeEntity);

    Optional<EmployeeEntity> getById(long id);

    List<EmployeeEntity> getAll();

    EmployeeEntity updateById(EmployeeEntity employeeEntity, long id);

    void deleteById(long id);
}
