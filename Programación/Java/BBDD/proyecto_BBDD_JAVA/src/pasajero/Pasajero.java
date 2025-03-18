package pasajero;

import serializer.Serializer;

public class Pasajero implements Serializer{
//Atributos
    private long id_pasajero;
    private String name;
    private String email;
    private String telefono;
//Constructores
    public Pasajero(){
        this(0,"","","");
    }
    
    public Pasajero(String Data){
        deserialize(Data);
    }

    public Pasajero(Pasajero pas){
        this.id_pasajero = pas.id_pasajero;
        this.name = pas.name;
        this.email = pas.email;
        this.telefono = pas.telefono;
    }

    public Pasajero(long id_pasajero, String name, String email, String telefono){
        this.id_pasajero = id_pasajero;
        this.name = name;
        this.email = email;
        this.telefono = telefono;
    }

//Getters y Setters
    public long getId_pasajero() {
        return id_pasajero;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setId_pasajero(long id_pasajero) {
        this.id_pasajero = id_pasajero;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

//Metodos  
    @Override
    public String toString() {
        return String.format("ID: %d, Nombre: %s, Email: %s, Telefono: %s", this.id_pasajero, this.name, this.email, this.telefono);
    }

    @Override
    public String serialize(){
        return String.format("%d;\"%s\";\"%s\";\"%s\";",this.id_pasajero,this.name, this.email, this.telefono);
    }
    public String substractAtribute(String data){
        String reString = data.substring(1, data.length()-1);
        return reString;
    }
    @Override
    public void deserialize(String t){
        String[] parts = t.split(";");
        this.id_pasajero =  Long.parseLong(substractAtribute(parts[0]));
        this.name = substractAtribute(parts[1]);
        this.email = substractAtribute(parts[2]);
        this.telefono = substractAtribute(parts[3]);
    }

}