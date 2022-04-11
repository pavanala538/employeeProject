package com.example.employee.model;

import static com.example.employee.model.Constants.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.employee.exception.EmployeeExceptions;

public class Employee {

	private Integer employeeId;
	private EmployeeType employeeType;
	private float noOfVacationDays;
	private int employeeWorkedDays;


	public Employee(Integer employeeId, EmployeeType employeeType) {
		super();
		this.employeeType = employeeType;
		this.employeeId = employeeId;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public EmployeeType getEmployeeType() {
		return employeeType;
	}

	public float getNoOfVacationDays() {
		return noOfVacationDays;
	}

	public int getEmployeeWorkedDays() {
		return employeeWorkedDays;
	}
	
	public void work(int noOfDaysWorked) {
		if (noOfDaysWorked >= 0 && noOfDaysWorked <= MAX_WORKDAYS_PER_YEAR) {
			noOfVacationDays = noOfDaysWorked*(employeesVacationLimit().get(this.employeeType)/MAX_WORKDAYS_PER_YEAR);
			employeeWorkedDays = noOfDaysWorked;
		}
		else {
			throw new EmployeeExceptions("Number of days worked '"+noOfDaysWorked+"' is not valid");
		}

	}

	public void takeVacation(float usedVacationDays) {
		if (usedVacationDays >= 0 && usedVacationDays <= noOfVacationDays && noOfVacationDays <= employeesVacationLimit().get(this.employeeType)) { // vacation days cannot be negative
			noOfVacationDays -= usedVacationDays;
		
		} else {
			throw new EmployeeExceptions("Number of vacation days '"+usedVacationDays+"' exceeded employees available vacation balance '"+noOfVacationDays+"'");
		}
	}
	
	public Map<EmployeeType,Float> employeesVacationLimit() {
		Map<EmployeeType,Float> vacationDaysLimit = new HashMap<>();
		vacationDaysLimit.put(EmployeeType.HOURLY, HOURLY_EMPLOYEES_MAX_VACATION_DAYS_PER_YEAR);
		vacationDaysLimit.put(EmployeeType.SALARIED, SALARIED_EMPLOYEES_MAX_VACATION_DAYS_PER_YEAR);
		vacationDaysLimit.put(EmployeeType.MANAGER, MANAGERS_MAX_VACATION_DAYS_PER_YEAR);
		
		return vacationDaysLimit;
	
	}

	public enum EmployeeType {
		HOURLY, SALARIED, MANAGER
	}
	
	public static List<Employee> getEmployees() {
		return Arrays.asList(
				new Employee( 100, Employee.EmployeeType.HOURLY), 
				new Employee( 101, Employee.EmployeeType.HOURLY),
				new Employee( 102, Employee.EmployeeType.HOURLY), 
				new Employee( 103, Employee.EmployeeType.HOURLY), 
				new Employee( 104, Employee.EmployeeType.HOURLY), 
				new Employee( 105, Employee.EmployeeType.HOURLY), 
				new Employee( 106, Employee.EmployeeType.HOURLY), 
				new Employee( 107, Employee.EmployeeType.HOURLY), 
				new Employee( 108, Employee.EmployeeType.HOURLY),
				new Employee( 109, Employee.EmployeeType.HOURLY),
				new Employee( 200, Employee.EmployeeType.SALARIED),
				new Employee( 201, Employee.EmployeeType.SALARIED),
				new Employee( 202, Employee.EmployeeType.SALARIED),
				new Employee( 203, Employee.EmployeeType.SALARIED),
				new Employee( 204, Employee.EmployeeType.SALARIED),
				new Employee( 205, Employee.EmployeeType.SALARIED),
				new Employee( 206, Employee.EmployeeType.SALARIED),
				new Employee( 207, Employee.EmployeeType.SALARIED),
				new Employee( 208, Employee.EmployeeType.SALARIED),
				new Employee( 209, Employee.EmployeeType.SALARIED),
				new Employee( 300, Employee.EmployeeType.MANAGER),
				new Employee( 301, Employee.EmployeeType.MANAGER),
				new Employee( 302, Employee.EmployeeType.MANAGER),
				new Employee( 303, Employee.EmployeeType.MANAGER),
				new Employee( 304, Employee.EmployeeType.MANAGER),
				new Employee( 305, Employee.EmployeeType.MANAGER),
				new Employee( 306, Employee.EmployeeType.MANAGER),
				new Employee( 307, Employee.EmployeeType.MANAGER),
				new Employee( 308, Employee.EmployeeType.MANAGER),
				new Employee( 309, Employee.EmployeeType.MANAGER));		
	}
	
	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", employeeType=" + employeeType + ", noOfVacationDays="
				+ noOfVacationDays + ", employeeWorkedDays=" + employeeWorkedDays + "]";
	}
}
