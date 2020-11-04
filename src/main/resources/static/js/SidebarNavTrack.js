GenerateSideBarNav(user);

function GenerateSideBarNav(user){
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
          let albumSideBar = document.querySelector("#albumList");
          for(let element of AlbumData){
            let albumList = document.createElement("a");
            albumList.innerHTML = element.name;
            albumList.id="Order_Album"+element.id;
            albumList.href= "Track.html?user=" + user + "&albums=" + element.id;
            albumSideBar.appendChild(albumList);
          }
        });
      })
      .catch(function (err) {
        console.log("Fetch Error :-S", err);
      });
    
}