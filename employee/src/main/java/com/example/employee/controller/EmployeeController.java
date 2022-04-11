package com.example.employee.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.employee.model.Employee;
import static com.example.employee.model.Employee.getEmployees;


@RestController
public class EmployeeController{
	
	public static final List<Employee> employeeData = getEmployees();
	
	@GetMapping("/getEmployeesStatus")
	public ResponseEntity<Object> getEmployeesStatus(){
		return ResponseEntity.ok(employeeData);
	}
	
	@PutMapping("/updateWorkandVacationDays/{employeeId}")
	public ResponseEntity<Object> updateWork(@PathVariable Integer employeeId, @RequestParam int workedDays){
		Optional<Employee> employee= getEmployeeById(employeeId);
		employee.ifPresent(emp->emp.work(workedDays));
		return ResponseEntity.ok(employee.isPresent()?employee.get():"Employee Not Found");
	}
	
	@PutMapping("/updateAccumulatedVacationDays/{employeeId}")
	public ResponseEntity<Object> updateTakeVacation(@PathVariable Integer employeeId, @RequestParam float vacationDays){
		Optional<Employee> employee= getEmployeeById(employeeId);
		employee.ifPresent(emp->emp.takeVacation(vacationDays));
		return ResponseEntity.ok(employee.isPresent()?employee.get():"Employee Not Found");
	}
	
	private Optional<Employee> getEmployeeById(Integer employeeId){
		return employeeData.stream().filter(emp->emp.getEmployeeId().equals(employeeId)).findFirst();
	}

}
