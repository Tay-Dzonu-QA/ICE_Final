const params = new URLSearchParams(window.location.search);
let loggedIn = false;
let user = 0;
for (const param of params) {
  if (param[0] === "user") {
    user = param[1];
    if (user != 0) {
      loggedIn = true;
    }
  }
}