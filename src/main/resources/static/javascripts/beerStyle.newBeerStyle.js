var Brewer = Brewer || {};

Brewer.InitBeerStyleModal = (function() {

	function InitBeerStyleModal() {
		this.modal = $('#style-of-beer-modal');
		this.btnSave = $('#btn-beerStyleModal-save');
		this.form = this.modal.find('form');
		this.urlAction = this.form.attr('action');
		this.inputNameStyle = $('#nameStyle');
		this.containerMessageError = $('.js-message-errorNewBeerStyle');
	}

	InitBeerStyleModal.prototype.initModal = function() {
		// Events
		this.form.on('submit', function(event) {
			event.preventDefault() // Disable submit form
		})
		this.modal.on('shown.bs.modal', onModalShow.bind(this));
		this.modal.on('hide.bs.modal', onModalClose.bind(this));
		this.btnSave.on('click', onBtnSaveClick.bind(this));
	}

	function onModalShow() {
		this.inputNameStyle.focus();
	}

	function onModalClose() {
		this.inputNameStyle.val('');
		this.containerMessageError.addClass('hidden');
		this.form.find('#beerStyleName').removeClass('has-error');
	}

	function onBtnSaveClick() {
		var nameStyle = this.inputNameStyle.val().trim();
		$.ajax({
			url : this.urlAction,
			method : 'POST',
			contentType : 'application/json',
			data : JSON.stringify({
				name : nameStyle
			}),
			error : onErrorSaveBeerStyle.bind(this),
			success : onSuccessSaveBeerStyle.bind(this)
		});
	}

	function onErrorSaveBeerStyle(obj) {
		var messageError = obj.responseText;
		this.containerMessageError.removeClass('hidden');
		this.containerMessageError.html('<span>' + messageError + '</span>');
		this.form.find('#beerStyleName').addClass('has-error');
	}

	function onSuccessSaveBeerStyle(beerStyle) {
		var comboBeerStyle = $('#beerStyle');
		comboBeerStyle.append('<option value=' + beerStyle.id + '>' + beerStyle.name + '</option>');
		comboBeerStyle.val(beerStyle.id);
		this.modal.modal('hide');
	}

	return InitBeerStyleModal;

})();

$(function() {

	var initBeerStyleModal = new Brewer.InitBeerStyleModal();
	initBeerStyleModal.initModal();

});
