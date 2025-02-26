package ejercicio15;

import ejercicio14.FichaDomino;

public class Ejercicio15 {
    public static void main(String[] args) {
        int fichas = 1;
        FichaDomino fant = new FichaDomino((int)(Math.random()*7), (int)(Math.random()*7));
        System.out.print(fant);
        do {
            FichaDomino fsig = new FichaDomino((int)(Math.random()*7), (int)(Math.random()*7));
            if(fant.encaja(fsig.getIzq()) || fant.encaja(fsig.getDch())){
                fichas++;
                if(fsig.getIzq()!=fant.getDch())
                    fsig.voltea();
                fant = fsig;
                System.out.print(fsig);
                
            }
        } while (fichas != 8);
    }
    
}
