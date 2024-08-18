package com.employee.employee.Service;

import com.employee.employee.Exception.EmployeeNotFoundException;
import com.employee.employee.Repository.EmployeeRepository;
import com.employee.employee.Repository.RetirementPlanRepository;
import com.employee.employee.dto.EmployeeResponseDto;
import com.employee.employee.dto.RetirementPlanRequestDTO;
import com.employee.employee.dto.RetirementPlanResponseDTO;
import com.employee.employee.model.Employee;
import com.employee.employee.model.RetirementPlan;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService implements EmpServiceImp {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private RetirementPlanRepository retirementPlanRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public List<EmployeeResponseDto> getAllEmployees() {
        List<Employee> employees= employeeRepository.findAllByOrderByLastNameAsc();
        return employees.stream().map(employee -> {
                    EmployeeResponseDto dto= modelMapper.map(employee, EmployeeResponseDto.class);
                    List<RetirementPlanResponseDTO> plans = retirementPlanRepository.findByEmployee(employee)
                            .stream().map(plan -> modelMapper.map(plan, RetirementPlanResponseDTO.class))
                            .collect(Collectors.toList());
                    dto.setRetirementPlans(plans);
                    return dto;
        })
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeResponseDto getEmployeeWithRetirementPlan(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(()-> new EmployeeNotFoundException("Employee with Id " + employeeId + "Not found"));
        EmployeeResponseDto dto = modelMapper.map(employee,EmployeeResponseDto.class);
        List<RetirementPlanResponseDTO> plans = retirementPlanRepository.findByEmployee(employee)
                .stream().map(plan -> modelMapper.map(plan, RetirementPlanResponseDTO.class))
                .collect(Collectors.toList());
        dto.setRetirementPlans(plans);
        return dto;
    }



    @Override
    public List<EmployeeResponseDto> getMonthlyUpcomingRetirees() {
        LocalDate today = LocalDate.now();
        LocalDate startOfNextMonth = today.withDayOfMonth(1).plusMonths(1);
        LocalDate endDayOfNextMonth = startOfNextMonth.withDayOfMonth(startOfNextMonth.lengthOfMonth());

        List<RetirementPlan> plans = retirementPlanRepository.findByRetirementDateBetween(startOfNextMonth,endDayOfNextMonth);
        return plans.stream().map(plan->{
            Employee employee = plan.getEmployee();
            EmployeeResponseDto dto = modelMapper.map(employee,EmployeeResponseDto.class);
            List<RetirementPlanResponseDTO> planDtos = plans.stream()
                    .map(p -> modelMapper.map(p, RetirementPlanResponseDTO.class))
                    .collect(Collectors.toList());
            dto.setRetirementPlans(planDtos);
            return dto;
        })
                .sorted((e1,e2)-> e1.getRetirementPlans().get(0).getRetirementDate().compareTo(e2.getRetirementPlans().get(0).getRetirementDate()))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void addRetirementPlanForEmployee(RetirementPlanRequestDTO request) {
        // Ensure that the DTO is properly populated
        if (request == null || request.getFirstName() == null || request.getLastName() == null) {
            throw new EmployeeNotFoundException("Request or required fields cannot be null");
        }

        // Find or create employee
        Employee employee = employeeRepository.findByFirstNameAndLastName(request.getFirstName(), request.getLastName())
                .orElseGet(() -> createEmployee(request));

        // Map DTO to RetirementPlan entity
        RetirementPlan plan = modelMapper.map(request, RetirementPlan.class);
        plan.setEmployee(employee);

        // Save RetirementPlan
        retirementPlanRepository.save(plan);
    }
    private Employee createEmployee(RetirementPlanRequestDTO request) {
        Employee newEmployee = new Employee();
        newEmployee.setFirstName(request.getFirstName());
        newEmployee.setLastName(request.getLastName());
        newEmployee.setYearlySalary(request.getYearlySalary());
        return employeeRepository.save(newEmployee);
    }
}
