Proceso calculadora_simple
	Escribir "Introduzca un número: ";
	Definir num1 como real;
	leer num1;
	Escribir "Introduzca otro número: ";
	Definir num2 como real;
	leer num2;
	Escribir "Escoja una opción: ";
	Escribir "1. Sumar (x+y)";
	Escribir "2. Restar (x-y)";
	Escribir "3. Multiplicar (x*y)";
	Escribir "4. Dividir (x/y)";
	Definir opcion Como Entero;
	leer opcion;
	segun opcion hacer
		1:
			Escribir num1, " + ", num2, " = ", num1+num2;
		2:
			Escribir num1, " - ", num2, " = ", num1-num2;
		3:
			Escribir num1, " * ", num2, " = ", num1*num2;
		4:
			Escribir num1, " / ", num2, " = ", num1/num2;
	FinSegun
FinProceso
