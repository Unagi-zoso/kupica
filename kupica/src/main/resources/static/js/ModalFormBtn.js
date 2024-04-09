const uploadModal = document.getElementById("upload__modal__form");
const span = document.getElementById("upload__modal__form__quit__btn");

function presentUploadModal() {
    uploadModal.style.display = "block";
}

span.onclick = function() {
    uploadModal.style.display = "none";
}

window.onclick = function(event) {
    if (event.target === uploadModal) {
        uploadModal.style.display = "none";
    }
}

