package com.mint.java_sql.service.impl;

import com.mint.java_sql.base.service.impl.BaseServiceImpl;
import com.mint.java_sql.dto.EmployeeDto;
import com.mint.java_sql.entity.Employee;
import com.mint.java_sql.mapper.EmployeeMapper;
import com.mint.java_sql.repository.EmployeeRepository;
import com.mint.java_sql.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl extends BaseServiceImpl<Employee, EmployeeDto, EmployeeRepository, EmployeeMapper> implements EmployeeService {
    public EmployeeRepository employeeRepository;
    public EmployeeMapper employeeMapper;

    @Override
    public EmployeeRepository repository() {
        return employeeRepository;
    }

    @Override
    public EmployeeMapper mapper() {
        return employeeMapper;
    }

    @Override
    public EmployeeDto create(EmployeeDto employeeDto) {

        Employee employee = employeeMapper.mapToEntity(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);

        return employeeMapper.mapToDto(savedEmployee);
    }

//    @Override
//    public EmployeeDto getEmployeeById(Long employeeId) {
//        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("Employee is not exits with given id: " + employeeId));
//        return EmployeeMapper.mapToEmployeeDto(employee);
//    }
//
//    @Override
//    public List<EmployeeDto> getAllEmployees() {
//        List<Employee> employees = employeeRepository.findAll();
////        return employees.stream().map((employee) -> EmployeeMapper.mapToEmployeeDto(employee)).collect(Collectors.toList());
//        return employees.stream().map(EmployeeMapper::mapToEmployeeDto).collect(Collectors.toList());
//    }
//
//    @Override
//    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updateEmployee) {
//        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("Employee is not exits with given id: " + employeeId));
//        employee.setFirstName(updateEmployee.getFirstName());
//        employee.setLastName(updateEmployee.getLastName());
//        employee.setEmail(updateEmployee.getEmail());
//
//        Employee updatedEmployee = employeeRepository.save(employee);
//        return EmployeeMapper.mapToEmployeeDto(updatedEmployee);
//    }
//
//    @Override
//    public void deleteEmployee(Long employeeId) {
//        employeeRepository.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("Employee is not exits with given id: " + employeeId));
//        employeeRepository.deleteById(employeeId);
//    }

}
