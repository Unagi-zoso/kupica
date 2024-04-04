var modal = document.getElementById("upload__modal__form");
var btn = document.getElementById("upload__btn");
var span = document.getElementById("upload__modal__form__quit__btn");

btn.onclick = function() {
    modal.style.display = "block";
}

span.onclick = function() {
    modal.style.display = "none";
}

window.onclick = function(event) {
    if (event.target === modal) {
        modal.style.display = "none";
    }
}

