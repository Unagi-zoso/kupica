const topScrollBtn = document.querySelector("#top__scroll__btn");

topScrollBtn.addEventListener("click", scrollToTop);

function scrollToTop() {
    document.body.scrollTop = 0; 
    document.documentElement.scrollTop = 0;
}
