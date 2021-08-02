package com.godel.project.javaMasteryProject.utils;

import com.godel.project.javaMasteryProject.dao.entities.EmployeeEntity;
import com.godel.project.javaMasteryProject.enums.Gender;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeRowMapper implements RowMapper {

    @Override
    public EmployeeEntity mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setEmployeeId(resultSet.getLong("employee_id"));
        employeeEntity.setFirstName(resultSet.getString("first_name"));
        employeeEntity.setLastName(resultSet.getString("last_name"));
        employeeEntity.setDepartmentId(resultSet.getLong("department_id"));
        employeeEntity.setGender(Gender.valueOf(resultSet.getString("gender").toUpperCase()));
        employeeEntity.setDateOfBirth(resultSet.getTimestamp("date_of_birth").toLocalDateTime().toLocalDate());
        employeeEntity.setJobTitle(resultSet.getString("job_title"));
        return employeeEntity;
    }
}
