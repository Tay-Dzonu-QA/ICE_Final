const params = new URLSearchParams(window.location.search);
let user =0;
console.log(params);
for (const param of params) {
  if (param[0] === "user") {
    user = param[1];
  } 
}

function GenerateSideBarNav(user){
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
          let artistSideBar = document.getElementById("artistList");
          for(let element of ArtistData){
            let artistList = document.createElement("a");
            artistList.innerHTML = element.name;
            artistList.href= "Album.html?user=" + user + "&artists=" + element.id;
            artistSideBar.appendChild(artistList);
          }
        });
      })
      .catch(function (err) {
        console.log("Fetch Error :-S", err);
      });
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
          let genreSideBar = document.getElementById("genreList");
          for(let element of GenreData){
            let genreList = document.createElement("a");
            genreList.innerHTML = element.name;
            genreList.href= "Album.html?user=" + user + "&artists=" + element.id;
            genreSideBar.appendChild(genreList);
          }
        });
      })
      .catch(function (err) {
        console.log("Fetch Error :-S", err);
      });

}