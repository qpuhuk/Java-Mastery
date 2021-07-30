package com.godel.project.javamasteryproject.services;

import com.godel.project.javamasteryproject.controllers.dto.EmployeeDto;
import com.godel.project.javamasteryproject.services.interfaces.IEmployeeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class EmployeeServiceTest {

    @Autowired
    private IEmployeeService employeeService;

    public EmployeeDto createEmployeeDto() {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setJobTitle("113");
        employeeDto.setGender("MALE");
        employeeDto.setDateOfBirth("1990-07-31");
        employeeDto.setDepartmentId(3);
        employeeDto.setLastName("employee");
        employeeDto.setFirstName("employee");
        return employeeDto;
    }

    @Test
    public void createTest() {
        List<EmployeeDto> employeeDtoListStart = employeeService.getAll();
        EmployeeDto employeeTest = employeeService.create(createEmployeeDto());

        List<EmployeeDto> employeeDtoListActual = employeeService.getAll();
        Assertions.assertEquals(employeeDtoListStart.size(), employeeDtoListActual.size() - 1);

        EmployeeDto employeeGotFromDb = employeeService.getById(employeeTest.getEmployeeId());

        Assertions.assertEquals(employeeGotFromDb.getEmployeeId(), employeeTest.getEmployeeId());
        Assertions.assertEquals(employeeGotFromDb.getDepartmentId(), employeeTest.getDepartmentId());
        Assertions.assertEquals(employeeGotFromDb.getDateOfBirth(), employeeTest.getDateOfBirth());
        Assertions.assertEquals(employeeGotFromDb.getGender(), employeeTest.getGender());
        Assertions.assertEquals(employeeGotFromDb.getFirstName(), employeeTest.getFirstName());
        Assertions.assertEquals(employeeGotFromDb.getLastName(), employeeTest.getLastName());
        Assertions.assertEquals(employeeGotFromDb.getJobTitle(), employeeTest.getJobTitle());
    }

    @Test
    public void deleteTest() {
        List<EmployeeDto> employeeDtoList = employeeService.getAll();
        EmployeeDto newEmployee = employeeService.create(createEmployeeDto());
        employeeService.delete(newEmployee.getEmployeeId());
        List<EmployeeDto> employeeDtoListActual = employeeService.getAll();

        Assertions.assertEquals(employeeDtoListActual.size(), employeeDtoList.size());
    }

    @Test
    public void updateTest() {
        EmployeeDto employeeBasic = employeeService.create(createEmployeeDto());

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
}
