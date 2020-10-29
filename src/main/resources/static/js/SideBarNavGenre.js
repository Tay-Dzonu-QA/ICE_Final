let idup = document.querySelector("#IDup");
idup.href= "Genre.html?user=" + user;
let idDown = document.querySelector("#IDdown");
idDown.href= "Genre.html?user=" + user+"&order=desc";
let nameUp = document.querySelector("#nameUp");
nameUp.href= "Genre.html?user=" + user+"&order=name";
let nameDown = document.querySelector("#nameDown");
nameDown.href= "Genre.html?user=" + user+"&order=nameDesc";