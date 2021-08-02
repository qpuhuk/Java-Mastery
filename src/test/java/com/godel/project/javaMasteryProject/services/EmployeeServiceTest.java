package com.godel.project.javaMasteryProject.services;

import com.godel.project.javaMasteryProject.controllers.dto.EmployeeDto;
import com.godel.project.javaMasteryProject.services.interfaces.IEmployeeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class EmployeeServiceTest {

    @Autowired
    private IEmployeeService employeeService;
    private EmployeeDto employeeDtoBefore;

    @BeforeEach
    void setUp() {
        employeeDtoBefore = new EmployeeDto();
        employeeDtoBefore.setEmployeeId(4);
        employeeDtoBefore.setJobTitle("113");
        employeeDtoBefore.setGender("MALE");
        employeeDtoBefore.setDateOfBirth("1990-07-31");
        employeeDtoBefore.setDepartmentId(3);
        employeeDtoBefore.setLastName("employee");
        employeeDtoBefore.setFirstName("employee");
    }

    @Test
    void getById() {
        employeeService.create(employeeDtoBefore);
        EmployeeDto employeeDtoForCheck = employeeService.getById(4);
        Assertions.assertEquals(employeeDtoForCheck, employeeDtoBefore);
    }

    @Test
    void create() {
        List<EmployeeDto> employeeDtoListStart = employeeService.getAll();
        EmployeeDto employeeTest = employeeService.create(employeeDtoBefore);

        List<EmployeeDto> employeeDtoListActual = employeeService.getAll();
        Assertions.assertEquals(employeeDtoListStart.size(), employeeDtoListActual.size() - 1);

        EmployeeDto employeeGotFromDb = employeeService.getById(employeeTest.getEmployeeId());

        Assertions.assertEquals(employeeGotFromDb, employeeTest);
    }

    @Test
    void update() {
        EmployeeDto employeeBasic = employeeService.create(employeeDtoBefore);

        EmployeeDto employeeForUpdate = new EmployeeDto();
        employeeForUpdate.setJobTitle("new");
        employeeForUpdate.setDateOfBirth("2000-05-05");
        employeeForUpdate.setDepartmentId(10);
        employeeForUpdate.setFirstName("new");
        employeeForUpdate.setLastName("new");
        employeeForUpdate.setGender("FEMALE");

        employeeService.update(employeeForUpdate, employeeBasic.getEmployeeId());

        EmployeeDto updatedEmployee = employeeService.getById(employeeBasic.getEmployeeId());

        Assertions.assertEquals(employeeBasic.getEmployeeId(), updatedEmployee.getEmployeeId());
        Assertions.assertNotEquals(employeeBasic.getDepartmentId(), updatedEmployee.getDepartmentId());
        Assertions.assertNotEquals(employeeBasic.getDateOfBirth(), updatedEmployee.getDateOfBirth());
        Assertions.assertNotEquals(employeeBasic.getGender(), updatedEmployee.getGender());
        Assertions.assertNotEquals(employeeBasic.getFirstName(), updatedEmployee.getFirstName());
        Assertions.assertNotEquals(employeeBasic.getLastName(), updatedEmployee.getLastName());
        Assertions.assertNotEquals(employeeBasic.getJobTitle(), updatedEmployee.getJobTitle());
    }

    @Test
    void delete() {
        List<EmployeeDto> employeeDtoList = employeeService.getAll();
        EmployeeDto newEmployee = employeeService.create(employeeDtoBefore);
        employeeService.delete(newEmployee.getEmployeeId());
        List<EmployeeDto> employeeDtoListActual = employeeService.getAll();

        Assertions.assertEquals(employeeDtoListActual.size(), employeeDtoList.size());
    }
}
