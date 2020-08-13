$(document).ready(function() {
	
	var idItem = window.location.pathname.split("/item_").join('');
	var item;
	$('#idItem').val(idItem);
	
	$.ajax({
			headers: { 
        		'Content-Type': 'application/json' 
    		},
			url: "api/items/"+idItem,
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
			}
	});
	
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

	var row;

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
		console.log(JSON.stringify(data))
		if (idPriceReduction!=""){
			$.ajax({
			headers: { 
        		'Content-Type': 'application/json' 
    		},
			url: "api/items/"+idItem+"/priceReductions/"+idPriceReduction,
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
			url: "api/items/"+idItem+"/priceReductions/",
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
		idPriceReduction = null;
		$("#formItem").trigger("reset");
		$(".modal-header").css("background-color", "#17a2b8");
		$(".modal-header").css("color", "white");
		$(".modal-title").text("New Price Reduction");
		$('#priceReductionsCRUD').modal('show');
	});
       
	$(document).on("click", ".btnEditPriceReduction", function() {
		row = $(this).closest("tr");        
		idPriceReduction = row.find('td:eq(0)').text();
		reducedPrice = row.find('td:eq(1)').text();
		startDate = row.find('td:eq(2)').text();
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
		row = $(this);
		idPriceReduction = $(this).closest('tr').find('td:eq(0)').text();
		var agree = confirm("Do you want to delete the Price reduction with id " + idPriceReduction + "?");
		if (agree) {
			$.ajax({
				url: "api/items/"+idItem+"/priceReductions/"+idPriceReduction,
				type: "DELETE",
				datatype: "json",
				data: { id: idPriceReduction },
				success: function() {
					itemListPriceReductions.row(row.parents('tr')).remove().draw();
				}
			});
		}
	});
	
	$('.btnResetPriceReduction').click(function(){
		$("#idPriceReduction").val(null);
		$("#reducedPrice").val("");
		$('#startDate').val(new Date().toDateInputValue());
});

});

Date.prototype.toDateInputValue = (function() {
    var local = new Date(this);
    local.setMinutes(this.getMinutes() - this.getTimezoneOffset());
    return local.toJSON().slice(0,10);
});