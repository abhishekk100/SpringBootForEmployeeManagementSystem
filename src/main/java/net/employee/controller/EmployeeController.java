package net.employee.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import net.employee.exception.ResourceNotFound;
import net.employee.model.Employee;
import net.employee.repository.EmployeeRepo;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {
	
	//Field dependency Injection
	@Autowired
	public EmployeeRepo employeeRepo;

	//	Constructor Dependency Injection
//	EmployeeController(EmployeeRepo employeeRepo){
//		this.employeeRepo = employeeRepo;
//	}
	
	@GetMapping("/getempl")
	public List<Employee> getAllEmployee(){
		
		return employeeRepo.findAll();
		
	}
	
	//create employee rest api to add employee
	@PostMapping("/getempl")// for post http request which will send Json obj
	public Employee createEmployee(@RequestBody Employee employee) //@RequestBody use to bound JSON object to Employee 
	{
		return  employeeRepo.save(employee);
		
		
	}
	//get employee rest api to get by id
	@GetMapping("/getempl/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
		
		//in .orElseThrow() we need to use lambda expression because it internally uses functional interface
		//functional interface in short which contains only one method 
		//in lambda expression we pass exception message refer class ResourceNotFound in package net.employee.exception
		
		Employee employee =  employeeRepo.findById(id)
		 .orElseThrow(() ->new ResourceNotFound("Employee dose not exist with id: "+id));
		
		return ResponseEntity.ok(employee); //return http request's response employee body to user
	}
	
	@PutMapping("/getempl/{id}")
	 public ResponseEntity<Employee> updateEmployee(@PathVariable Long id,@RequestBody Employee employeeDetails)
	 {
		Employee employee =  employeeRepo.findById(id)
				 .orElseThrow(() ->new ResourceNotFound("Employee dose not exist with id: "+id));
		//we are getting from user and save it to the database and also retuning updated response
				employee.setFname(employeeDetails.getFname());
				employee.setLname(employeeDetails.getLname());
				employee.setEmail(employeeDetails.getEmail());
				
				Employee updatedEmployee = employeeRepo.save(employee);
				
				return ResponseEntity.ok(updatedEmployee); //return http request's response updatedemployee body to user
	 }
	
	//create rest api for delete 
	@DeleteMapping("/getempl/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id){
		
//to check weather given id employee is present or not in the table 
		Employee employee =  employeeRepo.findById(id)
				 .orElseThrow(() ->new ResourceNotFound("Employee dose not exist with id: "+id));
		
		employeeRepo.delete(employee);
		
	//retun response to user with message
		Map<String, Boolean> response = new HashMap<>();
		response.put("Employee Deleted", Boolean.TRUE);
		
		return ResponseEntity.ok(response);
	}
}
