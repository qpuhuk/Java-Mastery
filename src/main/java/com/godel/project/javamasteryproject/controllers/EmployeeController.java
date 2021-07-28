package com.godel.project.javamasteryproject.controllers;


import com.godel.project.javamasteryproject.controllers.dto.EmployeeDto;
import com.godel.project.javamasteryproject.services.interfaces.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return new ResponseEntity<>(employeeService.update(employeeDto,id),HttpStatus.OK);
    }

    @DeleteMapping(path = "/employee/{id}")
    public String delete(@PathVariable long id){
        return employeeService.delete(id);
    }
}