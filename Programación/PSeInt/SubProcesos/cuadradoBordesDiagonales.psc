SubProceso rellenar(matriz, ancho, alto)
	definir i, j Como Entero;
	para i<-0 hasta alto-1 Hacer
		para j<-0 hasta ancho-1 Hacer
			matriz[i,j]<-0;
		FinPara
	FinPara
FinSubProceso

SubProceso bordes(matriz, ancho, alto)
	definir i, j Como Entero;
	para i<-0 hasta alto-1 Hacer
		para j<-0 hasta ancho-1 Hacer
			si (i==0 | j==0 | i==alto-1 | j==ancho-1) Entonces
				matriz[i, j] <- 1;
			FinSi
			
		FinPara
		Escribir "";
	FinPara
FinSubProceso

SubProceso diagonalIzquierda(matriz, ancho, alto)
	definir i, j Como Entero;
	para i<-0 hasta alto-1 Hacer
		para j<-0 hasta ancho-1 Hacer
			si (i==j) Entonces
				matriz[i, j] <- 1;
			FinSi
		FinPara
		Escribir "";
	FinPara
FinSubProceso

SubProceso diagonalDerecha(matriz, ancho, alto)
	definir i, j Como Entero;
	para i<-0 hasta alto-1 Hacer
		para j<-0 hasta ancho-1 Hacer
			si (i+j==ancho-1) Entonces
				matriz[i, j] <- 1;
			FinSi
		FinPara
		Escribir "";
	FinPara
FinSubProceso

SubProceso muestra(matriz, ancho, alto)
	definir i, j Como Entero;
	para i<-0 hasta alto-1 Hacer
		para j<-0 hasta ancho-1 Hacer
			si matriz[i, j] == 1 Entonces
				Escribir " * " sin saltar;
			SiNo
				Escribir "   " sin saltar;
			FinSi
		FinPara
		Escribir "";
	FinPara
FinSubProceso




Proceso uWu
	Dimension matriz[20,20];
	definir matriz Como Entero;
	rellenar(matriz, 20, 20);
	bordes(matriz, 20, 20);
	diagonalIzquierda(matriz, 20, 20);
	diagonalDerecha(matriz, 20, 20);
	muestra(matriz, 20, 20);
FinProceso


