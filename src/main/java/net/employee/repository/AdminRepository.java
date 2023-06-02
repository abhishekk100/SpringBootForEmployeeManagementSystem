package net.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.employee.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer> {

	
}
