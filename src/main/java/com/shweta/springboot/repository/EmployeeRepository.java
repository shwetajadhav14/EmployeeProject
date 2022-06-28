package com.shweta.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.shweta.springboot.model.Employee;

@org.springframework.stereotype.Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	@Query(value = "select * from emp where id = :id",nativeQuery = true)
    List<Employee> getUserDetails(long id);
}
