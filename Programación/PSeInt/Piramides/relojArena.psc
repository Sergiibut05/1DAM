Proceso sin_titulo
	definir fila, espacios, asteriscos, n Como Entero;
	Escribir "Define n: "Sin Saltar;
	leer n;
	para fila <- n Hasta 1 con paso -1 Hacer
		para espacios<-1 hasta n-fila Hacer
			Escribir " " Sin Saltar;
		FinPara
		para asteriscos<-1 hasta 2*fila-1 Hacer
			Escribir "*" Sin Saltar;
		FinPara
		Escribir "";
	FinPara
	
	para fila <- 1 Hasta n Hacer
		para espacios<-1 hasta n-fila Hacer
			Escribir " " Sin Saltar;
		FinPara
		para asteriscos<-1 hasta 2*fila-1 Hacer
			Escribir "*" Sin Saltar;
		FinPara
		Escribir "";
	FinPara
FinProceso
