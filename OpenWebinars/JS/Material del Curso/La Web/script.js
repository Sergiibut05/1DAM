
let buttonGreet = document.querySelector(".saludo");
let inputName = document.querySelector("input[name=\"nombre\"]");
const saludo = "Hola";

buttonGreet.addEventListener("click", function (event) {
    if (inputName.value != "") {
        alert(saludo + " " + inputName.value);
        inputName.value = '';
    } else {
        inputName.className = "error";
    }
});

inputName.addEventListener("focus", function (event){
    if (this.className == "error") {
        this.className = '';
    }
});