document.querySelector("form.createUser").addEventListener("submit",function(stop){
    stop.preventDefault();

    let formElements = document.querySelector("form.createUser").elements;
    let username = formElements["username"].value;
    let password = formElements["password"].value;
    let confirmPassword = formElements["secondPassword"].value;

    //updateRecord(name,foodgroup,price,weight,recipe);
    // console.log(username);
    // console.log(password);
    // console.log(confirmPassword);

    if(password.valueOf() !== confirmPassword.valueOf()){
        alert("Passwords do not match")
    }
    else{
        createAccount(username,password)
    }

})
let doesAccount = null;
function createAccount(username,password){

    doesAccountExist(username)
    if ( doesAccount !== null )alert("Account exists already");
    else{
        createRecord(password,username);
    }


}
// function doesAccountExist(username){
//     fetch('http://localhost:8082/users/username/'+username)
//         .then(
//             function(response) {
//                 if (response.status !== 200) {
//                     console.log('Looks like there was a problem. Status Code: ' +
//                         response.status);
//                     return;
//                 }
//                 response.json().then(function(data) {
//                     console.log(data);
//                 });
//             }
//         )
//         .catch(function(err) {
//             console.log('Fetch Error :-S', err);
//         });
// }

function doesAccountExist(id){

    fetch('http://localhost:8082/users/username/'+id)
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
                    doesAccount = data.username;

                    // document.getElementById("id").value = data.id;
                    // document.getElementById("name").value = data.name;
                    // document.getElementById("foodgroup").value = data.foodGroup;
                    // document.getElementById("price").value = data.price;
                    // document.getElementById("weight").value = data.weight;

                    //document.getElementById("noOfStrings").value = data.strings;
                    //document.getElementById("type").value = data.type;
                });
            }
        )
        .catch(function(err) {
            console.log('Fetch Error :-S', err);
        });


}

function createRecord(password,username){
    //let finalID = parseInt(id);
    fetch("http://localhost:8082/users/create", {
        method: 'POST',
        headers: {
            "Content-type": "application/json"
        },
        body: json = JSON.stringify( {

            "username": username,
            "password": password

        })
    })
        .then(json)
        .then(function (data) {
            alert("You have created an account")
            console.log('Request succeeded with JSON response', data);
        })
        .catch(function (error) {
            console.log('Request failed', error);
        });
}