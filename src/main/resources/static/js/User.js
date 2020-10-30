fetch("http://localhost:8082/users/read/" + user)
  .then(function (response) {
    if (response.status !== 200) {
      console.log(
        "Looks like there was a problem. Status Code: " + response.status
      );
      return;
    }
    //console.log(response);
    response.json().then(function (data) {
      let welcome = document.querySelector('#userName');
      welcome.innerHTML = "Welcome back "+data.name;
    });
  })
  .catch(function (err) {
    console.log("Fetch Error :-S", err);
  });
