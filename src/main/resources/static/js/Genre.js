const params = new URLSearchParams(window.location.search);
let loggedIn = false;
let user = 0;
let order ="";
for (const param of params) {
  if (param[0] === "user") {
    user = param[1];
    if (user !== 0) {
      loggedIn = true;
    }
  }else if(param[0]==="order"){
    order = "/"+param[1];
  }
}
getGenres(loggedIn,user,order);

function getGenres(loggedIn,user,order) {
  fetch("http://localhost:8082/genres/read"+order)
    .then(function (response) {
      if (response.status !== 200) {
        console.log(
          "Looks like there was a problem. Status Code: " + response.status
        );
        return;
      }
      // Examine the text in the response
      response.json().then(function (GenreData) {
        let table = document.querySelector("#GenreTable");
        let data = Object.keys(GenreData[0]);

        generateTableHead(table, data, loggedIn);
        generateTable(table, GenreData, loggedIn,user);
        if (loggedIn) {
          generateAddGenreBtn(table);
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
  let text = document.createTextNode("View Genre");
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

function generateTable(table, GenreData, loggedIn,user) {
  for (let element of GenreData) {
    let row = table.insertRow();
    for (key in element) {
      let cell = row.insertCell();
      let text = document.createTextNode(element[key]);
      if (key === "albums") {
        let albumNo = 0;
        for (album of element[key]) {
          albumNo += 1;
        }
        text = document.createTextNode(albumNo);
      }
      cell.appendChild(text);
    }
    let newCell = row.insertCell();
    let myViewButton = document.createElement("button");
    myViewButton.className = "btn";
    myViewButton.id = "ViewGenreButton";
    let genre = element.id;
    myViewButton.onclick = function(){document.location = "Album.html?user=" + user + "&genres=" + genre};

    let viewIcon = document.createElement("span");
    viewIcon.className = "material-icons";
    viewIcon.innerHTML = "launch";
    myViewButton.appendChild(viewIcon);
    newCell.appendChild(myViewButton);

    if (loggedIn) {
      let newCell2 = row.insertCell();
      let myEditButton = document.createElement("button");
      myEditButton.className = "btn";
      myEditButton.id = "EditGenreButton";
      myEditButton.setAttribute("data-toggle", "modal");
      myEditButton.setAttribute("data-target", "#EditGenreModal");

      let editIcon = document.createElement("span");
      editIcon.className = "material-icons";
      editIcon.innerHTML = "create";
      myEditButton.appendChild(editIcon);
      let ID = element.id;
      let Name = element.name;
      myEditButton.onclick = function () {
        changeEditGenreModal(ID, Name);
      };
      newCell2.appendChild(myEditButton);

      let newCell3 = row.insertCell();
      let myDeleteButton = document.createElement("button");
      myDeleteButton.className = "btn";
      myDeleteButton.id = "DeleteGenreButton" + element.name;

      let deleteIcon = document.createElement("span");
      deleteIcon.className = "material-icons";
      deleteIcon.innerHTML = "delete";
      myDeleteButton.appendChild(deleteIcon);
      myDeleteButton.onclick = function () {
        deleteGenre(element.id);
      };
      newCell3.appendChild(myDeleteButton);
    }
  }
}

function generateAddGenreBtn(table) {
  let tableFooter = document.createElement("footer");
  let myAddGenreButton = document.createElement("button");
  myAddGenreButton.className = "btn btn-outline-primary";
  myAddGenreButton.innerHTML = "Add Genre";
  myAddGenreButton.id = "AddGenreButton";
  myAddGenreButton.setAttribute("data-toggle", "modal");
  myAddGenreButton.setAttribute("data-target", "#AddGenreModal");


  tableFooter.appendChild(myAddGenreButton);
  table.appendChild(tableFooter);
}

function deleteGenre(id) {
  fetch("http://localhost:8082/genres/delete/" + id, {
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

let GenreId;

function changeEditGenreModal(id, name) {
  let modalPH = document.getElementById("EditGenreName");
  modalPH.setAttribute("value", name);
  GenreId = id;
}

document
  .querySelector("form.EditGenre")
  .addEventListener("submit", function (stop) {
    stop.preventDefault();

    let formElements = document.querySelector("form.EditGenre").elements;
    console.log(formElements);

    let EditGenrename = formElements["EditGenreName"].value;


    console.log(EditGenrename);
    editGenre(EditGenrename, GenreId);
  });

function editGenre(name, GenreId) {
  let ID = parseInt(GenreId);
  fetch("http://localhost:8082/genres/update/" + GenreId, {
    method: "put",
    headers: {
      "Content-type": "application/json",
    },
    body: (json = JSON.stringify({
      "id": ID,
      "name": name
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

document
  .querySelector("form.Genre")
  .addEventListener("submit", function (stop) {
    stop.preventDefault();

    let formElements = document.querySelector("form.Genre").elements;
    console.log(formElements);
    let name = formElements["GenreName"].value;
    addGenre(name);
  });

function addGenre(name) {
  fetch("http://localhost:8082/genres/create", {
    method: "post",
    headers: {
      "Content-type": "application/json",
    },
    body: (json = JSON.stringify({
      "name": name
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

