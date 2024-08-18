package com.employee.employee.Service;

import com.employee.employee.dto.EmployeeResponseDto;
import com.employee.employee.dto.RetirementPlanRequestDTO;

import java.util.List;

public interface EmpServiceImp {
    List<EmployeeResponseDto> getAllEmployees();
    EmployeeResponseDto getEmployeeWithRetirementPlan(Long employeeId);
    List<EmployeeResponseDto> getMonthlyUpcomingRetirees();

    void addRetirementPlanForEmployee(RetirementPlanRequestDTO request);



}
