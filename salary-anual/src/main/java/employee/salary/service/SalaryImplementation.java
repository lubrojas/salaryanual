package employee.salary.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import employee.salary.domain.Data;
import employee.salary.domain.Employee;

@Service
public class SalaryImplementation implements SalaryService {

	@Value("${resource.tasks}/{id}")
	private String idResource;

	private RestTemplate restTemplate;

	@Autowired
	public SalaryImplementation(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	/**
	 * Metodo que consulta el servicio http://dummy.restapiexample.com/api/v1/employees para
	 * traer todos los clientes
	 */
	public List<Employee> findAll() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add("user-agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36");
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		ResponseEntity<List<Employee>> response = restTemplate.exchange(
				"http://dummy.restapiexample.com/api/v1/employees", HttpMethod.GET, entity,
				new ParameterizedTypeReference<List<Employee>>() {
				});
//		ResponseEntity<Resource<List<Employee>>> response = restTemplate.exchange("http://dummy.restapiexample.com/api/v1/employees",
//				HttpMethod.GET, entity, new ParameterizedTypeReference<Resource<List<Employee>>>() {
//				});
		List<Employee> empleados = response.getBody();
		return empleados;
	}

	/**
	 * Metodo que consulta el servicio http://dummy.restapiexample.com/api/v1/employee/{id} para obtener
	 * la info del Employee por medio de un resource Value
	 */
	public List<Employee> findById(Long id) {
		// TODO Auto-generated method stub
		Employee employee = null;
		List<Employee> employees = new ArrayList<Employee>();
		HttpHeaders requestHeaders = new HttpHeaders();
		setearHeaders(requestHeaders);
		HttpEntity<Employee> requestEntity = new HttpEntity<>(requestHeaders);
		if (Objects.nonNull(id)) {
			ResponseEntity<Employee> resultado = restTemplate.exchange(idResource, HttpMethod.GET, requestEntity,
					Employee.class, id);
			if (resultado.getStatusCode() == HttpStatus.OK) {
				if (Objects.nonNull(resultado.getBody())) {
					employee = resultado.getBody();
					calculateAnualSalary(employee);
					employees.add(employee);
				}
			}
		} else {
			// Temporalmente se genera una lista mockeada de Empleados
			queryEmployees(employees);
		}
		return employees;
	}

	/**
	 * Metodo que setea los headers necesarios para consumir los servicios
	 * @param requestHeaders
	 */
	private void setearHeaders(HttpHeaders requestHeaders) {
		requestHeaders.add("user-agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36");
		requestHeaders.add("Accept", MediaType.APPLICATION_JSON_VALUE);
	}

	/**
	 * Metodo que calcula el salario anual del employee
	 * @param employee
	 */
	private void calculateAnualSalary(Employee employee) {
		if (Objects.nonNull(employee.getData())) {
			employee.getData().setAnual_salary(employee.getData().getEmployee_salary() * 12);
			employee.setCorrect(true);
		} else {
			employee.setCorrect(false);
		}
	}

	/**
	 * Mock que genera una lista temporal de employees cuando no se realiza la consulta por ids
	 * @param employees
	 */
	private void queryEmployees(List<Employee> employees) {
		int edad = 60;
		long salary = 1000;
		for (int i = 40; i < 50; i++) {
			Employee employee1 = new Employee();
			Data data1 = new Data();
			data1.setId(i);
			data1.setEmployee_name("Employee " + i);
			data1.setEmployee_age(edad);
			data1.setEmployee_salary(salary);
			employee1.setData(data1);
			calculateAnualSalary(employee1);
			employees.add(employee1);
			edad = edad + 1;
			salary = salary + 100;
		}

	}

}
