package com.employee.employee.Service;

import com.employee.employee.model.Employee;
import com.employee.employee.model.RetirementPlan;

import java.util.List;

public interface EmpServiceImp {
    List<Employee> getAllEmployees();
    Employee getEmployeeById(Long employeeId);
    List<RetirementPlan> getMonthlyUpcomingRetirees();

    void addRetirementPlan(Employee employee, RetirementPlan retirementPlan);



}
