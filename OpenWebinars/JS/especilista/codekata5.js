// *******************************
// FUNCIONES GENERADORAS Y "YIELD"
// *******************************

// 1. Ejemplo básico de función generadora
function* contador() {
    yield 1;
    yield 2;
    yield 3;
}

const generador = contador();
console.log(generador.next()); // { value: 1, done: false }
console.log(generador.next()); // { value: 2, done: false }
console.log(generador.next()); // { value: 3, done: false }
console.log(generador.next()); // { value: undefined, done: true }

// *****************************************
// 2. Generador infinito para secuencia de números
function* infinito() {
    let i = 1;
    while (true) {
        yield i++;
    }
}

const genInf = infinito();
console.log(genInf.next().value); // 1
console.log(genInf.next().value); // 2
console.log(genInf.next().value); // 3

// ***************************************************
// 3. Uso de yield con parámetros en next(valor)
function* doble() {
    let num = yield "Envíame un número"; // Se pausa aquí
    yield num * 2;
}

const genDoble = doble();
console.log(genDoble.next().value);  // "Envíame un número"
console.log(genDoble.next(5).value); // 10

// ****************************************
// 4. Generador para paginar datos en lotes
function* paginar(array, tamano) {
    for (let i = 0; i < array.length; i += tamano) {
        yield array.slice(i, i + tamano);
    }
}

const datos = [1, 2, 3, 4, 5, 6, 7, 8, 9];
const paginas = paginar(datos, 3);

console.log(paginas.next().value); // [1, 2, 3]
console.log(paginas.next().value); // [4, 5, 6]
console.log(paginas.next().value); // [7, 8, 9]
console.log(paginas.next().value); // undefined (fin del generador)

// ****************************************
// CONCLUSIÓN
// - yield permite pausar y reanudar una función.
// - Los generadores son útiles para secuencias, iteradores y procesamiento eficiente.
// - Se pueden enviar valores a un generador usando next(valor).