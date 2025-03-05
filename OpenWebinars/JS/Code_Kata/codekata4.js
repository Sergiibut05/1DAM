

let result = function getCopyright(name, year, formatter){
    let copyright = formatter(name,year);
    return copyright;
};

let formatter = function(name, year) {
    return name + " - "+ year;
}

console.log(result("Armando", 2023, formatter));

(function(names, year){
    console.log(names, year);
})("nombre", 2023);