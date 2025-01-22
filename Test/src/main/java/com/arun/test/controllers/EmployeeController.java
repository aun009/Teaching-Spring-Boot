package com.arun.test.controllers;

import com.arun.test.dto.EmployeeDTO;
import com.arun.test.entity.Employee;
import com.arun.test.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    @Autowired
    private final ModelMapper modelMapper;
    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeController(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @GetMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployeeById(@PathVariable Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow();
        return modelMapper.map(employee, EmployeeDTO.class);
    }



}
