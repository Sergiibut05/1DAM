Proceso sumarNNumeros
	Escribir "Escribe el número máximo que quieres sumar consecutivamente: "Sin Saltar;
	definir nMax Como Entero;
	leer nMax;
	definir contador Como Entero;
	definir suma Como Entero;
	suma <- 1;
	Para contador<-2 Hasta nMax Con Paso 1 Hacer
		suma<-suma + contador;
		escribir suma-contador, " + ", contador, " = ", suma;
	FinPara
	
FinProceso
