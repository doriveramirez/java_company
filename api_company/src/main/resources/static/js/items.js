$(document).ready(function() {
	
	var id_item;
	
	$('#creationDate').val(new Date().toDateInputValue());
	$('#state').val("false");

	var itemListTable = $('#itemListTable').DataTable({
		ajax: {
			url: "/api/items",
			dataSrc: ''
		},
		"columns": [
			{
			"data": "id"
		},
			{
			"data": "code"
		}, {
			"data": "description"
		}, {
			"data": "price"
		}, {
			"data": "creationDate"
		}, {
			"data": "creator"
		}, {
			"data": "state"
		},
		{
			"defaultContent": "<div class='text-center'><div class='btn-group'><button class='btn btn-primary btn-sm btnView'><i class='material-icons'>preview</i></button><button class='btn btn-primary btn-sm btnEdit'><i class='material-icons'>edit</i></button><button class='btn btn-danger btn-sm btnDelete'><i class='material-icons'>delete</i></button></div></div>"
		}]
	});

	var row;

	$('#formItems').submit(function(e) {
		e.preventDefault();
		id = $.trim($('#id').val());
		code = $.trim($('#code').val());
		description = $.trim($('#description').val());
		price = $.trim($('#price').val());
		creationDate = $.trim($('#creationDate').val());
		creator = $.trim($('#creator').val());
		state = $.trim($('#state').val());
		var data = {
				id: id,
				code: code,
				description: description,
				price: price,
				state: state,
				suppliers: null,
				priceReductions: null,
				creationDate: creationDate,
				creator: creator
			};
		console.log(JSON.stringify(data))
		if (id!=""){
			console.log("hola");
			$.ajax({
			headers: { 
        		'Content-Type': 'application/json' 
    		},
			url: "api/items/"+id,
			type: "PUT",
			datatype: "json",
			data: JSON.stringify(data),
			success: function(data) {
				itemListTable.ajax.reload(null, false);
			}
		});
		} else {
			$.ajax({
			headers: { 
        		'Content-Type': 'application/json' 
    		},
			url: "api/items",
			type: "POST",
			datatype: "json",
			data: JSON.stringify(data),
			success: function(data) {
				itemListTable.ajax.reload(null, false);
			}
		});	
		}
		$('#modalCRUD').modal('hide');
	}); 

	$("#btnNew").click(function() {
		$("#formItems").trigger("reset");
		$(".modal-header").css("background-color", "#17a2b8");
		$(".modal-header").css("color", "white");
		$(".modal-title").text("New item");
		$('#modalCRUD').modal('show');
	});
       
	$(document).on("click", ".btnEdit", function() {
		row = $(this).closest("tr");        
		id = row.find('td:eq(0)').text(); 
		code = row.find('td:eq(1)').text();
		description = row.find('td:eq(2)').text();
		price = row.find('td:eq(3)').text();
		creationDate = row.find('td:eq(4)').text();
		creator = row.find('td:eq(5)').text();
		state = row.find('td:eq(6)').text();
		$("#id").val(id);
		$("#code").val(code);
		$("#description").val(description);
		$("#price").val(price);
		$("#creationDate").val(creationDate);
		$("#creator").val(creator);
		$("#state").val(state);
		$(".modal-header").css("background-color", "#007bff");
		$(".modal-header").css("color", "white");
		$(".modal-title").text("Edit item");
		$('#modalCRUD').modal('show');
	});

	$(document).on("click", ".btnDelete", function() {
		row = $(this);
		id = $(this).closest('tr').find('td:eq(0)').text();
		var agree = confirm("Do you want to delete the item with id " + id + "?");
		if (agree) {
			$.ajax({
				url: "api/items/" + id,
				type: "DELETE",
				datatype: "json",
				data: { id: id },
				success: function() {
					itemListTable.row(row.parents('tr')).remove().draw();
				}
			});
		}
	});
	
	$(document).on("click", ".btnView", function() {
		console.log("Hola");
		var id = $(this).closest('tr').find('td:eq(0)').text();
		window.location.href = "item_" + id;
	});
	
	$('.btnReset').click(function(){
		$("#id").val(null);
		$("#code").val("");
		$("#description").val("");
		$("#price").val("");
		$('#creationDate').val(new Date().toDateInputValue());
		$("#creator").val("");
		$('#state').val("false");
});

});

Date.prototype.toDateInputValue = (function() {
    var local = new Date(this);
    local.setMinutes(this.getMinutes() - this.getTimezoneOffset());
    return local.toJSON().slice(0,10);
}); 