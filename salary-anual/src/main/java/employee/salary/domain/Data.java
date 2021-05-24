package employee.salary.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@NoArgsConstructor
public class Data {

	private int id;
	private String employee_name;
	private long employee_salary;
	private int employee_age;
	private String profile_image;
	private long anual_salary;

	public Data(int id, String employee_name, long employee_salary, int employee_age, String profile_image,
			long anual_salary) {
		this.id = id;
		this.employee_name = employee_name;
		this.employee_salary = employee_salary;
		this.employee_age = employee_age;
		this.profile_image = profile_image;
		this.anual_salary = anual_salary;
	}

}
