package employee.salary.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import employee.salary.domain.Employee;
import employee.salary.service.SalaryService;

@RestController
public class SalaryController {

	private final SalaryService service;

	@Autowired
	SalaryController(final SalaryService service) {
		this.service = service;
	}

	@GetMapping("/empleado")
	public ResponseEntity<List<Employee>> findById(@RequestParam Long id) {
		return ResponseEntity.ok(service.findById(id));
	}


}
