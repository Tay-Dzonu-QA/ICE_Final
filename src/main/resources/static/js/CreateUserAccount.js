let doesAccount;
document.querySelector("form.createUser").addEventListener("submit",function(stop){
    stop.preventDefault();

    let formElements = document.querySelector("form.createUser").elements;
    let name = formElements["name"].value;
    let username = formElements["username"].value;
    let password = formElements["password"].value;
    let confirmPassword = formElements["secondPassword"].value;

    if(password.valueOf() !== confirmPassword.valueOf()){
        alert("Passwords do not match")
    }
    else{
        createAccount(username,password,name)
    }

})

function createAccount(username,password,name){

    doesAccountExist(username,password,name)


}

function doesAccountExist(username,password,name){

    fetch('http://localhost:8082/users/username/'+username)
        .then(
            function(response) {
                if (response.status !== 200) {
                    console.log('Looks like there was a problem. Status Code: ' +
                        response.status);
                    return;
                }

                // Examine the text in the response
                console.log(response);
                response.json().then(function(data) {
                    if (data.username !== null){alert("this account exists")}
                    else{
                        createRecord(password,username,name)
                    }



                });
            }
        )
        .catch(function(err) {
            console.log('Fetch Error :-S', err);
        });


}

function createRecord(password,username,name){
    //let finalID = parseInt(id);
    fetch("http://localhost:8082/users/create", {
        method: 'POST',
        headers: {
            "Content-type": "application/json"
        },
        body: json = JSON.stringify( {

            "username": username,
            "password": password,
            "name" : name

        })
    })
        .then(json)
        .then(function (data) {
            alert("You have created an account")
            console.log('Request succeeded with JSON response', data);
            doesAccountExistTwo(username,password)
        })
        .catch(function (error) {
            console.log('Request failed', error);
        });
}