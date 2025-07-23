package com.mint.java_sql.controller;

import com.mint.java_sql.base.controller.BaseController;
import com.mint.java_sql.dto.request.EmployeeDto;
import com.mint.java_sql.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


@AllArgsConstructor
@RestController
@RequestMapping("/api/employees")
public class EmployeeController extends BaseController<EmployeeDto, EmployeeService> {
    public EmployeeService employeeService;

    @Override
    public EmployeeService service() {
        return employeeService;
    }

//
//    // Build Get All Employees REST API
//    @GetMapping
//    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
//        List<EmployeeDto> employeesDto = employeeService.getAllEmployees();
//        return ResponseEntity.ok(employeesDto);
//    }
//
//    // Build Get Employee REST API
//    @PutMapping("{id}")
//    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") Long employeeId, @RequestBody EmployeeDto updateEmployee) {
//        EmployeeDto employeeDto = employeeService.updateEmployee(employeeId, updateEmployee);
//        return ResponseEntity.ok(employeeDto);
//    }
//
//    // Build Get Employee REST API
//    @DeleteMapping("{id}")
//    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long employeeId) {
//        employeeService.deleteEmployee(employeeId);
//        return ResponseEntity.ok("Employee deleted successfully!");
//    }
}
