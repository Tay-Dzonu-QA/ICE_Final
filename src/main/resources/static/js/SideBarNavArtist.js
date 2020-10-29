let idup = document.querySelector("#IDup");
idup.href= "Artist.html?user=" + user;
let idDown = document.querySelector("#IDdown");
idDown.href= "Artist.html?user=" + user+"&order=desc";
let nameUp = document.querySelector("#nameUp");
nameUp.href= "Artist.html?user=" + user+"&order=name";
let nameDown = document.querySelector("#nameDown");
nameDown.href= "Artist.html?user=" + user+"&order=nameDesc";