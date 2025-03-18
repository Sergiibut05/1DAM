package reserva;

import java.sql.Date;

import serializer.Serializer;

public class Reserva implements Serializer{
//Atributos
    private long id_reserva;
    private Date fecha_reserva;
    private long id_pasajero;
    private long id_vuelo;
//Constructores
    public Reserva(long id_reserva, Date fecha_reserva, long id_pasajero, long id_vuelo){
        this.id_reserva = id_reserva;
        this.fecha_reserva = fecha_reserva;
        this.id_pasajero = id_pasajero;
        this.id_vuelo = id_vuelo;
    }
    public Reserva(){
        this(0, null, 0, 0);
    }
    public Reserva(Reserva res){
        this.id_reserva = res.getId_reserva();
        this.fecha_reserva = res.getFecha_reserva();
        this.id_pasajero = res.getId_pasajero();
        this.id_vuelo = res.getId_vuelo();
    }
    public Reserva(String data){
        deserialize(data);
    }

//Getters y Setters
    public void setId_reserva(long id_reserva) {
        this.id_reserva = id_reserva;
    }
    public void setId_pasajero(long id_pasajero) {
        this.id_pasajero = id_pasajero;
    }
    public void setId_vuelo(long id_vuelo) {
        this.id_vuelo = id_vuelo;
    }
    public void setFecha_reserva(Date fecha_reserva) {
        this.fecha_reserva = fecha_reserva;
    }
    public Date getFecha_reserva() {
        return fecha_reserva;
    }
    public long getId_pasajero() {
        return id_pasajero;
    }
    public long getId_reserva() {
        return id_reserva;
    }
    public long getId_vuelo() {
        return id_vuelo;
    }
//Metodos
    @Override
    public String toString() {
        return String.format("ID de la Reserva: %d, Fecha de la Reserva: %s, ID del Pasajero: %s, ID del Vuelo: %s", this.id_reserva, this.fecha_reserva, this.id_pasajero, this.id_vuelo);
    }

    @Override
    public String serialize() {
        return String.format("%d;\"%s\";%d;%d;", this.id_reserva,this.fecha_reserva,this.id_pasajero,this.id_vuelo);
    }
    public String substractAtribute(String data){
        String reString = data.substring(1, data.length()-1);
        return reString;
    }
    @Override
    public void deserialize(String t) {
        String[] parts = t.split(";");
        this.id_reserva =  Long.parseLong(substractAtribute(parts[0]));
        this.fecha_reserva = Date.valueOf(substractAtribute(parts[1]));
        this.id_pasajero =  Long.parseLong(substractAtribute(parts[2]));
        this.id_vuelo =  Long.parseLong(substractAtribute(parts[3]));
    }

}
