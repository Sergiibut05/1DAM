Proceso calculadora
	Escribir "¿Cuantos números quieres sumar?";
	definir maximo Como Entero;
	leer maximo;
	definir contador como entero;
	contador <- 2;
	definir suma Como Entero;
	suma <- 1;
	Repetir
		suma <- suma + contador;
	Hasta Que suma <- maximo
	Escribir "El resultado es: ", suma;
FinProceso
