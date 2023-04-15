// const p = 50;
// var lastYOffset = 0;
// var countOfQuests = getCountOfQuests();
// var currentQuest = 0;
// var boxes = document.querySelectorAll('.quest');

// window.addEventListener('scroll', function() {
//   dif = pageYOffset - lastYOffset;
//   if(dif < -p || dif > p){
//     if(dif > p){
//       currentQuest++;
//     } else {
//       currentQuest--;
//     }
//     checkQuest();
//     let box = boxes[currentQuest];
//     box.scrollIntoView();
//           lastYOffset = pageYOffset;
//   }
// });

// function getCountOfQuests(){
//   return document.querySelectorAll('.quest').length;
// }

// function checkQuest(){
//   if(currentQuest > countOfQuests){
//     currentQuest = countOfQuests - 1;
//   }else if(currentQuest < 0){
//     currentQuest = 0;
//   }

// }
