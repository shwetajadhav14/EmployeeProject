package com.shweta.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
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

import com.shweta.springboot.model.Employee;
import com.shweta.springboot.repository.EmployeeRepository;



@RestController
@RequestMapping("/api/v1/")
@CrossOrigin
public class EmployeeController {

	@Autowired
	private EmployeeRepository empRepo;
	
	Employee employee = new Employee();
	
	//get Employee
		@GetMapping("/emp")
		public List<Employee> getAllEmployee(){
			return empRepo.findAll();		
		}
		//create employee rest api'
		@PostMapping("/emp")
		public Employee createEmployee(@RequestBody Employee employee) {
			return empRepo.save(employee);
		}
		
//		//get employee by id
//		@GetMapping("/emp/{id}")
//		public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
//			Employee employee=empRepo.findById(id)
//					.orElse(()->new ResourceNotFoundException("Employee Not exixt with id:"+id));
//					
//					
//			return ResponseEntity.ok(employee);
//		}
//		
		@GetMapping("/emp/{id}")
	    public List<Employee> getAllUsers(@PathVariable long id){
	        return empRepo.getUserDetails(id);
	    }
		
		@PutMapping("/emp/{id}")
		public ResponseEntity<Employee> updateEmployee(@PathVariable long id,@RequestBody Employee employeeDetails){
//			 this.employee= (Employee) this.getAllUsers(id);
//			 this.getAllUsers(id);
			Employee employee=empRepo.findById(id).get();
			 employee.setFirstName(employeeDetails.getFirstName());
			 employee.setLastName(employeeDetails.getLastName());
			 employee.setEmailId(employeeDetails.getEmailId());
			 
			 Employee updateRepo=empRepo.save(employee);
			 return ResponseEntity.ok(updateRepo);
			 
		}
		
		//delete employee 
		@DeleteMapping("/emp/{id}")
		public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable long id){
			Employee employee=empRepo.findById(id).get();
			 empRepo.delete(employee);
			 Map<String, Boolean> response=new HashMap<>();
			 response.put("deleted", Boolean.TRUE);
			 
			 return ResponseEntity.ok(response);
		}
		
		
}

