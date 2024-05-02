/* detail__article__modal */
const detailArticleModal = document.getElementById("detail__article__modal");
const mainPage = document.getElementById("main__page");
const detailArticleModalSection = document.getElementById(
    "detail_article_modal_section");

function presentDetailArticleModal() {
  detailArticleModalSection.classList.add('modal__overlay');
  detailArticleModal.style.visibility = "visible";
  mainPage.classList.remove('scrollable');
  mainPage.classList.add('no__scroll');
  detailArticleModal.addEventListener("click", function (event) {
    event.stopPropagation();
  });
  document.addEventListener("click", detailArticleModalOutsideClick);
}

function dismissDetailArticleModal() {
  detailArticleModal.style.visibility = "hidden";
  document.getElementById(
      'detail__article__modal__content__comment__input__nickname').value = '';
  document.getElementById(
      'detail__article__modal__content__comment__input__password').value = '';
  document.getElementById(
      'detail__article__modal__content__comment__input').value = '';
  mainPage.classList.remove('no__scroll');
  mainPage.classList.add('scrollable');
  detailArticleModalSection.classList.remove('modal__overlay');
}

function detailArticleModalOutsideClick(event) {
  if (detailArticleModalSection.contains(event.target)) {
    dismissDetailArticleModal();
  }
}

/* detail__article__modify__modal__form */
const detailArticleModifyModal = document.getElementById("modify__modal__form");
const detailArticleModifyModalSection = document.getElementById(
    "detail_article_modify_modal_section");

function presentDetailArticleModifyModal() {
  detailArticleModifyModalSection.classList.add('modal__overlay');
  detailArticleModifyModal.style.visibility = "visible";
  detailArticleModal.classList.remove('scrollable');
  detailArticleModal.classList.add('no__scroll');
  detailArticleModifyModal.addEventListener("click", function (event) {
    event.stopPropagation();
  });
  document.addEventListener("click", detailArticleModifyModalOutsideClick);
}

function dismissDetailArticleModifyModal() {
  detailArticleModifyModal.style.visibility = "hidden";
  detailArticleModal.classList.remove('no__scroll')
  detailArticleModal.classList.add('scrollable');
  document.getElementById('modify__form').reset();
  detailArticleModifyModalSection.classList.remove('modal__overlay');
}

function detailArticleModifyModalOutsideClick(event) {
  if (detailArticleModifyModalSection.contains(event.target)) {
    dismissDetailArticleModifyModal();
  }
}

/* detail__article__delete__modal__form */
const detailArticleDeleteModal = document.getElementById("delete__modal__form");
const detailArticleDeleteModalSection = document.getElementById(
    "detail_article_delete_modal_section");

function presentDetailArticleDeleteModal() {
  detailArticleDeleteModalSection.classList.add('modal__overlay');
  detailArticleDeleteModal.style.visibility = "visible";
  detailArticleModal.classList.remove('scrollable');
  detailArticleModal.classList.add('no__scroll');
  detailArticleDeleteModal.addEventListener("click", function (event) {
    event.stopPropagation();
  });
  document.addEventListener("click", detailArticleDeleteModalOutsideClick);
}

function dismissDetailArticleDeleteModal() {
  detailArticleDeleteModal.style.visibility = "hidden";
  detailArticleModal.classList.remove('no__scroll');
  detailArticleModal.classList.add('scrollable');
  document.getElementById('detail__article__delete__password').value = '';
  detailArticleDeleteModalSection.classList.remove('modal__overlay');
}

function detailArticleDeleteModalOutsideClick(event) {
  if (detailArticleDeleteModalSection.contains(event.target)) {
    dismissDetailArticleDeleteModal();
  }
}

/* comment__modify__modal__form */
const commentModifyModal = document.getElementById("comment__modify__modal");
const commentModifyModalSection = document.getElementById(
    "comment_modify_modal_section");

function presentCommentModifyModal() {
  commentModifyModalSection.classList.add('modal__overlay');
  commentModifyModal.style.visibility = "visible";
  detailArticleModal.classList.remove('scrollable');
  detailArticleModal.classList.add('no__scroll');
  commentModifyModal.addEventListener("click", function (event) {
    event.stopPropagation();
  });
  document.addEventListener("click", commentModifyModalOutsideClick);
}

function dismissCommentModifyModal() {
  commentModifyModal.style.visibility = "hidden";
  detailArticleModal.classList.remove('no__scroll');
  detailArticleModal.classList.add('scrollable');
  document.getElementById('comment__modify__nickname').value = '';
  document.getElementById('comment__modify__password').value = '';
  document.getElementById('comment__modify__content').value = '';
  commentModifyModalSection.classList.remove('modal__overlay');
}

function commentModifyModalOutsideClick(event) {
  if (commentModifyModalSection.contains(event.target)) {
    dismissCommentModifyModal();
  }
}

/* comment__delete__modal__form */
const commentDeleteModal = document.getElementById("comment__delete__modal");
const commentDeleteModalSection = document.getElementById(
    "comment_delete_modal_section");

function presentCommentDeleteModal() {
  commentDeleteModalSection.classList.add('modal__overlay');
  commentDeleteModal.style.visibility = "visible";
  detailArticleModal.classList.remove('scrollable');
  detailArticleModal.classList.add('no__scroll');
  commentDeleteModal.addEventListener("click", function (event) {
    event.stopPropagation();
  });
  document.addEventListener("click", commentDeleteModalOutsideClick);
}

function dismissCommentDeleteModal() {
  commentDeleteModal.style.visibility = "hidden";
  detailArticleModal.classList.remove('no__scroll');
  detailArticleModal.classList.add('scrollable');
  document.getElementById('comment__delete__password').value = '';
  commentDeleteModalSection.classList.remove('modal__overlay');
}

function commentDeleteModalOutsideClick(event) {
  if (commentDeleteModalSection.contains(event.target)) {
    dismissCommentDeleteModal();
  }
}
