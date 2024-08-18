package com.employee.employee.modelMapper;

import com.employee.employee.dto.EmployeeResponseDto;
import com.employee.employee.model.Employee;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.typeMap(Employee.class, EmployeeResponseDto.class)
                .addMapping(Employee::getFirstName, EmployeeResponseDto::setFirstName)
                .addMapping(Employee::getLastName, EmployeeResponseDto::setLastName);

        return modelMapper;
    }

}
