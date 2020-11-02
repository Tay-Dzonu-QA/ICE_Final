getTracks(loggedIn, playlistToView, user);
function getTracks(loggedIn, playlistToView, user) {
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

        let title = document.querySelector("#PLTitle");
        title.innerHTML = "Playlist: "+PlaylistData.name;
        
        let table = document.querySelector("#PlaylistTable");
        let data = Object.keys(TrackData[0]);
        generateTableHeadPl(table, data);
        generateTablePl(table, TrackData, user);
      });
    })
    .catch(function (err) {
      console.log("Fetch Error :-S", err);
    });
}
function generateTableHeadPl(table, data) {
  let thead = table.createTHead();
  let row = thead.insertRow();
  for (let key of data) {
    if (key === "playlist") {
      continue;
    }
    let th = document.createElement("th");
    let text = document.createTextNode(key);
    th.appendChild(text);
    row.appendChild(th);
  }
  // th for view track
  let th = document.createElement("th");
  th.className = "btnCol";
  let text = document.createTextNode("");
  th.appendChild(text);
  row.appendChild(th);

  // th for delete track
  let th4 = document.createElement("th");
  th4.className = "btnCol";
  let text4 = document.createTextNode("");
  th4.appendChild(text4);
  row.appendChild(th4);
}

function generateTablePl(table, TrackData, user) {
  for (let element of TrackData) {
    let row = table.insertRow();
    row.className = "dataRow";
    for (key in element) {
      if (key === "playlist") {
        continue;
      }
      let cell = row.insertCell();
      let text = document.createTextNode(element[key]);
      if (key === "album") {
        text = document.createTextNode(element[key].name);
      }
      cell.appendChild(text);
    }
    let newCell = row.insertCell();
    let myViewButton = document.createElement("button");
    myViewButton.className = "btn";
    myViewButton.id = "ViewTrackButton";
    myViewButton.onclick = function () {
      document.location = "Track.html?user=" + user + "&tracks=" + element.id;
    };

    let viewIcon = document.createElement("span");
    viewIcon.className = "material-icons";
    viewIcon.innerHTML = "launch";
    myViewButton.appendChild(viewIcon);
    newCell.appendChild(myViewButton);

    let newCell3 = row.insertCell();
    let myRemoveButton = document.createElement("button");
    myRemoveButton.className = "btn";
    myRemoveButton.id = "RemoveTrackButton" + element.name;

    let RemoveIcon = document.createElement("span");
    RemoveIcon.className = "material-icons";
    RemoveIcon.innerHTML = "delete";
    myRemoveButton.appendChild(RemoveIcon);
    myRemoveButton.onclick = function () {
      removeTrack(element.id);
    };
    newCell3.appendChild(myRemoveButton);
  }
}

function removeTrack(TrackId){
  fetch("http://localhost:8082/playlists/remove/"+playlistToView+"/"+TrackId, {
    method: "put",
    headers: {
      "Content-type": "application/json",
    },
  })
    // .then(json)
    .then(function (data) {
      console.log("Request succeeded with JSON response", data);
      location.reload();
    })
    .catch(function (error) {
      console.log("Request failed", error);
    });


}
