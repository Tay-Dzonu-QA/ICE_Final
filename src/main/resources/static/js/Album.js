getAlbums(loggedIn, albumsToView, user, artistOrGenre);

function getAlbums(loggedIn, albumsToView, user, artistOrGenre) {
    fetch("http://localhost:8082/albums/read" + albumsToView)
        .then(function(response) {
            if (response.status !== 200) {
                console.log(
                    "Looks like there was a problem. Status Code: " + response.status
                );
                return;
            }
            // Examine the text in the response
            response.json().then(function(AlbumData) {
                let title = document.querySelector("#AlbumTitle");
                if (artistOrGenre === "artists") {
                    title.innerHTML = "Albums by " + AlbumData[0].artist.name;
                } else if (artistOrGenre === "genres") {
                    title.innerHTML = AlbumData[0].genre.name + " Albums";
                } else {
                    title.innerHTML = "Albums";
                }

                let table = document.querySelector("#AlbumTable");
                let data = Object.keys(AlbumData[0]);

                generateTableHeadAl(table, data, loggedIn);
                generateTableAl(table, AlbumData, loggedIn, user);
                if (loggedIn == true) {
                    generateAddAlbumBtn(table);
                }

            });
        })
        .catch(function(err) {
            console.log("Fetch Error :-S", err);
        });
}

function generateTableHeadAl(table, data, loggedIn) {
    let thead = table.createTHead();
    let row = thead.insertRow();
    for (let key of data) {
        if (key === "id") {
            continue;
          }
        let th = document.createElement("th");
        let text = document.createTextNode(key);
        th.appendChild(text);
        row.appendChild(th);
    }
    let th = document.createElement("th");
    th.className = "btnCol";
    let text = document.createTextNode("");
    th.appendChild(text);
    row.appendChild(th);
    // if (loggedIn == true) {
    //     let th2 = document.createElement("th");
    //     let text2 = document.createTextNode("Edit");
    //     th2.appendChild(text2);
    //     row.appendChild(th2);

    //     let th3 = document.createElement("th");
    //     let text3 = document.createTextNode("Delete");
    //     th3.appendChild(text3);
    //     row.appendChild(th3);
    // }
}

function generateTableAl(table, AlbumData, loggedIn) {
    changeAddAlbumModal();
    let modalEditAlbumArtist = document.getElementById("EditAlbumArtist");
    addArtistList(modalEditAlbumArtist);

    let modalEditAlbumGenre = document.getElementById("EditAlbumGenre");
    addGenreList(modalEditAlbumGenre);

    for (let element of AlbumData) {
        let row = table.insertRow();
        row.className = "dataRow";
        for (key in element) {
            if (key === "id") {
                continue;
              }
            let cell = row.insertCell();
            let text = document.createTextNode(element[key]);
            if (key === "tracks") {
                let tracksNo = 0;
                for (tracks of element[key]) {
                    tracksNo += 1;
                }
                text = document.createTextNode(tracksNo);
            }
            if (key === "genre" || key === "artist") {
                text = document.createTextNode(element[key].name)
            }
            cell.appendChild(text);
        }
        let newCell = row.insertCell();
        let myViewButton = document.createElement("button");
        myViewButton.className = "btn";
        myViewButton.id = "ViewAlbumButton";
        myViewButton.onclick = function() { document.location = 'Track.html?user=' + user + '&albums=' + element.id };

        let viewIcon = document.createElement("span");
        viewIcon.className = "material-icons";
        viewIcon.innerHTML = "launch";
        myViewButton.appendChild(viewIcon);
        newCell.appendChild(myViewButton);

        // if (loggedIn == true) {
        //     let newCell2 = row.insertCell();
        //     let myEditButton = document.createElement("button");
        //     myEditButton.className = "btn";
        //     myEditButton.id = "EditAlbumButton";
        //     myEditButton.setAttribute("data-toggle", "modal");
        //     myEditButton.setAttribute("data-target", "#EditAlbumModal");

        //     let editIcon = document.createElement("span");
        //     editIcon.className = "material-icons";
        //     editIcon.innerHTML = "create";
        //     myEditButton.appendChild(editIcon);
        //     let ID = element.id;
        //     let Name = element.name;
        //     let Cover = element.cover;

        //     myEditButton.onclick = function() {
        //         changeEditAlbumModal(ID, Name, Cover);
        //     };

        //     newCell2.appendChild(myEditButton);

        //     let newCell3 = row.insertCell();
        //     let myDeleteButton = document.createElement("button");
        //     myDeleteButton.className = "btn";
        //     myDeleteButton.id = "DeleteAlbumButton" + element.name;

        //     let deleteIcon = document.createElement("span");
        //     deleteIcon.className = "material-icons";
        //     deleteIcon.innerHTML = "delete";
        //     myDeleteButton.appendChild(deleteIcon);
        //     myDeleteButton.onclick = function() {
        //         deleteAlbum(element.id);
        //     };
        //     newCell3.appendChild(myDeleteButton);
        // }
    }
}

function generateAddAlbumBtn(table) {
    let tableFooter = document.createElement("footer");
    let myAddAlbumButton = document.createElement("button");
    myAddAlbumButton.className = "btn btn-outline-primary";
    myAddAlbumButton.innerHTML = "Add Album";
    myAddAlbumButton.id = "AddAlbumButton";
    myAddAlbumButton.setAttribute("data-toggle", "modal");
    myAddAlbumButton.setAttribute("data-target", "#AddAlbumModal");

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
        .then(function(data) {
            console.log("Request succeeded with JSON response", data);
            location.reload();
        })
        .catch(function(error) {
            console.log("Request failed", error);
        });
}

let AlbumId;

function changeEditAlbumModal(id, name, cover) {
    let modalEditAlbumName = document.getElementById("EditAlbumName");
    modalEditAlbumName.setAttribute("value", name);
    let modalEditAlbumCover = document.getElementById("EditAlbumCover");
    modalEditAlbumCover.setAttribute("value", cover);

    AlbumId = id;
}

function changeAddAlbumModal() {
    let modalEditAlbumArtist = document.getElementById("AlbumArtist");
    addArtistList(modalEditAlbumArtist);
    let modalEditAlbumGenre = document.getElementById("AlbumGenre");
    addGenreList(modalEditAlbumGenre);
}

function addArtistList(modalArtistList) {
    fetch("http://localhost:8082/artists/read")
        .then(function(response) {
            if (response.status !== 200) {
                console.log(
                    "Looks like there was a problem. Status Code: " + response.status
                );
                return;
            }
            // Examine the text in the response
            response.json().then(function(ArtistData) {
                for (let element of ArtistData) {
                    let artistList = document.createElement("option");
                    artistList.innerHTML = element.id + ". " + element.name;
                    modalArtistList.appendChild(artistList);
                }
            });
        })
        .catch(function(err) {
            console.log("Fetch Error :-S", err);
        });
}


function addGenreList(modalArtistList) {
    fetch("http://localhost:8082/genres/read")
        .then(function(response) {
            if (response.status !== 200) {
                console.log(
                    "Looks like there was a problem. Status Code: " + response.status
                );
                return;
            }
            // Examine the text in the response
            response.json().then(function(GenreData) {
                for (let element of GenreData) {
                    let GenreList = document.createElement("option");
                    GenreList.innerHTML = element.id + ". " + element.name;
                    modalArtistList.appendChild(GenreList);
                }
            });
        })
        .catch(function(err) {
            console.log("Fetch Error :-S", err);
        });
}

document
    .querySelector("form.EditAlbum")
    .addEventListener("submit", function(stop) {
        stop.preventDefault();

        let formElements = document.querySelector("form.EditAlbum").elements;

        let EditAlbumName = formElements["EditAlbumName"].value;
        let EditAlbumCover = formElements["EditAlbumCover"].value;
        let EditAlbumArtist = formElements["EditAlbumArtist"].value;
        let AlbumArtsit = EditAlbumArtist.split(".");
        let AlbumArtistId = parseInt(AlbumArtsit[0]);
        let EditAlbumGenre = formElements["EditAlbumGenre"].value;
        let AlbumGenre = EditAlbumGenre.split(".");
        let AlbumGenreId = parseInt(AlbumGenre[0]);

        let AlbumId1 = parseInt(AlbumId);
        editAlbum(EditAlbumName, AlbumId1, EditAlbumCover, AlbumArtistId, AlbumGenreId);
    });

function editAlbum(name, AlbumId, cover, artist, genre) {
    fetch("http://localhost:8082/albums/update/" + AlbumId, {
            method: "put",
            headers: {
                "Content-type": "application/json",
            },
            body: (json = JSON.stringify({
                "id": AlbumId,
                "name": name,
                "artist": {
                    "id": artist
                },
                "genre": {
                    "id": genre
                },
                "cover": cover
            })),
        })
        .then(json)
        .then(function(data) {
            console.log("Request succeeded with JSON response", data);
            location.reload();
        })
        .catch(function(error) {
            console.log("Request failed", error);
        });
}

document
    .querySelector("form.Album")
    .addEventListener("submit", function(stop) {
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

        addAlbum(AlbumName, AlbumCover, AlbumArtistId, AlbumGenreId)
    });

function addAlbum(name, cover, artist, genre) {
    fetch("http://localhost:8082/albums/create", {
            method: "post",
            headers: {
                "Content-type": "application/json",
            },
            body: (json = JSON.stringify({
                "name": name,
                "artist": {
                    "id": artist
                },
                "genre": {
                    "id": genre
                },
                "cover": cover
            })),
        })
        .then(json)
        .then(function(data) {
            console.log("Request succeeded with JSON response", data);
            location.reload();
        })
        .catch(function(error) {
            console.log("Request failed", error);
        });
}