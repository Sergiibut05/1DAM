package operacionesMatricesRepaso;

public class OperacionesMatricesRepaso {

    private static void rellenarMatriz(int[][] matriz){
        for(int i = 0; i < matriz.length; i++){
            for(int j = 0; j < matriz[0].length; j++){
                matriz[i][j] = (int)(Math.random()*10);
            }
        }
    }

    private static void pintarMatriz(int[][] matriz){
        for(int i = 0; i < matriz.length; i++){
            for(int j = 0; j < matriz[0].length; j++){
                System.out.printf("%-3d ", matriz[i][j]);
            }
            System.out.println();
        }
    }

    private static int[][] multiplicarMatriz(int[][] m1, int[][] m2){
        int[][] m3 = new int[m1.length][m2[0].length];
        for(int i = 0; i < m1.length; i++){
            for(int j = 0; j < m2[0].length; j++){
                for(int k = 0; k < m1[0].length; k++){
                    m3[i][j] += m2[k][j] * m1[i][k];
                }
            }
        }
        return m3;
    }

    private static int[][] sumarMatriz(int[][] m1, int[][] m2){
        if (m1.length != m2.length || m1[0].length != m2[0].length) {
            int[][] fallo = null;  
            return fallo;        
        }else{
            int[][] m3 = new int[m1.length][m1[0].length];
            for(int i = 0; i < m1.length; i++){
                for(int j = 0; j < m1[0].length; j++){
                    m3[i][j] = m1[i][j] + m2[i][j];
                }            
            }
            return m3;
        }
    }

    private static void elementoNeutro(int[][] m1, int[][] m2){
        boolean esNeutro = true;
        int[][] suma = sumarMatriz(m1, m2);
        for(int i = 0; i < m1.length; i++){
            for(int j = 0; j < m1[0].length; j++){
                if (m1[i][j] != suma[i][j]) {
                    esNeutro = false;
                    break;
                }
            }
        }
        if (esNeutro == true) {
            System.out.println("La suma de las matrices cumple la propiedad de elemento neutro.");
        }else{
            System.out.println("La suma de las matrices no cumple la propiedad de elemento neutro.");
        }
    }

    private static int[][] rellenarCeros(int[][] m1){
        for(int i = 0; i < m1.length; i++){
            for(int j = 0; j < m1[0].length; j++){
                m1[i][j] = 0;
            }
        }
        return m1;
    }

    private static boolean esConmutativa(int[][] m1, int[][] m2){
        boolean conmutativa = false;
        int[][] suma1 = sumarMatriz(m1, m2);
        int[][] suma2 = sumarMatriz(m2, m1);
        for(int i = 0; i < m1.length; i++){
            for(int j = 0; j < m1[0].length; j++){
                if (suma1[i][j] != suma2[i][j]) {
                    conmutativa = false;
                    break;
                }else{
                    conmutativa = true;
                }
            }
        }
        return conmutativa;
    }

    private static boolean asociativa(int[][] m1, int[][] m2, int[][] m6){
        int[][] suma1 = sumarMatriz(m2, m6);
        int[][] suma2 = sumarMatriz(m1, suma1);

        int[][] suma3 = sumarMatriz(m1, m2);
        int[][] suma4 = sumarMatriz(suma3, m6);

        for(int i = 0; i < m1.length; i++){
            for(int j = 0; j < m1[0].length; j++){
                if(suma2[i][j] != suma4[i][j]){
                    return false;
                }else{
                    
                }
            }
        }
        return true;
    }

    private static int[][] matrizOpuesta(int[][] m1){
        int[][] m1Opuesta = new int[m1.length][m1[0].length];
        for(int i = 0; i < m1.length; i++){
            for(int j = 0; j < m1[0].length; j++){
                m1Opuesta[i][j] = -m1[i][j]; 
            }
        }
        return m1Opuesta;
    }

    private static int[][] matrizIdentidad(){
        int[][] mIdentidad = new int[5][5];
        for(int i = 0; i < mIdentidad.length; i++){
            for(int j = 0; j < mIdentidad[0].length; j++){
                if (i == j) {
                    mIdentidad[i][j] = 1;
                }else{
                    mIdentidad[i][j] = 0;
                }
            }
        }
        return mIdentidad;
    }

    private static int[][] traspuesta(int[][] m1){
        int[][] mTraspuesta = new int[m1.length][m1[0].length];
        for(int i = 0; i < m1.length; i++){
            for(int j = 0; j < m1[0].length; j++){
                mTraspuesta[i][j] = m1[j][i];
            }
        }
        return mTraspuesta;
    }

    public static void main(String[] args) {
        try {
            //Se rellena y se pinta la matriz 1
            int[][] m1 = new int[3][3];
            System.out.println("Matriz 1:");
            rellenarMatriz(m1);
            pintarMatriz(m1);
            System.out.println();

            //Se rellena y se pinta la matriz 1
            System.out.println("Matriz 2:");
            int[][] m2 = new int[3][3];
            rellenarMatriz(m2);
            pintarMatriz(m2);
            System.out.println();

            //Se multiplican las dos matrices anteriores (m1 * m2)
            int[][] m3 = null;
            System.out.println("Matriz resultante de la multiplicacion:");
            m3 = multiplicarMatriz(m1, m2);
            pintarMatriz(m3);
            System.out.println();

            //Se suman las dos primeras matrices (m1 + m2)
            int[][] m4 = null;
            System.out.println("Matriz resultante de la suma:");
            m4 = sumarMatriz(m1, m2);
            pintarMatriz(m4);
            System.out.println();

            //Se verifica la propiedad del elemento neutro con la primera matriz (m1 + 0 = m1)
            System.out.println("Propiedad del elemento neutro:");
            int[][] m5 = new int[m1.length][m1.length];
            m5 = rellenarCeros(m5);
            elementoNeutro(m1, m5);
            System.out.println();

            //Se verifica la propiedad conmutativa con las dos primeras matrices (m1 + m2 = m2 + m1)
            System.out.println("Propiedad conmutativa:");
            if (esConmutativa(m1, m2)) {
                System.out.println("Las matrices cumplen la propiedad conmutativa.");
            }else{
                System.out.println("Las matrices no cumplen la propiedad conmutativa.");
            }
            System.out.println();

            //Se verifica la propiedad asociativa con las dos primeras matrices y una tercera que crearé ahora (m1 + (m2 + m6) = (m1 + m2) + m6)
            System.out.println("Propiedad asociativa:");
            int[][] m6 = new int[3][3];
            rellenarMatriz(m6);
            if (asociativa(m1, m2, m6)) {
                System.out.println("Las matrices cumplen la propiedad asociativa.");
            }else{
                System.out.println("Las matrices no cumplen la propiedad asociativa.");
            }
            System.out.println();

            //Se verifica la propiedad de la matriz opuesta (m1 + (−m1) = O).
            System.out.println("Propiedad de la matriz opuesta:");
            int[][] m1Inversa = matrizOpuesta(m1);
            int[][] sumaOpuesta = sumarMatriz(m1Inversa, m1);
            boolean esOpuesta = true;
            for(int i = 0; i < sumaOpuesta.length; i++){
                for(int j = 0; j < sumaOpuesta[0].length; j++){
                    if (sumaOpuesta[i][j] != 0) {
                        esOpuesta = false;
                        break;
                    }
                }
            }
            if (esOpuesta) {
                System.out.println("La matriz cumple la propiedad de la matriz opuesta.");
            }else{
                System.out.println("La matriz no cumple la propiedad de la matriz opuesta.");
            }
            System.out.println();

            //Se crea una matriz identidad.
            System.out.println("Matriz identidad:");
            int[][] mIdentidad = matrizIdentidad();
            pintarMatriz(mIdentidad);
            System.out.println();

            //Método para hacer un matriz traspuesta.
            int[][] m7 = new int[m1.length][m1[0].length];
            m7 = traspuesta(m1);
            System.out.println("Matriz original:");
            pintarMatriz(m1);
            System.out.println("Array traspuesta:");
            pintarMatriz(m7);
        
        } catch (NullPointerException e) {
            System.out.println("Las matrices deben de tener igual medida, y deben de ser cuadradas.");
        } catch (Exception e){
            System.out.println("Ha ocurrido un error inesperado.");
        }
    }
}