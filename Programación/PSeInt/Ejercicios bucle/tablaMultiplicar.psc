Proceso tablaMultiplicar
	Escribir "Escribe el n�mero del que quieres saber la tabla de m�ltiplicar: "Sin Saltar;
	definir n1 Como Entero;
	leer n1;
	definir resultado Como Entero;
	Para resultado<-1 Hasta 10 Con Paso 1 Hacer
		Escribir n1, " * ", resultado, " = ", n1*resultado;
	FinPara
	
FinProceso
