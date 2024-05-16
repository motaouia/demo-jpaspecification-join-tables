package org.medmota.demojpaspecificationjointables.controllers;

import io.swagger.annotations.ApiOperation;
import org.medmota.demojpaspecificationjointables.entities.Employee;
import org.medmota.demojpaspecificationjointables.repositories.specif2.SearchQuery;
import org.medmota.demojpaspecificationjointables.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @ApiOperation(value = "Employees Filter", notes = "Get employees by search criteria")
    @PostMapping(value = "/employees", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Employee>> getEmployees(@RequestBody SearchQuery searchQuery) {
        List<Employee> listOfEmployees = employeeService.findAll(searchQuery);
        return new ResponseEntity<>(listOfEmployees, HttpStatus.OK);
    }

}
