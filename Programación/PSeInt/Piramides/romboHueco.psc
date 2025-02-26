Proceso romboHueco
	definir cont, ast, esp, n, int Como Entero;
	definir simbolo Como Caracter;
	Escribir "Define la altura del rombo: "Sin Saltar;
	leer n;
	int <- 1;
	para cont<-1 Hasta n Hacer
		si int <= n/2 Entonces
			Escribir int;
			int <- int+1;
		FinSi
	FinPara
	para cont <- 1 hasta n/2 Hacer
		Escribir int;
		int <- int-1;
	FinPara
	Escribir int;
	int <- int-1;
FinProceso
