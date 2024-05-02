const uploadModal = document.getElementById("upload__modal__form");
const uploadModalSection = document.getElementById('upload_modal_section')

function presentUploadModal() {
  uploadModalSection.classList.add('modal__overlay')
  uploadModal.style.visibility = "visible";
  uploadModal.addEventListener("click", function (event) {
    event.stopPropagation();
  });
  document.addEventListener("click", uploadModalOutsideClick);
}

function dismissUploadModal() {
  uploadModal.style.visibility = "hidden";
  document.getElementById('upload__form').reset();
  clearForm();
  uploadModalSection.classList.remove('modal__overlay')
}

function uploadModalOutsideClick(event) {
  if (uploadModalSection.contains(event.target)) {
    dismissUploadModal();
  }
}
