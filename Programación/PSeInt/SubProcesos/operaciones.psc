SubProceso ret <- mult(n1,n2)
	Definir ret Como Entero;
	ret <- 0;
	Definir cont Como Entero;
	para cont <-1 hasta n2 Hacer
		ret <- ret+n1;
	FinPara
FinSubProceso

SubProceso ret <- coci(n1,n2)
	
FinSubProceso

Algoritmo operaciones
	definir resultado Como Entero;
	resultado <- mult (3,5);
	Escribir resultado;
FinAlgoritmo
	