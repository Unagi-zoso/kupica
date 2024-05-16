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

const withdrawModal = document.getElementById("withdraw__modal__form");
const withdrawModalSection = document.getElementById('withdraw_modal_section')

function presentWithdrawModal() {
  withdrawModalSection.classList.add('modal__overlay')
  withdrawModal.style.visibility = "visible";
  withdrawModal.addEventListener("click", function (event) {
    event.stopPropagation();
  });
  document.addEventListener("click", withdrawModalOutsideClick);
}

function dismissWithdrawModal() {
  withdrawModal.style.visibility = "hidden";
  withdrawModalSection.classList.remove('modal__overlay')
}

function withdrawModalOutsideClick(event) {
  if (withdrawModalSection.contains(event.target)) {
    dismissWithdrawModal();
  }
}
