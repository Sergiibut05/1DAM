/*
Array (namedCollection):

Uso: Almacenar una colección ordenada de elementos.
Caso de uso: Cuando necesitas una lista de elementos que pueden ser accedidos por su índice.
*/
// Definición de un array llamado namedCollection con cuatro nombres
let namedCollection = ["Armando", "Brenda", "Cristina", "Diana"];
// Imprime el array completo
console.log(namedCollection);
// Imprime el primer elemento del array
console.log(namedCollection[0]);


/*
Set (enumu):

Uso: Almacenar una colección de valores únicos.
Caso de uso: Cuando necesitas asegurarte de que no haya elementos duplicados en la colección.
*/
// Definición de un Set llamado enumu con varios tipos de datos
let enumu = new Set([1, 7, true, "Hola"]);
// Verifica si el Set contiene el número 7
console.log(enumu.has(7));
// Imprime el tamaño del Set
console.log(enumu.size);

// Añade un nuevo elemento al Set
enumu.add("Adios");


/*
Map (mapeo):

Uso: Almacenar pares clave-valor.
Caso de uso: Cuando necesitas una estructura de datos que permita asociar claves únicas a valores.
*/
// Definición de un Map llamado mapeo con pares clave-valor
let mapeo = new Map([
    [1, "Armando"],
    [2, "Brenda"],
]);

// Añade un nuevo par clave-valor al Map
mapeo.set(3, "Cristina");
// Imprime el valor asociado a la clave 2
console.log(mapeo.get(2));


/*
Objeto (obj):

Uso: Almacenar datos estructurados en pares clave-valor.
Caso de uso: Cuando necesitas representar una entidad con varias propiedades.
*/
// Definición de un objeto llamado obj con varias propiedades
let obj = {
    nombre: "Armando",
    edad: 21,
    estatura: 1.70,
    peso: 70,
    genero: "Masculino"
};
// Imprime el objeto completo
console.log(obj);
// Imprime el valor de la propiedad nombre del objeto
console.log(obj.nombre);


/*
Date (currentDate):

Uso: Trabajar con fechas y horas.
Caso de uso: Cuando necesitas obtener la fecha y hora actual o manipular fechas.
*/
// Obtiene la fecha y hora actual
let currentDate = new Date();
// Imprime la fecha y hora actual
console.log(currentDate);





