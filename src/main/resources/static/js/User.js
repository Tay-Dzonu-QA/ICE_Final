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
      let welcome = document.querySelector('#userName');
      welcome.innerHTML = "Welcome back "+UserData.name;
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
      let tableDiv = document.createElement("div");
      let table = document.createElement("table");
      let Listname = document.createElement("h2");
      Listname.textContent = element.name;
      let data = Object.keys(element.tracks[0]);
      generateTableHeadPl(table, data);
      generateTablePl(table, element.tracks,element.name);
      tableDiv.appendChild(Listname);
      tableDiv.appendChild(table);
  
      //Need to add footer for deleta and edit track list
      let ID =element.id;
      let Name = element.name;
  
      let tableFooter = document.createElement("footer");
  
      let myAddTrackButton = document.createElement("button");
      myAddTrackButton.className = "btn btn-outline-primary";
      myAddTrackButton.innerHTML = "Add Track";
      myAddTrackButton.id = "AddTrackButton"+element.name;
      myAddTrackButton.setAttribute("data-toggle", "modal");
      myAddTrackButton.setAttribute("data-target", "#AddTrackModal");
      myAddTrackButton.onclick = function () {
        changeAddTrackModal(ID, Name);
      };
  
      let myEditButtonPL = document.createElement("button");
      myEditButtonPL.className = "btn btn-outline-primary";
      myEditButtonPL.innerHTML = "Edit";
      myEditButtonPL.id = "EditPLButton"+element.name;
      myEditButtonPL.setAttribute("data-toggle", "modal");
      myEditButtonPL.setAttribute("data-target", "#EditPLModal");
      myEditButtonPL.onclick = function () {
        changeEditPLModal(ID, Name);
      };
  
      let myDeleteButtonPL = document.createElement("button");
      myDeleteButtonPL.className = "btn btn-outline-primary";
      myDeleteButtonPL.innerHTML = "Delete";
      myDeleteButtonPL.id = "DeletePLButton"+element.name;
      myDeleteButtonPL.onclick = function () {
        deleteTrackList(ID);
      };
  
      tableFooter.appendChild(myAddTrackButton);
      tableFooter.appendChild(myEditButtonPL);
      tableFooter.appendChild(myDeleteButtonPL);
      tableDiv.appendChild(tableFooter);
  
      tableDiv.className = "col-lg";
      tables.appendChild(tableDiv);
      let seperator = document.createElement("div");
      seperator.className = "col-1";
      tables.appendChild(seperator);
    }
  }
