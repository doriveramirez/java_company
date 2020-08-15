$(document).ready(function() {

	var userListTable = $('#userListTable').DataTable({
		ajax: {
			url: "/api/users",
			dataSrc: ''
		},
		"columns": [
			{
				"data": "id"
			},
			{
				"data": "username"
			}, {
				"data": "password"
			}, {
				"data": "roles[0].name"
			},
			{
				"defaultContent": "<div class='text-center'><div class='btn-group'><button class='btn btn-primary btn-sm btnEdit'><i class='material-icons'>edit</i></button><button class='btn btn-danger btn-sm btnDelete'><i class='material-icons'>delete</i></button></div></div>"
			}]
	});

	var row;

	$('#formUsers').submit(function(e) {
		e.preventDefault();
		id = $.trim($('#id').val());
		username = $.trim($('#username').val());
		password = $.trim($('#password').val());
		var data = {
			id: id,
			username: username,
			password: password
		};
		if (id != "") {
			$.ajax({
				headers: {
					'Content-Type': 'application/json'
				},
				url: "api/users/" +   id,
				type: "PUT",
				datatype: "json",
				data: JSON.stringify(data),
				success: function(data) {
					userListTable.ajax.reload(null, false);
				}
			});
		} else {
			$.ajax({
				headers: {
					'Content-Type': 'application/json'
				},
				url: "api/users/",
				type: "POST",
				datatype: "json",
				data: JSON.stringify(data),
				success: function(data) {
					userListTable.ajax.reload(null, false);
				}
			});
		}
		$('#modalCRUD').modal('hide');
	});

	$("#btnNew").click(function() {
		$("#formUsers").trigger("reset");
		$(".modal-header").css("background-color", "#17a2b8");
		$(".modal-header").css("color", "white");
		$(".modal-title").text("New user");
		$('#modalCRUD').modal('show');
	});

	$(document).on("click", ".btnEdit", function() {
		row = $(this).closest("tr");
		id = row.find('td:eq(0)').text();
		username = row.find('td:eq(1)').text();
		$("#id").val(id);
		$("#username").val(username);
		$("#password").val("");
		$(".modal-header").css("background-color", "#007bff");
		$(".modal-header").css("color", "white");
		$(".modal-title").text("Edit user");
		$('#modalCRUD').modal('show');
	});

	$(document).on("click", ".btnDelete", function() {
		row = $(this);
		id = $(this).closest('tr').find('td:eq(0)').text();
		var agree = confirm("Do you want to delete the user with id " + id + "?");
		if (agree) {
			$.ajax({
				url: "api/users/" + id,
				type: "DELETE",
				datatype: "json",
				data: { id: id },
				success: function() {
					$.ajax({
						url: "api/users/" + id,
						type: "DELETE",
						datatype: "json",
						data: { id: id },
						success: function() {
							userListTable.row(row.parents('tr')).remove().draw();
						}
					});
				}
			});
		}
	});

	$('.btnReset').click(function() {
		$("#id").val(null);
		$("#username").val("");
		$("#password").val("");
	});

});