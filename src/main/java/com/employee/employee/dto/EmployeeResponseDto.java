package com.employee.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponseDto {
    private Long employeeId;
    private String firstName;
    private String lastName;
    private Double yearlySalary;
    private List<RetirementPlanResponseDTO> retirementPlans;
}
