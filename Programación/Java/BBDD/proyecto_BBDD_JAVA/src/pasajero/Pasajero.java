package pasajero;

public class Pasajero {
    long id_pasajero;
    String name;
    String email;
    String telefono;

    public Pasajero(){
        this(0,"","","");
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

    
    @Override
    public String toString() {
        return String.format("ID: %d, Nombre: %s, Email: %s, Telefono: %s", this.id_pasajero, this.name, this.email, this.telefono);
    }

    @Override
    public String serialize(){
        return String.format("\"%d\";\"%s\";\"%s\";\"%s\";",this.id_pasajero,this.name, this.email, this.telefono);
    }
}