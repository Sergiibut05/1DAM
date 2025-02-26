function calcular() {
    const operando1 = parseFloat(document.getElementById("operando1").value);
    const operando2 = parseFloat(document.getElementById("operando2").value);
    const operador = document.getElementById("operador").value;
    let resultado;

    switch (operador) {
        case "sumar":
            resultado = operando1 + operando2;
            break;
        case "restar":
            resultado = operando1 - operando2;
            break;
        case "multiplicar":
            resultado = operando1 * operando2;
            break;
        case "dividir":
            resultado = operando2 !== 0 ? operando1 / operando2 : "Error: División por 0";
            break;
        default:
            resultado = "Operador no válido";
    }

    document.getElementById("resultado").value = resultado;
}
