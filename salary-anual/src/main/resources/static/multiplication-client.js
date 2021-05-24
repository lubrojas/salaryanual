function queryUser(user) {
	$.ajax({
		async: false,
		url: "http://localhost:8086/empleado?id=" + user,
		success: function(data) {
			if (data[0].correct) {
				$('#results-div').show();
				$('#results-body').empty();
				$('.result-message').empty();
				data.forEach(function(row) {
					$('#results-body').append('<tr><td>' + row.data.id + '</td>' +
						'<td>' + row.data.employee_name + '</td>' +
						'<td>' + row.data.employee_salary + '</td>' +
						'<td>' + row.data.employee_age + '</td>' +
						'<td>' + row.data.anual_salary + '</td></tr>');
				});
			}else{
				$('#results-div').hide();
				$('.result-message').empty()
                        .append("<p class='bg-danger text-center'>The employee is not found!</p>");
			}
		}
	});
}

$(document).ready(function() {


	$("#attempt-form").submit(function(event) {

		event.preventDefault();

		var $form = $(this),
			user = $form.find("input[name='id-employee']").val();

		queryUser(user);
	});
});
