package io.red.relatoriospoc.repositories;

import io.red.relatoriospoc.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
