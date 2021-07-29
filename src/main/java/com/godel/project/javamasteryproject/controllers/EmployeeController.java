package com.godel.project.javamasteryproject.controllers;

import com.godel.project.javamasteryproject.controllers.dto.EmployeeDto;
import com.godel.project.javamasteryproject.services.interfaces.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.time.DateTimeException;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(path = "/")
public class EmployeeController {

    private final IEmployeeService employeeService;

    @Autowired
    public EmployeeController(IEmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/employee/{id}")
    public ResponseEntity<EmployeeDto> getById(@PathVariable long id) {
        return new ResponseEntity<>(employeeService.getById(id), HttpStatus.OK);
    }

    @GetMapping(path = "/employees")
    public ResponseEntity<List<EmployeeDto>> getAll() {
        return new ResponseEntity<>(employeeService.getAll(), HttpStatus.OK);
    }

    @PostMapping(path = "/employee")
    public ResponseEntity<EmployeeDto> create(@RequestBody EmployeeDto employeeDto) {
        return new ResponseEntity<>(employeeService.create(employeeDto), HttpStatus.CREATED);
    }

    @PutMapping(path = "/employee/{id}")
    public ResponseEntity<EmployeeDto> update(@RequestBody EmployeeDto employeeDto, @PathVariable long id) {
        return new ResponseEntity<>(employeeService.update(employeeDto, id), HttpStatus.OK);
    }

    @DeleteMapping(path = "/employee/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        return new ResponseEntity<>(employeeService.delete(id), HttpStatus.OK);
    }

    /**
     * Exception handlers
     */

    @ExceptionHandler
    public ResponseEntity<String> missingEmployeeId(NoSuchElementException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ClassUtils.getShortName(ex.getClass()) + "\n" +
                ex.getLocalizedMessage() + "\nMessage : no such an employee with such an id");
    }

    @ExceptionHandler
    public ResponseEntity<String> illegalArgs(HttpMessageNotReadableException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ClassUtils.getShortName(ex.getClass()) + "\n" +
                ex.getLocalizedMessage() + "\nMessage : illegal arguments");
    }

    @ExceptionHandler
    public ResponseEntity<String> dateTimeException(DateTimeException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ClassUtils.getShortName(ex.getClass()) + "\n" +
                ex.getLocalizedMessage() + "\nMessage : incorrect date format");
    }

    @ExceptionHandler
    public ResponseEntity<String> illegalEnumValue(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ClassUtils.getShortName(ex.getClass()) + "\n" +
                ex.getLocalizedMessage() + "\nMessage : such value of enum not exist");
    }

    @ExceptionHandler
    public ResponseEntity<String> missingEmployees(EmptyResultDataAccessException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ClassUtils.getShortName(ex.getClass()) + "\n" +
                ex.getLocalizedMessage() + "\nMessage : list of employees is empty");
    }

    @ExceptionHandler
    public ResponseEntity<String> nullPointerException(NullPointerException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ClassUtils.getShortName(ex.getClass()) + "\n" +
                ex.getLocalizedMessage() + "\nMessage : some of the not null fields is empty");
    }

    @ExceptionHandler
    public ResponseEntity<String> SQLProblems(SQLException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ClassUtils.getShortName(ex.getClass()) + "\n" +
                ex.getLocalizedMessage() + "\nMessage : something went wrong");
    }
}