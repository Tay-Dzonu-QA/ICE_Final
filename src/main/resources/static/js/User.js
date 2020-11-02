fetch("http://localhost:8082/users/read/" + user)
  .then(function (response) {
    if (response.status !== 200) {
      console.log(
        "Looks like there was a problem. Status Code: " + response.status
      );
      return;
    }
    //console.log(response);
    response.json().then(function (UserData) {
      console.log(UserData);
      let welcome = document.querySelector("#userName");
      welcome.innerHTML = "Welcome back " + UserData.name;
      let PLData = UserData.playlists;
      let tables = document.querySelector("#PLTables");
      console.log(PLData);
      createTables(tables, PLData);
    });
  })
  .catch(function (err) {
    console.log("Fetch Error :-S", err);
  });

function createTables(tables, PLData) {
  for (element of PLData) {
    let ID = element.id;
    let Name = element.name;
    let modalAddTrackPL = document.getElementById("TrackAdd");
    addTracks(modalAddTrackPL);

    let tableDiv = document.createElement("div");
    let table = document.createElement("table");
    let Listname = document.createElement("h2");
    Listname.textContent = Name;
    playlistToView = element.id;
    let data = Object.keys(element.tracks[0]);
    generateTableHeadPl(table, data);
    generateTablePl(table, element.tracks, Name, playlistToView);
    tableDiv.appendChild(Listname);
    tableDiv.appendChild(table);
    let tableFooter = document.createElement("footer");
    let UserPage = true;
    generateTableFooterPl(tableFooter, ID, Name, user, UserPage);
    //Need to add footer for deleta and edit track list
    tableDiv.appendChild(tableFooter);

    tableDiv.className = "col-lg";
    tables.appendChild(tableDiv);
    let seperator = document.createElement("div");
    seperator.className = "col-1";
    tables.appendChild(seperator);
  }
}
