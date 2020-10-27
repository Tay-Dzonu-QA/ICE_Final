function openNav(tableId) {
    document.getElementById("mySidebar").style.width = "250px";
    document.getElementById("main").style.marginLeft = "250px";
    document.getElementById(tableId).style.marginLeft = "250px";
}

function closeNav(tableId) {
    document.getElementById("mySidebar").style.width = "0";
    document.getElementById("main").style.marginLeft = "0";
    document.getElementById(tableId).style.marginLeft = "0";
}
