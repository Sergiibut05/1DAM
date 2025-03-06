let inputName = document.querySelector(".saluda");
let buttoncase = document.querySelector(".boton")
document.getElementsByClassName("telfono")
buttoncase.addEventListener("click", function(event){
    if (inputName.value !== ""){
        alert("Hola "+inputName.value);
        inputName.value = "";
    }

    
});