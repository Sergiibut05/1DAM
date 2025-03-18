import java.io.File;
import java.sql.*;

import pasajero.*;
import reserva.*;
import vuelo.*;
import connection.ConnectionList;

import java.util.ArrayList;
import java.util.Scanner;
public class App {
    public static void listarreservas(ReservaCRUD service){
        try {
            ArrayList<Reserva> reservas = service.requestAll();
            if(reservas.size()==0){
                System.out.println("No hay reservas");
            }
            else{
                for(Reserva r : reservas){
                    System.out.println(r);
                }
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    public static void listarpasajeros(PasajeroCRUD service){
        try {
            ArrayList<Pasajero> pasajeros = service.requestAll();
            if(pasajeros.size()==0){
                System.out.println("No hay pasajeros");
            }
            else{
                for(Pasajero p : pasajeros){
                    System.out.println(p);
                }
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

    public static void listarvuelos(VueloCRUD service){
        try {
            ArrayList<Vuelo> vuelos = service.requestAll();
            if(vuelos.size()==0){
                System.out.println("No hay grupos de alumnos");
            }
            else{
                for(Vuelo v : vuelos){
                    System.out.println(v);
                }
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        // Configuración de la conexión a la base de datos
        String url = "jdbc:mysql://localhost:3306/aerolinea";
        String usuario = "sergii";
        String clave = "1234";

        ConnectionList pool = new ConnectionList(url, usuario, clave);
        PasajeroCRUD pservice = new PasajeroCRUD(pool.getConnection());
        VueloCRUD vservice = new VueloCRUD(pool.getConnection());
        ReservaCRUD rservice = new ReservaCRUD(pool.getConnection());
        
        
        String file;
        String origen, destino;
        Date fecha_salida, fecha_llegada;
        String nombre, email, telefono;
        String apellidos; 
        long grupoId;
        long id;
        boolean salir = false;
        while(!salir){
            try {
                System.out.println("1. Editar Vuelos");
                System.out.println("2. Editar Pasajeros");
                System.out.println("3. Editar Reservas");
                System.out.println("4. Salir");
                int opcion = Integer.parseInt(sc.nextLine());
                switch (opcion) {
                    case 1:
                        Boolean salir2 = false;
                        while(!salir2){
                            try {
                                // Conexión a la base de datos
                                System.out.println("1. Crear un Vuelo");
                                System.out.println("2. Editar un Vuelo");
                                System.out.println("3. Borrar un Vuelo");
                                System.out.println("4. Visualizar Vuelos");
                                System.out.println("5. Visualizar un Vuelo");        
                                System.out.println("6. Salir");
                                opcion = Integer.parseInt(sc.nextLine());
                                switch (opcion) {
                                    case 1:
                                        System.out.println("Introduzca el Origen del Vuelo: ");
                                        origen = sc.nextLine();
                                        System.out.println("Introduzca el Destino del Vuelo: ");
                                        destino = sc.nextLine();
                                        System.out.println("Introduzca la Fecha de Salida (yyyy-MM-dd): ");
                                        fecha_salida = Date.valueOf(sc.nextLine());
                                        System.out.println("Introduzca la Fecha de Llegada: ");
                                        fecha_llegada = Date.valueOf(sc.nextLine());
                                        try {
                                            id = vservice.create(new Vuelo(0, origen, destino, fecha_salida, fecha_llegada));    
                                            System.out.printf("Vuelo creado correctamente (id: %d)\n", id);
                                        } catch (SQLException e) {
                                            if(e.getErrorCode() == 1062){
                                                System.out.println("El vuelo ya existe con ese nombre");
                                            }
                                        }
                                        break;
                                    case 2:
                                        System.out.println("Escriba el ID del Vuelo a Editar:");
                                        id = Integer.parseInt(sc.nextLine());
                                        Vuelo vueloeditable = vservice.requestById(id);
                                        System.out.println("Introduzca el Origen del Vuelo: ");
                                        origen = sc.nextLine();
                                        if(origen.equals("")){
                                            origen = vueloeditable.getOrigen();
                                        }
                                        System.out.println("Introduzca el Destino del Vuelo: ");
                                        destino = sc.nextLine();
                                        if(destino.equals("")){
                                            destino = vueloeditable.getDestino();
                                        }
                                        System.out.println("Introduzca la Fecha de Salida (yyyy-MM-dd): ");
                                        String fechaSalidaStr = sc.nextLine();
                                        if(fechaSalidaStr.equals("")){
                                            fecha_salida = vueloeditable.getFecha_salida();
                                        }
                                        else
                                        fecha_salida = Date.valueOf(fechaSalidaStr);
                                        System.out.println("Introduzca la Fecha de Llegada: ");
                                        String fechaLlegadaStr = sc.nextLine();
                                        if(fechaLlegadaStr.equals("")){
                                            fecha_llegada = vueloeditable.getFecha_llegada();
                                        }
                                        else
                                        fecha_llegada = Date.valueOf(sc.nextLine());
                                        try {
                                            int rowAffected = vservice.update(new Vuelo(id, origen, destino, fecha_salida, fecha_llegada));
                                            if(rowAffected == 1)
                                                System.out.println("Grupo actualizado correctamente");
                                            else
                                                System.out.println("No se ha podido actualizar el grupo");
                                        } catch (SQLException e) {
                                            System.out.println("No se ha podido actualizar el grupo");
                                            System.out.println("Ocurrión una excepción: "+ e.getMessage());
                                        }
                                        break;
                                    case 3:
                                        System.out.println("Elija el Vuelo a borrar");
                                        listarvuelos(vservice);
                                        id = Integer.parseInt(sc.nextLine());
                                        try {
                                            vservice.delete(id);
                                        } catch (SQLException e) {
                                            // TODO: handle exception
                                        }
                                        break;
                                    case 4:
                                        listarvuelos(vservice);
                                        break;
                                    case 5:
                                        System.out.println("Elija el Vuelo a visualizar");
                                        listarvuelos(vservice);
                                        id = Integer.parseInt(sc.nextLine());
                                        Vuelo vuelo = vservice.requestById(id);
                                        if(vuelo!=null)
                                            System.out.println(vuelo);
                                        break;
                                    case 6:
                                        salir2 = true;
                                        break;
                                    default:
                                        break;
                                }
                                //TODO: Incluye llamadas para probar el servicio
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        break;
                    case 2:
                        Boolean salir3 = false;
                        while(!salir3){
                            try {
                                System.out.println("1. Crear un Pasajero");
                                System.out.println("2. Editar un Pasajero");
                                System.out.println("3. Borrar un Pasajero");
                                System.out.println("4. Visualizar Pasajeros");
                                System.out.println("5. Visualizar un Pasajero");
                                System.out.println("6. Salir");
                                opcion = Integer.parseInt(sc.nextLine());
                                switch (opcion) {
                                    case 1:
                                        System.out.println("Introduzca el nombre del Pasajero: ");
                                        nombre = sc.nextLine();
                                        System.out.println("Introduzca el email del Pasajero: ");
                                        email = sc.nextLine();
                                        System.out.println("Introduzca el teléfono del Pasajero: ");
                                        telefono = sc.nextLine();
                                        try {
                                            id = pservice.create(new Pasajero(0, nombre, email, telefono));    
                                            System.out.printf("Pasajero creado correctamente (id: %d)\n", id);
                                        } catch (SQLException e) {
                                            if(e.getErrorCode() == 1062){
                                                System.out.println("El pasajero ya existe con ese nombre");
                                            }
                                        }
                                        break;
                                    case 2:
                                        System.out.println("Escriba el ID del Pasajero a Editar:");
                                        id = Integer.parseInt(sc.nextLine());
                                        if(pservice.requestById(id) == null){
                                            System.out.println("No existe un pasajero con ese ID");
                                            break;
                                        }
                                        System.out.println("Introduzca el nombre del Pasajero: ");
                                        nombre = sc.nextLine();
                                        if(nombre.equals("")){
                                            nombre = pservice.requestById(id).getName();
                                        }
                                        System.out.println("Introduzca el email del Pasajero: ");
                                        email = sc.nextLine();
                                        if(email.equals("")){
                                            email = pservice.requestById(id).getEmail();
                                        }
                                        System.out.println("Introduzca el teléfono del Pasajero: ");
                                        telefono = sc.nextLine();
                                        if(telefono.equals("")){
                                            telefono = pservice.requestById(id).getTelefono();
                                        }
                                        try {
                                            int rowAffected = pservice.update(new Pasajero(id, nombre, email, telefono));
                                            if(rowAffected == 1)
                                                System.out.println("Pasajero actualizado correctamente");
                                            else
                                                System.out.println("No se ha podido actualizar el pasajero");
                                        } catch (SQLException e) {
                                            System.out.println("Ocurrió una excepción: "+ e.getMessage());
                                        }
                                        break;
                                    case 3:
                                        System.out.println("Elija el Pasajero a borrar");
                                        listarpasajeros(pservice);
                                        id = Integer.parseInt(sc.nextLine());
                                        try {
                                            pservice.delete(id);
                                        } catch (SQLException e) {
                                            System.out.println("No se ha podido borrar el pasajero");
                                            System.out.println("Ocurrió una excepción: "+ e.getMessage());
                                        }
                                        break;
                                    case 4:
                                        listarpasajeros(pservice);
                                        break;
                                    case 5:
                                        System.out.println("Elija el Pasajero a visualizar");
                                        listarpasajeros(pservice);
                                        id = Integer.parseInt(sc.nextLine());
                                        Pasajero pasajero = pservice.requestById(id);
                                        if(pasajero != null)
                                            System.out.println(pasajero);
                                        break;
                                    case 6:
                                        salir3 = true;
                                        break;
                                    default:
                                        break;
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        break;
                    case 3:
                        Boolean salir4 = false;
                        while(!salir4){
                            try {
                                System.out.println("1. Crear una Reserva");
                                System.out.println("2. Editar una Reserva");
                                System.out.println("3. Borrar una Reserva");
                                System.out.println("4. Visualizar Reservas");
                                System.out.println("5. Visualizar una Reserva");
                                System.out.println("6. Salir");
                                opcion = Integer.parseInt(sc.nextLine());
                                switch (opcion) {
                                    case 1:
                                        System.out.println("Introduzca la Fecha de la Reserva (yyyy-MM-dd): ");
                                        Date fecha_reserva = Date.valueOf(sc.nextLine());
                                        System.out.println("Introduzca el ID del Pasajero: ");
                                        long id_pasajero = Long.parseLong(sc.nextLine());
                                        System.out.println("Introduzca el ID del Vuelo: ");
                                        long id_vuelo = Long.parseLong(sc.nextLine());
                                        try {
                                            id = rservice.create(new Reserva(0, fecha_reserva, id_pasajero, id_vuelo));    
                                            System.out.printf("Reserva creada correctamente (id: %d)\n", id);
                                        } catch (SQLException e) {
                                            if(e.getErrorCode() == 1062){
                                                System.out.println("La reserva ya existe");
                                            }
                                            else{
                                                System.out.println("No se ha podido crear la reserva");
                                                System.out.println("Ocurrió una excepción: "+ e.getMessage());
                                            }
                                        }
                                        break;
                                    case 2:
                                        System.out.println("Escriba el ID de la Reserva a Editar:");
                                        id = Integer.parseInt(sc.nextLine());
                                        Reserva reservaEditable = rservice.requestById(id);
                                        System.out.println("Introduzca la Fecha de la Reserva (yyyy-MM-dd): ");
                                        String fechaReservaStr = sc.nextLine();
                                        if(fechaReservaStr.equals("")){
                                            fecha_reserva = reservaEditable.getFecha_reserva();
                                        } else {
                                            fecha_reserva = Date.valueOf(fechaReservaStr);
                                        }
                                        System.out.println("Introduzca el ID del Pasajero: ");
                                        String idPasajero = sc.nextLine();
                                        if(idPasajero.equals("")){
                                            id_pasajero = reservaEditable.getId_pasajero();
                                        } else {
                                            id_pasajero = Long.parseLong(idPasajero);
                                        }
                                        System.out.println("Introduzca el ID del Vuelo: ");
                                        String idVuelo = sc.nextLine();
                                        if(idVuelo.equals("")){
                                            id_vuelo = reservaEditable.getId_vuelo();
                                        } else {
                                            id_vuelo = Long.parseLong(idVuelo);
                                        }
                                        try {
                                            int rowAffected = rservice.update(new Reserva(id, fecha_reserva, id_pasajero, id_vuelo));
                                            if(rowAffected == 1)
                                                System.out.println("Reserva actualizada correctamente");
                                            else
                                                System.out.println("No se ha podido actualizar la reserva");
                                        } catch (SQLException e) {
                                            System.out.println("No se ha podido actualizar la reserva");
                                            System.out.println("Ocurrió una excepción: "+ e.getMessage());
                                        }
                                        break;
                                    case 3:
                                        System.out.println("Elija la Reserva a borrar");
                                        id = Integer.parseInt(sc.nextLine());
                                        try {
                                            rservice.delete(id);
                                        } catch (SQLException e) {
                                            System.out.println("No se ha podido borrar la reserva");
                                            System.out.println("Ocurrió una excepción: "+ e.getMessage());
                                        }
                                        break;
                                    case 4:
                                        listarreservas(rservice);
                                        break;
                                    case 5:
                                        System.out.println("Elija la Reserva a visualizar");
                                        id = Integer.parseInt(sc.nextLine());
                                        Reserva reserva = rservice.requestById(id);
                                        if(reserva != null)
                                            System.out.println(reserva);
                                        break;
                                    case 6:
                                        salir4 = true;
                                        break;
                                    default:
                                        break;
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        break;
                    case 4:
                        salir = true;
                        break;
                    default:
                        break;
                }
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }
            
        }
    } 
}