const inputUserName = document.getElementById("inputUserName");
const inputPassword = document.getElementById("inputPassword");
const submitButton = document.getElementById("submitButton");
inputUserName.addEventListener("input", () => {
    inputPassword.addEventListener("input", () => {
        submitButton.disabled = false;
        document.addEventListener("keydown", (e) =>{
            if(e.code === 'Enter'){
                login();
            }
        }, false);
    })
})
submitButton.addEventListener("click",() => {
    login();
})

async function login(){
    let usernameValue = inputUserName.value;
    let passwordValue = inputPassword.value;
    if(passwordValue){
        
        const headers = new Headers();
        headers.set('Authorization', usernameValue + ":" + passwordValue); // 'Basic ' has a space after it!
        const url = "http://localhost:5432/login";
        var httpResponse = await fetch(url, {headers: headers});
        if(httpResponse.status === 200){
            const body = await httpResponse.json();
            sessionStorage.setItem("key", body.user_id);
            location.href = 'C:/Work/HTML&CSS/project1/MainMenu.html';
        }else if (httpResponse.status === 205){
            alert("The username or password you entered does not match the records in our database. Please try again!");
            location.reload();
        }
    }
    
}