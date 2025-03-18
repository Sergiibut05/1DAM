package vuelo;
import java.sql.Date;

import serializer.Serializer;
public class Vuelo implements Serializer{
//Atributos
    private long id_vuelo;
    private String origen;
    private String destino;
    private Date fecha_salida;
    private Date fecha_llegada;
//Constructores
    public Vuelo(){
        this(0,"","",null,null);
    }
    public Vuelo(String Data){
        deserialize(Data);
    }
    public Vuelo(long id_vuelo, String origen, String destino, Date fecha_salida, Date fecha_llegada) {
        this.id_vuelo = id_vuelo;
        this.origen = origen;
        this.destino = destino;
        this.fecha_salida = fecha_salida;
        this.fecha_llegada = fecha_llegada;
    }
    public Vuelo(Vuelo vue){
        this.id_vuelo = vue.getId_vuelo();
        this.origen = vue.getOrigen();
        this.destino = vue.getDestino();
        this.fecha_llegada = vue.getFecha_llegada();
        this.fecha_salida = vue.getFecha_salida();
    }
    
//Getters y Setters
    public long getId_vuelo() {
        return id_vuelo;
    }
    public String getDestino() {
        return destino;
    }
    public String getOrigen() {
        return origen;
    }
    public Date getFecha_llegada() {
        return fecha_llegada;
    }
    public Date getFecha_salida() {
        return fecha_salida;
    }
    public void setDestino(String destino) {
        this.destino = destino;
    }
    public void setFecha_llegada(Date fecha_llegada) {
        this.fecha_llegada = fecha_llegada;
    }
    public void setFecha_salida(Date fecha_salida) {
        this.fecha_salida = fecha_salida;
    }public void setId_vuelo(long id_vuelo) {
        this.id_vuelo = id_vuelo;
    }public void setOrigen(String origen) {
        this.origen = origen;
    }

//Metodos
    @Override
    public String toString() {
        return String.format("ID del Vuelo=%d, Origen=%s, Destino=%s, Fecha de Salida=%s, Fecha de Llegada=%s", id_vuelo, origen, destino, fecha_salida, fecha_llegada);
    }
    @Override
    public String serialize() {
        return String.format("%d;\"%s\";\"%s\";\"%s\";\"%s\";", this.id_vuelo,this.origen,this.destino,this.fecha_salida,this.fecha_llegada);
    }
    public String substractAtribute(String data){
        String reString = data.substring(1, data.length()-1);
        return reString;
    }
    @Override
    public void deserialize(String pasajero) {
        String[] parts = pasajero.split(";");
        this.id_vuelo =  Long.parseLong(substractAtribute(parts[0]));
        this.origen = substractAtribute(parts[1]);
        this.destino = substractAtribute(parts[2]);
        this.fecha_salida = Date.valueOf(substractAtribute(parts[3]));
        this.fecha_llegada = Date.valueOf(substractAtribute(parts[4]));
    }
    
}
