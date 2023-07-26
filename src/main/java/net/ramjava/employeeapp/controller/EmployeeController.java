package net.ramjava.employeeapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import net.ramjava.employeeapp.response.EmployeeResponse;
import net.ramjava.employeeapp.service.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	EmployeeService empService;
	
	@GetMapping("/employees/{id}")
	ResponseEntity<EmployeeResponse> getEmployeeDetails(@PathVariable("id") int id) {
		//Employee emp = empService.getEmployeeById(id);
		EmployeeResponse empResponse = empService.getEmployeeById(id);
 		return ResponseEntity.status(HttpStatus.OK).body(empResponse);
	}
}
