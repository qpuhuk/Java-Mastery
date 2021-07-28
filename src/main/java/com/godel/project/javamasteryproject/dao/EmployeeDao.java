package com.godel.project.javamasteryproject.dao;

import com.godel.project.javamasteryproject.dao.entities.EmployeeEntity;
import com.godel.project.javamasteryproject.dao.interfaces.IEmployeeDao;
import com.godel.project.javamasteryproject.services.EmployeeRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class EmployeeDao implements IEmployeeDao {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    public EmployeeDao(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("employees")
                .usingGeneratedKeyColumns("employee_id");
    }

    @Override
    public EmployeeEntity create(EmployeeEntity employeeEntity) {

        Map params = new HashMap();
        params.put("first_name", employeeEntity.getFirstName());
        params.put("last_name", employeeEntity.getLastName());
        params.put("department_id", employeeEntity.getDepartmentId());
        params.put("job_title", employeeEntity.getJobTitle());
        params.put("gender", employeeEntity.getGender().name());
        params.put("date_of_birth", employeeEntity.getDateOfBirth());

        long generateId = simpleJdbcInsert.executeAndReturnKey(params).longValue();
        employeeEntity.setEmployeeId(generateId);
        return employeeEntity;
    }

    @Override
    public EmployeeEntity getById(long id) {

        return (EmployeeEntity) jdbcTemplate.queryForObject(
                "SELECT *  FROM employees WHERE employee_id = ?", new EmployeeRowMapper(), new Object[]{id});
    }

    @Override
    public List<EmployeeEntity> getAll() {
        return jdbcTemplate.query("SELECT * FROM employees ORDER BY employee_id", new EmployeeRowMapper());
    }

    @Override
    public EmployeeEntity updateById(EmployeeEntity employeeEntity, long id) {
        jdbcTemplate.update("UPDATE employees SET " +
                        "first_name = ?, " +
                        "last_name = ?, " +
                        "department_id = ?, " +
                        "job_title = ?, " +
                        "gender = ?, " +
                        "date_of_birth = ? " +
                        "WHERE employee_id = ?",
                employeeEntity.getFirstName(),
                employeeEntity.getLastName(),
                employeeEntity.getDepartmentId(),
                employeeEntity.getJobTitle(),
                employeeEntity.getGender().name(),
                employeeEntity.getDateOfBirth(),
                id);
        employeeEntity.setEmployeeId(id);
        return employeeEntity;
    }

    @Override
    public void deleteById(long id) {
        jdbcTemplate.update("DELETE FROM employees WHERE employee_id = ?", id);
    }
}
