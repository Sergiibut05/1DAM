Proceso uWu
	definir fila, espacios, asteriscos, n Como Entero;
	definir simbolo Como Caracter;
	Escribir "Define n: "Sin Saltar;
	leer n;
	para fila<-1 hasta n Hacer
		para espacios<-1 hasta n-fila Hacer
			Escribir " "Sin Saltar;
		FinPara
		Escribir "*" Sin Saltar;
		simbolo<-" ";
		si fila==n Entonces
			simbolo<-"*";
		FinSi
		para espacios<-1 hasta 2*fila-3 Hacer
			escribir simbolo Sin Saltar;
		FinPara
		si fila > 1 Entonces
			Escribir "*" Sin Saltar;
		FinSi
		Escribir "";
	FinPara
FinProceso


