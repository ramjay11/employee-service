package net.ramjava.employeeapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import net.ramjava.employeeapp.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
