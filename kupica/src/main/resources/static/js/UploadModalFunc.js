document.getElementById('file-upload').addEventListener('change',
    function (event) {
      var file = event.target.files[0];
      var reader = new FileReader();
      reader.onload = function (e) {
        var imgData = e.target.result;
        document.getElementById(
            'upload__modal__form__image__input__label').style.backgroundImage = 'url('
            + imgData + ')';
      };
      reader.readAsDataURL(file);
    });

function clearForm() {
  document.getElementById('upload__form').reset();
  document.getElementById(
      'upload__modal__form__image__input__label').style.backgroundImage = 'none';
}
