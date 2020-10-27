const params = new URLSearchParams(window.location.search);
let loggedIn = false;
let playlistToView = "";
let user = 0;
console.log(params);

for (const param of params) {
  if (param[0] === "user") {
    user = param[1];
    if (user != 0) {
      loggedIn = true;
    }
  } else if ((param[0] = "playlists")) {
    playlistToView = param[1];
  }
}
getTracks(loggedIn, playlistToView, user);
function getTracks(loggedIn, playlistToView, user) {
  fetch("http://localhost:8082/playlists/read" + playlistToView)
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

        let table = document.querySelector("#TrackTable");
        let data = Object.keys(TrackData[0]);
        generateTableHead(table, data);
        generateTable(table, TrackData, user);
      });
    })
    .catch(function (err) {
      console.log("Fetch Error :-S", err);
    });
}
function generateTableHead(table, data) {
  let thead = table.createTHead();
  let row = thead.insertRow();
  for (let key of data) {
    let th = document.createElement("th");
    let text = document.createTextNode(key);
    th.appendChild(text);
    row.appendChild(th);
  }
  let th = document.createElement("th");
  let text = document.createTextNode("View Track");
  th.appendChild(text);
  row.appendChild(th);

  let th4 = document.createElement("th");
  let text4 = document.createTextNode("Remove From Playlist");
  th4.appendChild(text4);
  row.appendChild(th4);
}

function generateTable(table, TrackData, user) {
  for (let element of TrackData) {
    let row = table.insertRow();
    for (key in element) {
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
