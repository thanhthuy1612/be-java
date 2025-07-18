package com.mint.java_sql.mapper;

import com.mint.java_sql.base.mapper.BaseMapper;
import com.mint.java_sql.dto.EmployeeDto;
import com.mint.java_sql.entity.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper extends BaseMapper<Employee, EmployeeDto> {
    @Override
    public EmployeeDto mapToDto(Employee employee) {
        return new EmployeeDto(
                employee.getId(),
                employee.getUsername(),
                employee.getEmail(),
                employee.getPassword(),
                employee.getRole()
        );
    }

    @Override
    public Employee mapToEntity(EmployeeDto employee) {
        return new Employee(
                employee.getId(),
                employee.getUsername(),
                employee.getEmail(),
                employee.getPassword(),
                employee.getRole()
        );
    }
}
