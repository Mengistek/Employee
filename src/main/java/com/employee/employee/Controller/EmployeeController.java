package com.employee.employee.Controller;

import com.employee.employee.Service.EmployeeService;
import com.employee.employee.dto.EmployeeResponseDto;
import com.employee.employee.dto.RetirementPlanRequestDTO;
import jakarta.validation.Valid;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@NoArgsConstructor
@RequestMapping("/api/v1/employees")
@Validated
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;



    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<EmployeeResponseDto> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{employeeId}")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeResponseDto getEmployeeWithRetirementPlan(@PathVariable Long employeeId){
        return employeeService.getEmployeeWithRetirementPlan(employeeId);
    }

    @GetMapping("/monthly-retirees")
    @ResponseStatus(HttpStatus.OK)
    public List<EmployeeResponseDto> getMonthlyUpcomingRetirees(){
       return employeeService.getMonthlyUpcomingRetirees();
    }

    @PostMapping("/add-retirement-plan")
    @ResponseStatus(HttpStatus.CREATED)
    public void addRetirementPlan(@Valid @RequestBody RetirementPlanRequestDTO request){
        System.out.println("Received DTO: " + request);
      employeeService.addRetirementPlanForEmployee(request);



    }

}
