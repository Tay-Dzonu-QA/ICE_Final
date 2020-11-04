GenerateSideBarNav(user);

function GenerateSideBarNav(user){
  fetch("http://localhost:8082/playlists/user/"+user)
    .then(function (response) {
      if (response.status !== 200) {
        console.log(
          "Looks like there was a problem. Status Code: " + response.status
        );
        return;
      }
      // Examine the text in the response
      response.json().then(function (PlaylistData) {
        let PlSideBar = document.querySelector("#PlList");
          for(let element of PlaylistData){
            let PlList = document.createElement("a");
            PlList.innerHTML = element.name;
            PlList.id = "Select_"+element.id;
            PlList.href= "Playlist.html?user=" + user + "&playlists=" + element.id;
            PlSideBar.appendChild(PlList);
          }
      });
    })
    .catch(function (err) {
      console.log("Fetch Error :-S", err);
    });
    
}