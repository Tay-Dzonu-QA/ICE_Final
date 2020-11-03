const params = new URLSearchParams(window.location.search);
// const paramsAlbum = new URLSearchParams(window.location.search);
let loggedIn = false;
// let loggedInAlbum = false;
let albumsToView = "";
let artistOrGenre = "";
let albumOrTrack = "";
let tracksToView = "";
let singleTrack = false;
let user = 0;
// let userAlbum =0;
let playlistToView = "";
let order = "";

for (const param of params) {
  if (param[0] === "user") {
    user = param[1];
    if (user != 0) {
      loggedIn = true;
    }
  } else if (param[0] === "artists" || param[0] === "genres") {
    artistOrGenre = param[0];
    let artistOrGenreId = param[1];
    albumsToView = "/" + artistOrGenre + "/" + artistOrGenreId;
  } else if (param[0] === "playlists") {
    playlistToView = param[1];
  } else if (param[0] === "albums") {
    albumOrTrack = param[0];
    let albumOrTrackId = param[1];
    tracksToView = "/" + albumOrTrack + "/" + albumOrTrackId;
  } else if (param[0] === "tracks") {
    albumOrTrack = param[0];
    singleTrack = true;
    tracksToView = "/" + param[1];
  } else if (param[0] === "order") {
    order = "/" + param[1];
  }
}
