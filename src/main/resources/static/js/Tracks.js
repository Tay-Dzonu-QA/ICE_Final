const params = new URLSearchParams(window.location.search);
let loggedIn = false;
let tracksToView="";
console.log(params);
let run = 0;

for (const param of params) {
  if(param[0]==="loggedIn"){
    loggedIn = param[1];
  } else if(param[0]==="albums"){
    let artistOrGenre = param[0];
    let artistOrGenreId = param[1];
    albumsToView = "/"+artistOrGenre+"/"+artistOrGenreId;
  }
}

console.log(tracksToView);
console.log(loggedIn);
getTracks(loggedIn,tracksToView);

function getTracks(loggedIn,tracksToView) {
  fetch("http://localhost:8082/tracks/read"+tracksToView)
    .then(function (response) {
      if (response.status !== 200) {
        console.log(
          "Looks like there was a problem. Status Code: " + response.status
        );
        return;
      }
      // Examine the text in the response
      response.json().then(function (TrackData) {
        console.log(TrackData);
        let table = document.querySelector("#TrackTable");
        let data = Object.keys(TrackData[0]);

        generateTableHead(table, data, loggedIn);
        generateTable(table, TrackData, loggedIn);
        if(loggedIn){
            generateAddTrackBtn(table);
        }
        
      });
    })
    .catch(function (err) {
      console.log("Fetch Error :-S", err);
    });
}

function generateTableHead(table, data, loggedIn) {
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
  if (loggedIn) {
    let th2 = document.createElement("th");
    let text2 = document.createTextNode("Edit");
    th2.appendChild(text2);
    row.appendChild(th2);

    let th3 = document.createElement("th");
    let text3 = document.createTextNode("Delete");
    th3.appendChild(text3);
    row.appendChild(th3);
  }
}

function generateTable(table, TrackData, loggedIn) {
  for (let element of TrackData) {
    let row = table.insertRow();
    for (key in element) {
      let cell = row.insertCell();
      let text = document.createTextNode(element[key]);
      if(key ==="album"){
        text =  document.createTextNode(element[key].name)
      }
      cell.appendChild(text);
    }
    let newCell = row.insertCell();
    let myViewButton = document.createElement("button");
    myViewButton.className = "btn";
    myViewButton.id = "ViewTrackButton";
    myViewButton.onclick = function(){document.location='Tracks.html?loggedIn='+loggedIn+'?tracks='+element.id};

    let viewIcon = document.createElement("span");
    viewIcon.className = "material-icons";
    viewIcon.innerHTML="launch";
    myViewButton.appendChild(viewIcon);
    newCell.appendChild(myViewButton);

    if (loggedIn) {
        let newCell2 = row.insertCell();
        let myEditButton = document.createElement("button");
        myEditButton.className = "btn";
        myEditButton.id = "EditTrackButton";
        myEditButton.setAttribute("data-toggle", "modal");
        myEditButton.setAttribute("data-target", "#EditTrackModal");
    
        let editIcon = document.createElement("span");
        editIcon.className = "material-icons";
        editIcon.innerHTML="create";
        myEditButton.appendChild(editIcon);
        let ID = element.id;
        let Name=element.name;
        let Duration = element.duration;
        let Lyrics = element.lyrics;
        myEditButton.onclick = function () {
          changeEditTrackModal(ID, Name,Duration,Lyrics);
        };
        newCell2.appendChild(myEditButton);

        let newCell3 = row.insertCell();
        let myDeleteButton = document.createElement("button");
        myDeleteButton.className = "btn";
        myDeleteButton.id = "DeleteTrackButton"+element.name;

        let deleteIcon = document.createElement("span");
        deleteIcon.className = "material-icons";
        deleteIcon.innerHTML="delete";
        myDeleteButton.appendChild(deleteIcon);
        myDeleteButton.onclick = function () {
            deleteTrack(element.id);
        };
        newCell3.appendChild(myDeleteButton);
    }
  }
}

function generateAddTrackBtn(table){
    let tableFooter = document.createElement("footer");
    let myAddTrackButton = document.createElement("button");
    myAddTrackButton.className = "btn btn-outline-primary";
    myAddTrackButton.innerHTML = "Add Track";
    myAddTrackButton.id = "AddTrackButton";
    myAddTrackButton.setAttribute("data-toggle", "modal");
    myAddTrackButton.setAttribute("data-target", "#AddTrackModal");
    myAddTrackButton.onclick = function () {
      changeAddTrackModal();
    };

    tableFooter.appendChild(myAddTrackButton);
    table.appendChild(tableFooter);
}

function deleteTrack(id) {
    fetch("http://localhost:8082/tracks/delete/" + id, {
      method: "delete",
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

  let TrackId;

  function changeEditTrackModal(id, name,duration,lyrics) {
    let modalEditTrackName = document.getElementById("EditTrackName");
    modalEditTrackName.setAttribute("value", name);

    let modalEditTrackDuration = document.getElementById("EditTrackDuration");
    modalEditTrackDuration.setAttribute("value", duration);
    let modalEditTrackLyrics = document.getElementById("EditTrackLyrics");
    modalEditTrackLyrics.setAttribute("value", lyrics);

    let modalEditTrackAlbum = document.getElementById("EditTrackAlbum");
    addAlbumList(modalEditTrackAlbum);
    TrackId = id;
  }
  function changeAddTrackModal() {
    let modalEditTrackAlbum = document.getElementById("TrackAlbum");
    addAlbumList(modalEditTrackAlbum);
  }

  function addAlbumList(modalAlbumList){
    fetch("http://localhost:8082/albums/read")
      .then(function (response) {
        if (response.status !== 200) {
          console.log(
            "Looks like there was a problem. Status Code: " + response.status
          );
          return;
        }
        // Examine the text in the response
        response.json().then(function (AlbumData) {
          console.log(AlbumData);
          for(let element of AlbumData){
            console.log(element);
            let albumList = document.createElement("option");
            albumList.innerHTML = element.id +". "+element.name;
            modalAlbumList.appendChild(albumList);
          }
        });
      })
      .catch(function (err) {
        console.log("Fetch Error :-S", err);
      });
  }
  document
  .querySelector("form.EditTrack")
  .addEventListener("submit", function (stop) {
     stop.preventDefault();

    let formElements = document.querySelector("form.EditTrack").elements;
    console.log(formElements);

    let EditTrackName = formElements["EditTrackName"].value;
    let EditTrackDuration = formElements["EditTrackDuration"].value;
    let EditTrackLyrics = formElements["EditTrackLyrics "].value;
    let EditTrackAlbum = formElements["EditTrackAlbum"].value;
    let TrackAlbum = EditTrackAlbum.split(".");
    let TrackAlbumId = parseInt(TrackAlbum[0]);

    let TrackId1 = parseInt(TrackId);
    console.log(TrackId1);
    editTrack(EditTrackName, TrackId1,EditTrackDuration,EditTrackLyrics,TrackAlbumId);
  });

  function editTrack(name, TrackId, duration,lyrics,album) {
    fetch("http://localhost:8082/tracks/update/" + TrackId, {
      method: "put",
      headers: {
        "Content-type": "application/json",
      },
      body: (json = JSON.stringify({
        "id": TrackId,
        "name": name,
        "duration":duration,
        "lyrics":lyrics,
        "albums":{
          "id":album
        }
      })),
    })
      .then(json)
      .then(function (data) {
        console.log("Request succeeded with JSON response", data);
        location.reload();
      })
      .catch(function (error) {
        console.log("Request failed", error);
      });
  }

  document
  .querySelector("form.Track")
  .addEventListener("submit", function (stop) {
    stop.preventDefault();

    let formElements = document.querySelector("form.Track").elements;
    let TrackName = formElements["AlbumName"].value;
    let TrackDuration = formElements["TrackDuration"].value;
    let TrackLyrics = formElements["TrackLyrics "].value;
    let TrackAlbum = formElements["TrackAlbum"].value;
    let TrackAlbum1 = TrackAlbum.split(".");
    let TrackAlbumId = parseInt(TrackAlbum1[0]);

    addTrack(AlbumName,TrackDuration,TrackLyrics,TrackAlbumId)
  });

  function addTrack(name, duration,lyrics,album) {
    fetch("http://localhost:8082/tracks/create", {
      method: "post",
      headers: {
        "Content-type": "application/json",
      },
      body: (json = JSON.stringify({
        "name": name,
        "duration":duration,
        "lyrics":lyrics,
        "tracks":{
          "id":album
        }
      })),
    })
      .then(json)
      .then(function (data) {
        console.log("Request succeeded with JSON response", data);
        location.reload();
      })
      .catch(function (error) {
        console.log("Request failed", error);
      });
  }