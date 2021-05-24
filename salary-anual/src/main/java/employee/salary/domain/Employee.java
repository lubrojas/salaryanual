package employee.salary.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@NoArgsConstructor
public class Employee {

	private String status;
	private Data data;
	private String message;
	private boolean correct;

	public Employee(String status, Data data, String message, boolean correct) {
		this.status = status;
		this.data = data;
		this.message = message;
		this.correct = correct;
		
	}

}
