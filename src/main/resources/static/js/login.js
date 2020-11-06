
let isPasswordCorrect;
let isAccountCorrect;
let information;
document.querySelector("form.login-form").addEventListener("submit",function(stop){
    stop.preventDefault();
    console.log("clicked")
    let formElements = document.querySelector("form.login-form").elements;
    let username = formElements["login-username"].value;
    let password = formElements["login-password"].value;

    console.log(username);

    if (username !== null) {
        doesAccountExistTwo(username, password);
    }

})

function doesAccountExistTwo(username,password){

    fetch('http://localhost:8082/users/username/'+username)
        .then(
            function(response) {
                if (response.status !== 200) {
                    console.log('Looks like there was a problem. Status Code: ' +
                        response.status);
                    return;
                }
                //console.log(response);
                response.json().then(function(data) {
                    isAccountCorrect = data.username;
                    isPasswordCorrect = data.password;
                    console.log("Username", isAccountCorrect)
                    if (isAccountCorrect !== username)alert("This account does not exist")
                    else{
                        if(isPasswordCorrect !== password)alert("The password is incorrect")
                        else{
                            document.location = "User.html?user="+data.id;

                        }
                    }
                });
            }
        )
        .catch(function(err) {
            console.log('Fetch Error :-S', err);
        });


}

