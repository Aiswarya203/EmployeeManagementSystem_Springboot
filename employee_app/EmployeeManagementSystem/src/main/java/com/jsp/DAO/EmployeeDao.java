package com.jsp.DAO;

import java.util.List; 

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.DTO.Employee;


@Repository
public class EmployeeDao {
	
	@Autowired
	EntityManager manager;
	@Autowired
	EntityTransaction transaction;
	
	// to save employee details
	public void saveEmployee (Employee employee) {
		transaction.begin();
		manager.persist(employee);
		transaction.commit();
		
	}
	public Employee getEmployeeId (int id) {
		Employee e = manager.find(Employee.class,id);
		return e;
		
	}
	// to delete the employee details
	public void deleteEmployee(int id) {
		Employee e = manager.find(Employee.class,id);
		if(e!=null) {
			transaction.begin();
			manager.remove(e);
			transaction.commit();
		}
	}
	
	// to update employee details
	public void updateEmployee(Employee updatedEmployee) {
		transaction.begin();
		manager.merge(updatedEmployee);
		transaction.commit();
	}
	//to get all employee details
	public List<Employee> getAllEmployee(){
		Query q = manager.createQuery("select e from Employee e");
		List<Employee> employee = q.getResultList();
		return employee;
		
	}
	
}
