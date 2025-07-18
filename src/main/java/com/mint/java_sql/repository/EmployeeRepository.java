package com.mint.java_sql.repository;

import com.mint.java_sql.base.repository.BaseRepository;
import com.mint.java_sql.entity.Employee;

import java.util.Optional;

public interface EmployeeRepository extends BaseRepository<Employee> {
    Optional<Employee> findByUsername(String username);

    Boolean existsByUsername(String username);
}
