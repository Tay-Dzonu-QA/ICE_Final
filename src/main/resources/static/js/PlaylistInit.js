getTracks(playlistToView, user);
function getTracks(playlistToView, user) {
  fetch("http://localhost:8082/playlists/read/" + playlistToView)
    .then(function (response) {
      if (response.status !== 200) {
        console.log(
          "Looks like there was a problem. Status Code: " + response.status
        );
        return;
      }
      // Examine the text in the response
      response.json().then(function (PlaylistData) {
        let TrackData = PlaylistData.tracks;
        let ID = PlaylistData.id;
        let Name = PlaylistData.name;
        let Describe = PlaylistData.description;
        console.log(PlaylistData);

        let modalAddTrackPL = document.getElementById("TrackAdd");
        addTracks(modalAddTrackPL);

        let title = document.querySelector("#PLTitle");
        title.innerHTML = "Playlist: " + Name;
        let description = document.querySelector("#PLDescription");
        description.innerHTML = "Description:" + Describe;
        if (TrackData.length === 0) {
          let empty = document.querySelector("#PLempty");
          empty.textContent = "NO TRACKS";
        } else {
          let table = document.querySelector("#PlaylistTable");
          let data = Object.keys(TrackData[0]);
          generateTableHeadPl(table, data);
          generateTablePl(table, TrackData, user, playlistToView);
        }
        let footer = document.querySelector("#PLFooter");
        let tableFooter = document.createElement("footer");
        let UserPage = false;
        generateTableFooterPl(tableFooter, ID, Name, user, UserPage, Describe);
        footer.appendChild(tableFooter);
      });
    })
    .catch(function (err) {
      console.log("Fetch Error :-S", err);
    });
}
