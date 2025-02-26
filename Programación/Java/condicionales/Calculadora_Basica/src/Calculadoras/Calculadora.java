package Calculadoras;

public class Calculadora {
    private double operando1;
    private double operando2;
    private String Operador;
    private double resultado;
    public Calculadora(double operando1, double operando2, String Operador, double resultado) {
        this.operando1 = operando1;
        this.operando2 = operando2;
        this.Operador = Operador;
        this.resultado = resultado;
    }
    
    public double getoperando1(){
        return operando1;
    }
    public void setoperando1(double a){
        this.operando1 = a;
    }
    public double getoperando2(){
        return operando2;
    }
    public void setoperando2(double b){
    this.operando2 = b;
    }
    public String getOperador(){
        return Operador;
    }
    public void setOperador(String Operador){
        this.Operador = Operador;
    }
    public double getresultado(){
        return resultado;
    }
    public void setresultado(double resultado){
        this.resultado = resultado;
    }
    public static double Operacion(Calculadora Calculadora){
        if (Calculadora.resultado != 0 && Calculadora.operando1 == 0) {
            Calculadora.operando1 = Calculadora.resultado;
        }
        switch(Calculadora.Operador){
            case "+":
            Calculadora.setresultado(Calculadora.operando1 + Calculadora.operando2);
            break;
            case"-":
            Calculadora.setresultado(Calculadora.operando1 - Calculadora.operando2);
            break;
            case"*":
            Calculadora.setresultado(Calculadora.operando1 * Calculadora.operando2);
            break;
            case"/":
            if (Calculadora.operando2 != 0) {
                Calculadora.setresultado(Calculadora.operando1 / Calculadora.operando2);
            } else {
                System.out.println("Error: División por cero no permitida.");
            }
            
            break;
        }
        Calculadora.operando1=0;
        return Calculadora.resultado;
    }
}

