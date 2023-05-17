var burgerMenu = document.getElementById("burger-menu");
var burgerBtn = document.getElementById("burger-btn");

burgerBtn.addEventListener("click", function() {
  burgerMenu.classList.toggle("open");
});

document.addEventListener("click", function(event) {
  var target = event.target;

  if (!burgerMenu.contains(target) && !burgerBtn.contains(target)) {
    burgerMenu.classList.remove("open");
  }
});