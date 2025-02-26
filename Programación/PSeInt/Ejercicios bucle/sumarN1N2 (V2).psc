Proceso SumarEntreN1N2
	Escribir "Escribe el primer número a sumar: "Sin Saltar;
	definir n1 Como Entero;
	leer n1;
	Escribir "Escribe el último número a sumar: "Sin Saltar;
	definir n2 Como Entero;
	leer n2;
	definir i Como Entero;
	i <- 0;
	definir suma Como Entero;
	suma <- 0;
	Para i<-n1 Hasta n2 Con Paso 1 Hacer
		suma <- suma+i;
		Escribir suma-i, " + ", i, " = ", suma;
	FinPara

FinProceso
