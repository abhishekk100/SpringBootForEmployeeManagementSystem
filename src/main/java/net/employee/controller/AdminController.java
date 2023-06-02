package net.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.employee.exception.ResourceNotFound;
import net.employee.model.Admin;
import net.employee.repository.AdminRepository;

@CrossOrigin(value = "http://localhost:4200/")
@RestController
@RequestMapping("admin")
public class AdminController {

	@Autowired
	AdminRepository adminRepo;
	
	@PostMapping("/save")
	public Admin addAdmin(@RequestBody Admin admin) {
		return adminRepo.save(admin);
	}
	
	@GetMapping("/getAll")
	public List<Admin> getAllAdmin(){
		
		return adminRepo.findAll();
		
	}
	//get admin rest api to get by id
	@GetMapping("{id}")
	public ResponseEntity<Admin> getAdminById(@PathVariable int id) {
		
		//in .orElseThrow() we need to use lambda expression because it internally uses functional interface
		//functional interface in short which contains only one method 
		//in lambda expression we pass exception message refer class ResourceNotFound in package net.employee.exception
		
		Admin admin =  adminRepo.findById(id)
		 .orElseThrow(() ->new ResourceNotFound("Admin dose not exist with id: "+id));
		return ResponseEntity.ok(admin); //return if 200ok http request's response employee body to user
	}
	
}
