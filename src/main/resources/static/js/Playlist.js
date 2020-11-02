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

        let modalAddTrackPL = document.getElementById("TrackAdd");
        addTracks(modalAddTrackPL);

        let title = document.querySelector("#PLTitle");
        title.innerHTML = "Playlist: " + Name;
        let table = document.querySelector("#PlaylistTable");
        let data = Object.keys(TrackData[0]);
        let footer = document.querySelector("#PLFooter");
        let tableFooter = document.createElement("footer");
        generateTableHeadPl(table, data);
        generateTablePl(table, TrackData, user, playlistToView);
        let UserPage = false;
        generateTableFooterPl(tableFooter, ID, Name, user, UserPage);
        footer.appendChild(tableFooter);
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
  let th = document.createElement("th");
  let text = document.createTextNode("View Track");
  th.appendChild(text);
  row.appendChild(th);

  let th4 = document.createElement("th");
  let text4 = document.createTextNode("Remove");
  th4.appendChild(text4);
  row.appendChild(th4);
}

function generateTablePl(table, TrackData, user, playlistToView) {
  for (let element of TrackData) {
    let row = table.insertRow();
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
      removeTrack(element.id, playlistToView);
    };
    newCell3.appendChild(myRemoveButton);
  }
}
function generateTableFooterPl(tableFooter, ID, Name, user, UserPage) {
  if (UserPage === true) {
    let myViewPLButton = document.createElement("button");
    myViewPLButton.className = "btn";
    myViewPLButton.id = "ViewPLButton" + ID;
    myViewPLButton.onclick = function () {
      document.location = "Playlist.html?user=" + user + "&playlists=" + ID;
    };
    let viewIcon = document.createElement("span");
    viewIcon.className = "material-icons";
    viewIcon.innerHTML = "launch";
    myViewPLButton.appendChild(viewIcon);
    tableFooter.appendChild(myViewPLButton);
  }

  let myAddTrackButton = document.createElement("button");
  myAddTrackButton.className = "btn ";
  myAddTrackButton.id = "AddTrackButton" + ID;
  myAddTrackButton.setAttribute("data-toggle", "modal");
  myAddTrackButton.setAttribute("data-target", "#AddTrackPLModal");
  myAddTrackButton.onclick = function () {
    changeAddTrackModal(ID, Name);
  };
  let addToPlaylistIcon = document.createElement("span");
  addToPlaylistIcon.className = "material-icons";
  addToPlaylistIcon.innerHTML = "add";
  myAddTrackButton.appendChild(addToPlaylistIcon);

  let myEditButtonPL = document.createElement("button");
  myEditButtonPL.className = "btn ";
  myEditButtonPL.id = "EditPLButton" + ID;
  myEditButtonPL.setAttribute("data-toggle", "modal");
  myEditButtonPL.setAttribute("data-target", "#EditPLModal");
  myEditButtonPL.onclick = function () {
    changeEditPLModal(ID, Name);
  };
  let editIcon = document.createElement("span");
  editIcon.className = "material-icons";
  editIcon.innerHTML = "create";
  myEditButtonPL.appendChild(editIcon);

  let myDeleteButtonPL = document.createElement("button");
  myDeleteButtonPL.className = "btn";
  myDeleteButtonPL.id = "DeletePLButton" + ID;
  myDeleteButtonPL.onclick = function () {
    deletePL(ID);
  };
  let deleteIcon = document.createElement("span");
  deleteIcon.className = "material-icons";
  deleteIcon.innerHTML = "delete";
  myDeleteButtonPL.appendChild(deleteIcon);


  tableFooter.appendChild(myAddTrackButton);
  tableFooter.appendChild(myEditButtonPL);
  tableFooter.appendChild(myDeleteButtonPL);
}

function deletePL(ID) {
  fetch("http://localhost:8082/playlists/delete/" + ID, {
    method: "delete",
    headers: {
      "Content-type": "application/json",
    },
  })
    // .then(json)
    .then(function (data) {
      console.log("Request succeeded with JSON response", data);
      // document.location = "User.html?user="+user;
    })
    .catch(function (error) {
      console.log("Request failed", error);
    });
}
let PLID;
function changeEditPLModal(id, name) {
  let modalPH = document.getElementById("EditPLName");
  modalPH.setAttribute("value", name);
  PLID = id;
}
document
  .querySelector("form.EditPL")
  .addEventListener("submit", function (stop) {
    stop.preventDefault();

    let formElements = document.querySelector("form.EditPL").elements;

    let EditPLname = formElements["EditPLName"].value;

    editPL(EditPLname, PLID);
  });

function editPL(name, PLID) {
  let ID = parseInt(PLID);
  fetch("http://localhost:8082/playlists/update/" + PLID, {
    method: "put",
    headers: {
      "Content-type": "application/json",
    },
    body: (json = JSON.stringify({
      id: ID,
      name: name,
    })),
  })
    .then(json)
    .then(function (data) {
      console.log("Request succeeded with JSON response", data);
      // location.reload();
    })
    .catch(function (error) {
      console.log("Request failed", error);
    });
}

function removeTrack(TrackId, playlistToView) {
  fetch(
    "http://localhost:8082/playlists/remove/" + playlistToView + "/" + TrackId,
    {
      method: "put",
      headers: {
        "Content-type": "application/json",
      },
    }
  )
    // .then(json)
    .then(function (data) {
      console.log("Request succeeded with JSON response", data);
      // location.reload();
    })
    .catch(function (error) {
      console.log("Request failed", error);
    });
}

function changeAddTrackModal(ID, Name){
  let modalEditTrackName = document.getElementById("AddTrackPL");
    modalEditTrackName.setAttribute("value", Name);
    PLID = ID;
}

function addTracks(modalAddTrackPL) {
  fetch("http://localhost:8082/tracks/read")
      .then(function(response) {
          if (response.status !== 200) {
              console.log(
                  "Looks like there was a problem. Status Code: " + response.status
              );
              return;
          }
          // Examine the text in the response
          response.json().then(function(TrackData) {
              for (let element of TrackData) {
                  let tracks = document.createElement("option");
                  tracks.innerHTML = element.id + ". " + element.name;
                  modalAddTrackPL.appendChild(tracks);
              }
          });
      })
      .catch(function(err) {
          console.log("Fetch Error :-S", err);
      });
}

document
    .querySelector("form.AddTrackPL")
    .addEventListener("submit", function(stop) {
        stop.preventDefault();

        let formElements = document.querySelector("form.AddTrackPL").elements;

        let TrackPlaylist = formElements["TrackAdd"].value;
        let TrackPlaylist1 = TrackPlaylist.split(".");
        let TrackId1 = parseInt(TrackPlaylist1[0]);
        let TrackPlaylistId = parseInt(PLID);
        addToPlaylist(TrackId1, TrackPlaylistId);
    });

function addToPlaylist(TrackId1, TrackPlaylistId) {
    fetch(
            "http://localhost:8082/playlists/add/" + TrackPlaylistId + "/" + TrackId1, {
                method: "put",
                headers: {
                    "Content-type": "application/json",
                },
            }
        )
        // .then(json)
        .then(function(data) {
            console.log("Request succeeded with JSON response", data);
            location.reload();
        })
        .catch(function(error) {
            console.log("Request failed", error);
        });
}
