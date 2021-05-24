package employee.salary.service;

import java.util.List;

import employee.salary.domain.Employee;

public interface SalaryService {

	public List<Employee> findAll();
	
	public List<Employee> findById(Long id);
	
}
