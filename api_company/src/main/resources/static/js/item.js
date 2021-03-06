$(document).ready(function() {

	var idItem = window.location.pathname.split("/item_").join('');
	var item;
	$('#idItem').val(idItem);
	var belongs = [];
	var index = 0;
	var suppliers = [];

	$.ajax({
		async: false,
		headers: {
			'Content-Type': 'application/json'
		},
		url: "api/suppliers/",
		type: "GET",
		datatype: "json",
		success: function(data) {
			suppliers = data;
		}
	});

	$.ajax({
		async: false,
		headers: {
			'Content-Type': 'application/json'
		},
		url: "api/items/" + idItem,
		type: "GET",
		datatype: "json",
		success: function(data) {
			item = data;
			$('#idItem').text(data.id);
			$('#code').text(data.code);
			$('#description').text(data.description);
			$('#price').text(data.price);
			$('#creationDate').text(data.creationDate);
			$('#creator').text(data.creator);
			$('#state').text(data.state);
			var loop;
			console.log(suppliers.length);
			for (var i = 0; i < item.suppliers.length; i++) {
				loop = false;
				for (var x = 0; x < suppliers.length; x++) {
					if (item.suppliers[i].id == suppliers[x].id) {
						loop = true;
					}
				}
				if (loop) {
					belongs[i] = true;
				} else {
					belongs[i] = false;
				}
			}
		}
	});

	$('#startDate').val(new Date().toDateInputValue());

	var itemListPriceReductions = $('#itemListPriceReductions').DataTable({
		ajax: {
			url: "/api/items/" + idItem + "/priceReductions",
			dataSrc: ''
		},
		"columns": [
			{
				"data": "id"
			},
			{
				"data": "reducedPrice"
			}, {
				"data": "startDate"
			},
			{
				"defaultContent": "<div class='text-center'><div class='btn-group'><button class='btn btn-primary btn-sm btnEditPriceReduction'><i class='material-icons'>edit</i></button><button class='btn btn-danger btn-sm btnDeletePriceReduction'><i class='material-icons'>delete</i></button></div></div>"
			}]
	});

	$('#startDate').val(new Date().toDateInputValue());

	var rowPriceReduction;

	$('#formPriceReductions').submit(function(e) {
		e.preventDefault();
		idPriceReduction = $.trim($('#idPriceReduction').val());
		reducedPrice = $.trim($('#reducedPrice').val());
		startDate = $.trim($('#startDate').val());
		var data = {
			id: idPriceReduction,
			reducedPrice: reducedPrice,
			startDate: startDate,
			item: item
		};
		if (idPriceReduction != "") {
			$.ajax({
				headers: {
					'Content-Type': 'application/json'
				},
				url: "api/items/" + idItem + "/priceReductions/" + idPriceReduction,
				type: "PUT",
				datatype: "json",
				data: JSON.stringify(data),
				success: function(data) {
					itemListPriceReductions.ajax.reload(null, false);
				}
			});
		} else {
			$.ajax({
				headers: {
					'Content-Type': 'application/json'
				},
				url: "api/items/" + idItem + "/priceReductions/",
				type: "POST",
				datatype: "json",
				data: JSON.stringify(data),
				success: function(data) {
					itemListPriceReductions.ajax.reload(null, false);
				}
			});
		}
		$('#priceReductionsCRUD').modal('hide');
	});

	$("#btnNewPriceReduction").click(function() {
		$("#formPriceReductions").trigger("reset");
		$('#startDate').val(new Date().toDateInputValue());
		$(".modal-header").css("background-color", "#17a2b8");
		$(".modal-header").css("color", "white");
		$(".modal-title").text("New Price Reduction");
		$('#priceReductionsCRUD').modal('show');
	});

	$(document).on("click", ".btnEditPriceReduction", function() {
		rowPriceReduction = $(this).closest("tr");
		idPriceReduction = rowPriceReduction.find('td:eq(0)').text();
		reducedPrice = rowPriceReduction.find('td:eq(1)').text();
		startDate = rowPriceReduction.find('td:eq(2)').text();
		$("#idItem").val(idItem);
		$("#idPriceReduction").val(idPriceReduction);
		$("#reducedPrice").val(reducedPrice);
		$("#startDate").val(startDate);
		$(".modal-header").css("background-color", "#007bff");
		$(".modal-header").css("color", "white");
		$(".modal-title").text("Edit Price Reduction");
		$('#priceReductionsCRUD').modal('show');
	});

	$(document).on("click", ".btnDeletePriceReduction", function() {
		rowPriceReduction = $(this);
		idPriceReduction = $(this).closest('tr').find('td:eq(0)').text();
		var agree = confirm("Do you want to delete the Price reduction with id " + idPriceReduction + "?");
		if (agree) {
			$.ajax({
				url: "api/items/" + idItem + "/priceReductions/" + idPriceReduction,
				type: "DELETE",
				datatype: "json",
				data: { id: idPriceReduction },
				success: function() {
					itemListPriceReductions.rowPriceReduction(rowPriceReduction.parents('tr')).remove().draw();
				}
			});
		}
	});

	$('.btnResetPriceReduction').click(function() {
		$("#idPriceReduction").val(null);
		$("#reducedPrice").val("");
		$('#startDate').val(new Date().toDateInputValue());
	});

	var suppliersList = $('#suppliersList').DataTable({
		ajax: {
			url: "/api/suppliers/",
			dataSrc: ''
		},
		"columns": [
			{
				"data": "id"
			},
			{
				"data": "name"
			}, {
				"data": "country"
			}, {
				"defaultContent": "asd",
				"render": function(type, row, meta) {
					if (belongs[index] == true) {
						index++;
						return "<div class='text-center'><i class='material-icons'>done_outline</i>";
					} else {
						index++;
						return "<div class='text-center'><div class='btn-group'><button class='btn btn-primary btn-sm btnUpdateMTM'><i class='material-icons'>add_box</i></button></div></div>"
					}
				}
			},
			{
				"defaultContent": "<div class='text-center'><div class='btn-group'><button class='btn btn-primary btn-sm btnEditSupplier'><i class='material-icons'>edit</i></button><button class='btn btn-danger btn-sm btnDeleteSupplier'><i class='material-icons'>delete</i></button></div></div>"
			}]
	});

	var rowSupplier;

	$('#formSuppliers').submit(function(e) {
		e.preventDefault();
		idSupplier = $.trim($('#idSupplier').val());
		name = $.trim($('#name').val());
		country = $.trim($('#country').val());
		var data = {
			id: idSupplier,
			name: name,
			country: country,
			item: item
		};
		if (idSupplier != "") {
			$.ajax({
				headers: {
					'Content-Type': 'application/json'
				},
				url: "api/suppliers/" + idSupplier,
				type: "PUT",
				datatype: "json",
				data: JSON.stringify(data),
				success: function(data) {
					suppliersList.ajax.reload(null, false);
					location.reload();
				}
			});
		} else {
			$.ajax({
				headers: {
					'Content-Type': 'application/json'
				},
				url: "api/suppliers/",
				type: "POST",
				datatype: "json",
				data: JSON.stringify(data),
				success: function(data) {
					suppliersList.ajax.reload(null, false);
					location.reload();
				}
			});
		}
		$('#suppliersCRUD').modal('hide');
	});

	$("#btnNewSupplier").click(function() {
		$("#formSuppliers").trigger("reset");
		$(".modal-header").css("background-color", "#17a2b8");
		$(".modal-header").css("color", "white");
		$(".modal-title").text("New Supplier");
		$('#suppliersCRUD').modal('show');
	});

	$(document).on("click", ".btnEditSupplier", function() {
		rowSupplier = $(this).closest("tr");
		idSupplier = rowSupplier.find('td:eq(0)').text();
		name = rowSupplier.find('td:eq(1)').text();
		country = rowSupplier.find('td:eq(2)').text();
		$("#idItem").val(idItem);
		$("#idSupplier").val(idSupplier);
		$("#name").val(name);
		$("#country").val(country);
		$(".modal-header").css("background-color", "#007bff");
		$(".modal-header").css("color", "white");
		$(".modal-title").text("Edit Price Reduction");
		$('#suppliersCRUD').modal('show');
	});

	$(document).on("click", ".btnDeleteSupplier", function() {
		rowSupplier = $(this).closest("tr");
		idSupplier = $(this).closest('tr').find('td:eq(0)').text();
		$.ajax({
			headers: {
				'Content-Type': 'application/json'
			},
			url: "api/items/" + idItem,
			type: "GET",
			datatype: "json",
			success: function(item) {
				var find = false;
				for (var i = 0; i < item.suppliers.length; i++) {
					if (item.suppliers[i].id == idSupplier) {
						find = true;
					}
				}
				if (find) {
					var agree = confirm("Do you want to unlink the Supplier with id " + idSupplier + " of the item with id " + idItem + "?");
					if (agree) {
						for (var i = 0; i < item.suppliers.length; i++) {
							if (item.suppliers[i].id == idSupplier) {
								item.suppliers[i] = null;
							}
						}
						updateItem(item);
						rowSupplier.find('td:eq(3)').html("<div class='text-center'><div class='btn-group'><button class='btn btn-primary btn-sm btnUpdateMTM'><i class='material-icons'>add_box</i></button></div></div>")
					}
				} else {
					deleteSupplier();
				}
			}
		});
	});

	$('.btnResetSupplier').click(function() {
		$("#idSupplier").val(null);
		$("#name").val("");
		$('#country').val(new Date().toDateInputValue());
	});

	$(document).on("click", ".btnUpdateMTM", function() {
		rowSupplier = $(this).closest("tr");
		idSupplier = rowSupplier.find('td:eq(0)').text();
		name = rowSupplier.find('td:eq(1)').text();
		country = rowSupplier.find('td:eq(2)').text();
		var agree = confirm("Do you want to link the Supplier with id " + idSupplier + " of the item with id " + idItem + "?");
		if (agree) {
			var data = createSupplier();
			item.suppliers.push(data);
			updateItem(item);
			rowSupplier.find('td:eq(3)').html("<div class='text-center'><i class='material-icons'>done_outline</i>")
		}
	});

	function deleteSupplier() {
		var agree = confirm("Do you want to delete the Supplier with id " + idSupplier + "?");
		if (agree) {
			$.ajax({
				url: "api/suppliers/" + idSupplier,
				type: "DELETE",
				datatype: "json",
				data: { id: idSupplier },
				success: function() {
					suppliersList.ajax.reload(null, false);
					location.reload();
				}
			});
		}
	}

	function updateItem(item) {
		$.ajax({
			headers: {
				'Content-Type': 'application/json'
			},
			url: "api/items/" + idItem,
			type: "PUT",
			datatype: "json",
			data: JSON.stringify(item),
			success: function(item) {
			}
		});
	}

	function createSupplier() {
		var data = {
			id: idSupplier,
			name: name,
			country: country,
			item: null
		};
		return data;
	}

});

Date.prototype.toDateInputValue = (function() {
	var local = new Date(this);
	local.setMinutes(this.getMinutes() - this.getTimezoneOffset());
	return local.toJSON().slice(0, 10);
});