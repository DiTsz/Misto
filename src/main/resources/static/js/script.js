var burgerMenu = document.querySelector(".burger-menu");
var burgerBtn = document.querySelector(".burger-menu-btn");

burgerBtn.addEventListener("click", function() {
  burgerMenu.classList.toggle("open");
});

document.addEventListener("click", function(event) {
  var target = event.target;

  if (!burgerMenu.contains(target) && !burgerBtn.contains(target)) {
    burgerMenu.classList.remove("open");
  }
});




window.addEventListener('wheel', function(event) {
  if (event.deltaY > 0) {
    scrollToNextSection();
  } else if (event.deltaY < 0) {
    scrollToPreviousSection();
  }
});

function scrollToNextSection() {
  var currentSection = getCurrentSection();
  var nextSection = currentSection.nextElementSibling;

  if (nextSection !== null) {
    nextSection.scrollIntoView();
    window.scrollBy(0, -100);
  }
}

function scrollToPreviousSection() {
  var currentSection = getCurrentSection();
  var previousSection = currentSection.previousElementSibling;

  if (previousSection !== null) {
    previousSection.scrollIntoView();
    window.scrollBy(0, 100);
  }
}

function getCurrentSection() {
  var sections = document.querySelectorAll('.quest');
  var currentSection = null;

  sections.forEach(function(section) {
    var rect = section.getBoundingClientRect();

    if (rect.top <= window.innerHeight / 2 && rect.bottom >= window.innerHeight / 2) {
      currentSection = section;
    }
  });

  return currentSection;
}