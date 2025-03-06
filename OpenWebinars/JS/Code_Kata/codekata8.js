document.querySelector(".test").addEventListener("click", function(event){
    
    alert("click en el boton");
});

document.addEventListener("contextmenu", function (event){
    event.preventDefault();

    alert("menu contextual esta interviniendo");
});

window.addEventListener("click", function (event){
    console.log("hemos hecho click en la ventana de la web");
});