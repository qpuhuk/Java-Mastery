package com.godel.project.javaMasteryProject.dao;

import com.godel.project.javaMasteryProject.dao.entities.EmployeeEntity;
import com.godel.project.javaMasteryProject.dao.interfaces.IEmployeeDao;
import com.godel.project.javaMasteryProject.utils.EmployeeRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class EmployeeDao implements IEmployeeDao {

    private  JdbcTemplate jdbcTemplate;
    private  SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    public EmployeeDao(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("employees")
                .usingGeneratedKeyColumns("employee_id");
    }

    @Override
    public EmployeeEntity create(EmployeeEntity employeeEntity) {

        Map<String, Object> params = new HashMap<>();
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
    public Optional<EmployeeEntity> getById(long id) {
        String query = "SELECT *  FROM employees WHERE employee_id = ?";
        Optional<EmployeeEntity> employeeEntityOptional = jdbcTemplate.query(
                query, new Object[]{id}, new EmployeeRowMapper()).stream().findAny();
        return employeeEntityOptional;
    }

    @Override
    public List<EmployeeEntity> getAll() {
        String query = "SELECT * FROM employees ORDER BY employee_id";
        return jdbcTemplate.query(query, new EmployeeRowMapper());
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
        String query = "DELETE FROM employees WHERE employee_id = ?";
        jdbcTemplate.update(query, id);
    }
}
