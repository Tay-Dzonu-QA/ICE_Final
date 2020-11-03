let idup = document.querySelector("#Order_");
idup.href= "Artist.html?user=" + user;
let idDown = document.querySelector("#Order_desc");
idDown.href= "Artist.html?user=" + user+"&order=desc";
let nameUp = document.querySelector("#Order_name");
nameUp.href= "Artist.html?user=" + user+"&order=name";
let nameDown = document.querySelector("#Order_nameDesc");
nameDown.href= "Artist.html?user=" + user+"&order=nameDesc";