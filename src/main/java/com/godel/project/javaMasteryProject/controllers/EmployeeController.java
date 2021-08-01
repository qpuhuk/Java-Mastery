package com.godel.project.javaMasteryProject.controllers;

import com.godel.project.javaMasteryProject.controllers.dto.EmployeeDto;
import com.godel.project.javaMasteryProject.services.interfaces.IEmployeeService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static javax.servlet.http.HttpServletResponse.*;

@Api(tags = "Employee API")
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private IEmployeeService employeeService;

    @Autowired
    public EmployeeController(IEmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @ApiOperation(value = "show employee by id")
    @GetMapping("/{id}")
    @ApiResponses(value = {@ApiResponse(code = SC_OK, message = "ok")})
    public ResponseEntity<EmployeeDto> getById(@PathVariable @ApiParam (value = "id employee") long id) {
        return new ResponseEntity<>(employeeService.getById(id), HttpStatus.OK);
    }

    @ApiOperation(value = "show all employees")
    @GetMapping
    @ApiResponses(value = {@ApiResponse(code = SC_OK, message = "ok")})
    public ResponseEntity<List<EmployeeDto>> getAll() {
        return new ResponseEntity<>(employeeService.getAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "create employee")
    @PostMapping
    @ApiResponses(value = {@ApiResponse(code = SC_CREATED, message = "created")})
    public ResponseEntity<EmployeeDto> create(@RequestBody @ApiParam(value = "json body") EmployeeDto employeeDto) {
        return new ResponseEntity<>(employeeService.create(employeeDto), HttpStatus.CREATED);
    }

    @ApiOperation(value = "update employee")
    @PutMapping("/{id}")
    @ApiResponses(value = {@ApiResponse(code = SC_OK, message = "updated")})
    public ResponseEntity<EmployeeDto> update(@RequestBody @ApiParam(value = "json update body")EmployeeDto employeeDto,
                                              @PathVariable @ApiParam(value = "old employee ID")long id) {
        return new ResponseEntity<>(employeeService.update(employeeDto, id), HttpStatus.OK);
    }

    @ApiOperation(value = "delete employee")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiResponses(value = {@ApiResponse(code = SC_NO_CONTENT, message = "deleted")})
    public void delete(@PathVariable @ApiParam(value = "employee ID") long id) {
        employeeService.delete(id);
    }
}