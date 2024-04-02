const menuBtn = document.getElementById("navbar__toggleBtn");
const menu = document.getElementById("navbar__menu__list");
const sideMenuBar = document.getElementById("side__menu__bar");
const toggleBtn = document.getElementById("navbar__toggleBtn");
const menuComponents = document.getElementById("menu__components");

menuBtn.addEventListener('click', () => {
    openNav();
});

function openNav() {
    sideMenuBar.style.width = "250px";
    toggleBtn.style.marginLeft = "250px";
    menuComponents.style.display = "block";
}

function closeNav() {
    menuComponents.style.display = "none";
    sideMenuBar.style.width = "0";
    toggleBtn.style.marginLeft = "0";
}
