package ejercicio2.vehiculos;

public abstract class Vehiculo {
    private static int vehiculosCreados = 0;
    private static int kilometrosTotales = 0;
    protected int kilometros;

    protected Vehiculo(){
        vehiculosCreados++;
    }
    
    public void anda(int kms){
        kilometrosTotales += kms;
        this.kilometros += kms;
    } 

    public int getKilometros() {
        return kilometros;
    }

    public static int getKilometrosTotales() {
        return kilometrosTotales;
    }
    
}
