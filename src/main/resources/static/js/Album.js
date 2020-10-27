const params = new URLSearchParams(window.location.search);
let loggedIn = false;
let albumsToView;
console.log(params);
let run = 0;

for (const param of params) {
  console.log(param);
  if(run === 0){
    loggedIn = param[1];
    run+=1;
  } else if(run ===1){
    let artistOrGenre = param[0];
    let artistOrGenreId = param[1];
    albumsToView = "/"+artistOrGenre+"/"+artistOrGenreId;
    run+=1;
  }
}
console.log(run);
if(run ===1){
  albumsToView="";
}
console.log(albumsToView);
console.log(loggedIn);
getAlbums(loggedIn,albumsToView);

function getAlbums(loggedIn,albumsToView) {
  fetch("http://localhost:8082/albums/read"+albumsToView)
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
        let table = document.querySelector("#AlbumTable");
        let data = Object.keys(AlbumData[0]);

        generateTableHead(table, data, loggedIn);
        generateTable(table, AlbumData, loggedIn);
        if(loggedIn){
            generateAddAlbumBtn(table);
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
  let text = document.createTextNode("View Album");
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

function generateTable(table, AlbumData, loggedIn) {
  for (let element of AlbumData) {
    let row = table.insertRow();
    for (key in element) {
      let cell = row.insertCell();
      let text = document.createTextNode(element[key]);
      if (key === "tracks") {
        let tracksNo = 0;
        for (tracks of element[key]) {
            tracksNo +=1;
        }
        text = document.createTextNode(tracksNo);
      }
      if(key ==="genre" ||key ==="artist"){
        text =  document.createTextNode(element[key].name)
      }
      cell.appendChild(text);
    }
    let newCell = row.insertCell();
    let myViewButton = document.createElement("button");
    myViewButton.className = "btn";
    myViewButton.id = "ViewAlbumButton";
    myViewButton.onclick = function(){document.location='Tracks.html?loggedIn='+loggedIn+'?albums='+element.id};

    let viewIcon = document.createElement("span");
    viewIcon.className = "material-icons";
    viewIcon.innerHTML="launch";
    myViewButton.appendChild(viewIcon);
    newCell.appendChild(myViewButton);

    if (loggedIn) {
        let newCell2 = row.insertCell();
        let myEditButton = document.createElement("button");
        myEditButton.className = "btn";
        myEditButton.id = "EditAlbumButton";
        myEditButton.setAttribute("data-toggle", "modal");
        myEditButton.setAttribute("data-target", "#EditAlbumModal");
    
        let editIcon = document.createElement("span");
        editIcon.className = "material-icons";
        editIcon.innerHTML="create";
        myEditButton.appendChild(editIcon);
        let ID = element.id;
        let Name=element.name;
        let Cover = element.cover;
        myEditButton.onclick = function () {
          changeEditAlbumModal(ID, Name,Cover);
        };
        newCell2.appendChild(myEditButton);

        let newCell3 = row.insertCell();
        let myDeleteButton = document.createElement("button");
        myDeleteButton.className = "btn";
        myDeleteButton.id = "DeleteAlbumButton"+element.name;

        let deleteIcon = document.createElement("span");
        deleteIcon.className = "material-icons";
        deleteIcon.innerHTML="delete";
        myDeleteButton.appendChild(deleteIcon);
        myDeleteButton.onclick = function () {
            deleteAlbum(element.id);
        };
        newCell3.appendChild(myDeleteButton);
    }
  }
}

function generateAddAlbumBtn(table){
    let tableFooter = document.createElement("footer");
    let myAddAlbumButton = document.createElement("button");
    myAddAlbumButton.className = "btn btn-outline-primary";
    myAddAlbumButton.innerHTML = "Add Album";
    myAddAlbumButton.id = "AddAlbumButton";
    myAddAlbumButton.setAttribute("data-toggle", "modal");
    myAddAlbumButton.setAttribute("data-target", "#AddAlbumModal");
    myAddAlbumButton.onclick = function () {
      changeAddAlbumModal();
    };

    tableFooter.appendChild(myAddAlbumButton);
    table.appendChild(tableFooter);
}

function deleteAlbum(id) {
    fetch("http://localhost:8082/albums/delete/" + id, {
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

  let AlbumId;

  function changeEditAlbumModal(id, name,cover) {
    let modalEditAlbumName = document.getElementById("EditAlbumName");
    modalEditAlbumName.setAttribute("value", name);
    let modalEditAlbumCover = document.getElementById("EditAlbumCover");
    modalEditAlbumCover.setAttribute("value", cover);

    let modalEditAlbumArtist = document.getElementById("EditAlbumArtist");
    addArtistList(modalEditAlbumArtist);
    let modalEditAlbumGenre = document.getElementById("EditAlbumGenre");
    addGenreList(modalEditAlbumGenre);
    
    AlbumId = id;
  }
  function changeAddAlbumModal() {
    let modalEditAlbumArtist = document.getElementById("AlbumArtist");
    addArtistList(modalEditAlbumArtist);
    let modalEditAlbumGenre = document.getElementById("AlbumGenre");
    addGenreList(modalEditAlbumGenre);
  }

  function addArtistList(modalArtistList){
    fetch("http://localhost:8082/artists/read")
      .then(function (response) {
        if (response.status !== 200) {
          console.log(
            "Looks like there was a problem. Status Code: " + response.status
          );
          return;
        }
        // Examine the text in the response
        response.json().then(function (ArtistData) {
          console.log(ArtistData);
          for(let element of ArtistData){
            console.log(element);
            let artistList = document.createElement("option");
            artistList.innerHTML = element.id +". "+element.name;
            modalArtistList.appendChild(artistList);
          }
        });
      })
      .catch(function (err) {
        console.log("Fetch Error :-S", err);
      });
  }


  function addGenreList(modalArtistList){
    fetch("http://localhost:8082/genres/read")
      .then(function (response) {
        if (response.status !== 200) {
          console.log(
            "Looks like there was a problem. Status Code: " + response.status
          );
          return;
        }
        // Examine the text in the response
        response.json().then(function (GenreData) {
          console.log(GenreData);
          for(let element of GenreData){
            console.log(element);
            let GenreList = document.createElement("option");
            GenreList.innerHTML = element.id +". "+element.name;
            modalArtistList.appendChild(GenreList);
          }
        });
      })
      .catch(function (err) {
        console.log("Fetch Error :-S", err);
      });
  }

  document
  .querySelector("form.EditAlbum")
  .addEventListener("submit", function (stop) {
     stop.preventDefault();

    let formElements = document.querySelector("form.EditAlbum").elements;
    console.log(formElements);

    let EditAlbumName = formElements["EditAlbumName"].value;
    let EditAlbumCover = formElements["EditAlbumCover"].value;
    let EditAlbumArtist = formElements["EditAlbumArtist"].value;
    let AlbumArtsit = EditAlbumArtist.split(".");
    let AlbumArtistId = parseInt(AlbumArtsit[0]);
    let EditAlbumGenre = formElements["EditAlbumGenre"].value;
    let AlbumGenre = EditAlbumGenre.split(".");
    let AlbumGenreId = parseInt(AlbumGenre[0]);

    let AlbumId1 = parseInt(AlbumId);
    console.log(AlbumId1);
    editAlbum(EditAlbumName, AlbumId1,EditAlbumCover,AlbumArtistId,AlbumGenreId);
  });

  function editAlbum(name, AlbumId, cover,artist,genre) {
    fetch("http://localhost:8082/albums/update/" + AlbumId, {
      method: "put",
      headers: {
        "Content-type": "application/json",
      },
      body: (json = JSON.stringify({
        "id": AlbumId,
        "name": name,
        "artists":{
          "id":artist
        },
        "genres":{
          "id":genre
        },
        "cover":cover
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
  .querySelector("form.Album")
  .addEventListener("submit", function (stop) {
    stop.preventDefault();

    let formElements = document.querySelector("form.Album").elements;
    let AlbumName = formElements["AlbumName"].value;
    let AlbumCover = formElements["AlbumCover"].value;
    let AlbumArtist = formElements["AlbumArtist"].value;
    let AlbumArtist1 = AlbumArtist.split(".");
    let AlbumArtistId = parseInt(AlbumArtist1[0]);
    let AlbumGenre = formElements["AlbumGenre"].value;
    let AlbumGenre1 = AlbumGenre.split(".");
    let AlbumGenreId = parseInt(AlbumGenre1[0]);

    addAlbum(AlbumName,AlbumCover,AlbumArtistId,AlbumGenreId)
  });

  function addAlbum(name, cover,artist,genre) {
    fetch("http://localhost:8082/albums/create", {
      method: "post",
      headers: {
        "Content-type": "application/json",
      },
      body: (json = JSON.stringify({
        "name": name,
        "artists":{
          "id":artist
        },
        "genres":{
          "id":genre
        },
        "cover":cover
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
