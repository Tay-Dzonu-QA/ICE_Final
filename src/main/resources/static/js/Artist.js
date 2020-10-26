const params = new URLSearchParams(window.location.search);
let loggedIn = false;
for (const param of params) {
  console.log(param);
  loggedIn = param[1];
}
getArtists(loggedIn);

function getArtists(loggedIn) {
  fetch("http://localhost:8082/artist/read")
    .then(function (response) {
      if (response.status !== 200) {
        console.log(
          "Looks like there was a problem. Status Code: " + response.status
        );
        return;
      }
      // Examine the text in the response
      response.json().then(function (ArtistData) {
        let table = document.querySelector("#ArtistTable");
        let data = Object.keys(ArtistData[0]);

        generateTableHead(table, data, loggedIn);
        generateTable(table, ArtistData, loggedIn);
        if (loggedIn) {
          generateAddArtistBtn(table);
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
  let text = document.createTextNode("View Artist");
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

function generateTable(table, ArtistData, loggedIn) {
  for (let element of ArtistData) {
    let row = table.insertRow();
    for (key in element) {
      let cell = row.insertCell();
      let text = document.createTextNode(element[key]);
      if (key === "albums") {
        let albumNo = 0;
        for (album of element[key]) {
          albumNo = +1;
        }
        text = document.createTextNode(albumNo);
      }
      cell.appendChild(text);
    }
    let newCell = row.insertCell();
    let myViewButton = document.createElement("button");
    myViewButton.className = "btn";
    myViewButton.id = "ViewArtistButton";
    myViewButton.onclick = document.location =
      "Albums.html?loggedIn=" + loggedIn + "artist=" + artist;

    let viewIcon = document.createElement("span");
    viewIcon.className = "material-icons";
    viewIcon.innerHTML = "launch";
    myViewButton.appendChild(viewIcon);
    newCell.appendChild(myViewButton);

    if (loggedIn) {
      let newCell2 = row.insertCell();
      let myEditButton = document.createElement("button");
      myEditButton.className = "btn";
      myEditButton.id = "EditArtistButton";
      myEditButton.setAttribute("data-toggle", "modal");
      myEditButton.setAttribute("data-target", "#EditArtistModal");

      let editIcon = document.createElement("span");
      editIcon.className = "material-icons";
      editIcon.innerHTML = "create";
      myEditButton.appendChild(editIcon);
      let ID = element.id;
      let Name = element.name;
      myEditButton.onclick = function () {
        changeEditArtistModal(ID, Name);
      };
      newCell2.appendChild(myEditButton);

      let newCell3 = row.insertCell();
      let myDeleteButton = document.createElement("button");
      myDeleteButton.className = "btn";
      myDeleteButton.id = "DeleteArtistButton" + element.name;

      let deleteIcon = document.createElement("span");
      deleteIcon.className = "material-icons";
      deleteIcon.innerHTML = "delete";
      myDeleteButton.appendChild(deleteIcon);
      myDeleteButton.onclick = function () {
        deleteArtist(element.id);
      };
      newCell3.appendChild(myDeleteButton);
    }
  }
}

function generateAddArtistBtn(table) {
  let tableFooter = document.createElement("footer");
  let myAddArtistButton = document.createElement("button");
  myAddArtistButton.className = "btn btn-outline-primary";
  myAddArtistButton.innerHTML = "Add Artist";
  myAddArtistButton.id = "AddArtistButton";
  myAddArtistButton.setAttribute("data-toggle", "modal");
  myAddArtistButton.setAttribute("data-target", "#AddArtistModal");
  myAddArtistButton.onclick = function () {
    changeAddArtistModal(ID, Name);
  };

  tableFooter.appendChild(myAddArtistButton);
  table.appendChild(tableFooter);
}

function deleteArtist(id) {
  fetch("http://localhost:8082/artist/delete/" + id, {
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

let ArtistId;

function changeEditArtistModal(id, name) {
  let modalPH = document.getElementById("EditArtistName");
  modalPH.setAttribute("value", name);
  ArtistId = id;
}

document
  .querySelector("form.EditArtist")
  .addEventListener("submit", function (stop) {
    stop.preventDefault();

    let formElements = document.querySelector("form.EditArtist").elements;
    console.log(formElements);

    let EditArtistname = formElements["EditArtistName"].value;

    let ArtistId = parseInt(ArtistId);
    console.log(ArtistId);
    editArtist(EditArtistname, ArtistId);
  });

function editArtist(name, ArtistId) {
  fetch("http://localhost:8082/artist/update/" + ArtistId, {
    method: "put",
    headers: {
      "Content-type": "application/json",
    },
    body: (json = JSON.stringify({
      id: ArtistId,
      name: name
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
  .querySelector("form.Artist")
  .addEventListener("submit", function (stop) {
    stop.preventDefault();

    let formElements = document.querySelector("form.Artist").elements;
    console.log(formElements);
    let name = formElements["ArtistName"].value;
    addArtist(name);
  });

function addArtist(name) {
  fetch("http://localhost:8082/artist/create", {
    method: "post",
    headers: {
      "Content-type": "application/json",
    },
    body: (json = JSON.stringify({
      name: name
    })),
  })
    .then(json)
    .then(function (data) {
      console.log("Request succeeded with JSON response", data);
    })
    .catch(function (error) {
      console.log("Request failed", error);
    });
}
