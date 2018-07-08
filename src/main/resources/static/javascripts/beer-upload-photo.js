var Brewer = Brewer || {};

Brewer.UploadPhoto = (function() {

	function UploadPhoto() {
		this.inputPhotoName = $('input[name=photoName]');
		this.inputContentType = $('input[name=photoContentType]');
		
		this.htmlPhotoBeerTemplate = $('#photo-beer').html();
		this.template = Handlebars.compile(this.htmlPhotoBeerTemplate);
		
		this.containerBeerPhoto = $('.js-container-beer-photo');
		
		this.uploadDrop = $('#upload-drop');
	}

	UploadPhoto.prototype.init = function() {
		var settings = {
			type : 'json',
			filelimit : 1,
			allow : '*.(jpg|jpeg|png)',
			action : this.containerBeerPhoto.data('url-photos'),
			complete : onUploadComplete.bind(this)
		}

		UIkit.uploadSelect($('#upload-select'), settings);
		UIkit.uploadDrop(this.uploadDrop, settings);
	}

	function onUploadComplete(result) {
		this.inputPhotoName.val(result.name);
		this.inputContentType.val(result.contentType);

		this.uploadDrop.addClass('hidden');

		var htmlImgPhotoBeer = this.template({
			photoName : result.name
		});

		this.containerBeerPhoto.append(htmlImgPhotoBeer);

		$('.js-remove-photo').on('click', onRemovePhoto.bind(this));
	}

	function onRemovePhoto() {
		$('.js-photo-beer').remove();
		this.uploadDrop.removeClass('hidden');
		this.inputPhotoName.val('');
		this.inputContentType.val('');
	}

	return UploadPhoto;

})();

$(function() {
	var uploadPhoto = new Brewer.UploadPhoto();
	uploadPhoto.init();
});
