package employee.salary.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import employee.salary.controller.SalaryController;
import employee.salary.domain.Data;
import employee.salary.domain.Employee;
import employee.salary.service.SalaryService;

@RunWith(SpringRunner.class)
@WebMvcTest(SalaryController.class)
public class EmployeeServiceTest {

	@MockBean
	private SalaryService salaryService;

	@Autowired
	private MockMvc mvc;

	private JacksonTester<List<Employee>> json;

	@Before
	public void setup() {
		JacksonTester.initFields(this, new ObjectMapper());
	}

	@Test
	public void getEmployeeByIdTest() throws Exception {

		List<Employee> employees = new ArrayList<Employee>();
		Employee employee = new Employee("success", new Data(2, "Garrett Winters", 170750, 63, "", 2049000),
				"Successfully! Record has been fetched.", true);
		employees.add(employee);
		given(salaryService.findById(2L)).willReturn(employees);

		// when
		MockHttpServletResponse response = mvc.perform(get("/empleado?id=2").accept(MediaType.APPLICATION_JSON))
				.andReturn().getResponse();

		// then
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		assertThat(response.getContentAsString()).isEqualTo(json.write(employees).getJson());
	}

}
