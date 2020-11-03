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
      let welcome = document.querySelector("#userName");
      welcome.innerHTML = "Welcome back " + UserData.name;
      let PLData = UserData.playlists;
      let tables = document.querySelector("#PLTables");
      createTables(tables, PLData);
    });
  })
  .catch(function (err) {
    console.log("Fetch Error :-S", err);
  });

function createTables(tables, PLData) {
  console.log(PLData.length);
  if (PLData.length === 0) {
    console.log("entered if statement");
    let userClass = document.querySelector(".UserPlaylist")
    let noPLElement = document.createElement("h3");
    noPLElement.textContent = "No Playlist Found. Please Create New Playlists.";
    noPLElement.style.display = "block";
    noPLElement.style.textAlign = "center"
    userClass.appendChild(noPLElement);
    
  }

  let modalAddTrackPL = document.getElementById("TrackAdd");
  addTracks(modalAddTrackPL);
  
  for (element of PLData) {
    let ID = element.id;
    let Name = element.name;
    let Describe = element.description;

    let tableDiv = document.createElement("div");
    let table = document.createElement("table");
    let Listname = document.createElement("h2");
    Listname.textContent = Name;
    playlistToView = element.id;
    tableDiv.appendChild(Listname);
    if(element.tracks.length ===0){
      let empty = document.createElement("h5");
      empty.textContent = "NO TRACKS";
      tableDiv.appendChild(empty);
    }else{
    let data = Object.keys(element.tracks[0]);
    
    generateTableHeadPl(table, data);
    generateTablePl(table, element.tracks, Name, playlistToView);
    tableDiv.appendChild(table);
    }

    let tableFooter = document.createElement("footer");
    let UserPage = true;
    generateTableFooterPl(tableFooter, ID, Name, user, UserPage,Describe);
    //Need to add footer for deleta and edit track list
    tableDiv.appendChild(tableFooter);

    tableDiv.className = "col";
    tables.appendChild(tableDiv);
  }
}

document
  .querySelector("form.AddPL")
  .addEventListener("submit", function (stop) {
    stop.preventDefault();

    let formElements = document.querySelector("form.AddPL").elements;
    let AddPLname = formElements["AddPLName"].value;
    let AddPLDescription = formElements["AddPLDescription"].value;

    addPL(AddPLname,AddPLDescription);
  });

function addPL(name,description) {
  console.log(name);
  fetch("http://localhost:8082/playlists/create", {
    method: "post",
    headers: {
      "Content-type": "application/json",
    },
    body: (json = JSON.stringify({
      "name": name,
      "user":{
        "id":user
      },
      "description":description,
      "artwork":name+"artwork"
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
