package net.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import net.employee.model.Employee;

@Service
public interface EmployeeRepo extends JpaRepository<Employee, Long> {

}
