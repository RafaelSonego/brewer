$(function() {
	var modal = $('#style-of-beer-modal');
	var btnSave = $('#btn-beerStyleModal-save');
	var form = modal.find('form');
	var urlAction = form.attr('action');
	var inputNameStyle = $('#nameStyle');
	var containerMessageError = $('.js-message-errorNewBeerStyle');

	// Events
	form.on('submit', function(event) {
		event.preventDefault() // Disable submit form
	})
	modal.on('shown.bs.modal', onModalShow);
	modal.on('hide.bs.modal', onModalClose)
	btnSave.on('click', onBtnSaveClick)

	// Functions
	function onModalShow() {
		inputNameStyle.focus();
	}

	function onModalClose() {
		inputNameStyle.val('');
		containerMessageError.addClass('hidden');
		form.find('#beerStyleName').removeClass('has-error');
	}

	function onBtnSaveClick() {
		var nameStyle = inputNameStyle.val().trim();
		$.ajax({
			url : urlAction,
			method : 'POST',
			contentType : 'application/json',
			data : JSON.stringify({
				name : nameStyle
			}),
			error: onErrorSaveBeerStyle,
			success: onSuccessSaveBeerStyle
		});
	}
	
	function onErrorSaveBeerStyle(obj) {
		var messageError = obj.responseText;
		containerMessageError.removeClass('hidden');
		containerMessageError.html('<span>' + messageError + '</span>');
		form.find('#beerStyleName').addClass('has-error');
	}
	
	function onSuccessSaveBeerStyle(beerStyle){
		var comboBeerStyle = $('#beerStyle');
		comboBeerStyle.append('<option value=' + beerStyle.id + '>' + beerStyle.name + '</option>');
		comboBeerStyle.val(beerStyle.id);
		modal.modal('hide');
	}

});