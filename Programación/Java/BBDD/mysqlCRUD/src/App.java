import java.io.File;
import java.sql.*;

import alumnos.Alumno;
import alumnos.AlumnosService;
import connection.ConnectionPool;
import grupos.Grupo;
import grupos.GruposService;

import java.util.ArrayList;
import java.util.Scanner;
public class App {
    public static void listarGrupos(GruposService service){
        try {
            ArrayList<Grupo> grupos = service.requestAll();
            if(grupos.size()==0){
                System.out.println("No hay grupos de alumnos");
            }
            else{
                for(Grupo g : grupos){
                    System.out.println(g);
                }
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

    public static void listarAlumnos(AlumnosService service){
        try {
            ArrayList<Alumno> alumnos = service.requestAll();
            if(alumnos.size()==0){
                System.out.println("No hay grupos de alumnos");
            }
            else{
                for(Alumno g : alumnos){
                    System.out.println(g);
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
        String url = "jdbc:mysql://localhost:3306/crudbbdd";
        String usuario = "sergii";
        String clave = "1234";

        ConnectionPool pool = new ConnectionPool(url, usuario, clave);
        GruposService gservice = new GruposService(pool.getConnection());
        AlumnosService aservice = new AlumnosService(pool.getConnection());
        
        String file;
        String nombre, profesor;
        String apellidos; 
        long grupoId;
        long id;
        boolean salir = false;
        while(!salir){
            try {
                System.out.println("1. Editar Grupo de alumnos");
                System.out.println("2. Editar Alumnos");
                System.out.println("3. Salir");
                int opcion = Integer.parseInt(sc.nextLine());
                switch (opcion) {
                    case 1:
                        Boolean salir2 = false;
                        while(!salir2){
                            try {
                                // Conexión a la base de datos
                                System.out.println("1. Crear un grupo de alumnos");
                                System.out.println("2. Editar un grupo de alumnos");
                                System.out.println("3. Borrar un grupo de alumnos");
                                System.out.println("4. Visualizar grupos de alumnos");
                                System.out.println("5. Visualiza un grupo");
                                System.out.println("6. Salir");
                                opcion = Integer.parseInt(sc.nextLine());
                                switch (opcion) {
                                    case 1:
                                        System.out.println("Introduzca el nombre del grupo: ");
                                        nombre = sc.nextLine();
                                        System.out.println("Introduzca el nombre del tutor: ");
                                        profesor = sc.nextLine();
                                        try {
                                            id = gservice.create(new Grupo(0, nombre, profesor));    
                                            System.out.printf("Grupo creado correctamente (id: %d)\n", id);
                                        } catch (SQLException e) {
                                            if(e.getErrorCode() == 1062){
                                                System.out.println("El grupo ya existe con ese nombre");
                                            }
                                            
                                        }
                                        break;
                                    case 2:
                                        System.out.println("Elija el grupo a editar");
                                        listarGrupos(gservice);
                                        id = Integer.parseInt(sc.nextLine());
                                        System.out.println("Introduzca el nombre del grupo: ");
                                        nombre = sc.nextLine();
                                        System.out.println("Introduzca el nombre del tutor: ");
                                        profesor = sc.nextLine();
                                        try {
                                            int rowAffected = gservice.update(new Grupo(id, nombre, profesor));
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
                                        System.out.println("Elija el grupo a borrar");
                                        listarGrupos(gservice);
                                        id = Integer.parseInt(sc.nextLine());
                                        try {
                                            gservice.delete(id);
                                        } catch (SQLException e) {
                                            // TODO: handle exception
                                        }
                                        break;
                                    case 4:
                                        listarGrupos(gservice);
                                        break;
                                    case 5:
                                        System.out.println("Elija el grupo a visualizar");
                                        listarGrupos(gservice);
                                        id = Integer.parseInt(sc.nextLine());
                                        Grupo grupo = gservice.requestById(id);
                                        if(grupo!=null)
                                            System.out.println(grupo);
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
                                // Conexión a la base de datos
                                System.out.println("1. Crear un Alumno");
                                System.out.println("2. Editar un Alumno");
                                System.out.println("3. Borrar un Alumno");
                                System.out.println("4. Visualizar Alumnos");
                                System.out.println("5. Visualiza un Alumno");
                                System.out.println("6. Importar desde CSV");
                                System.out.println("7. Exportar a CSV");
                                System.out.println("8. Salir");
                                opcion = Integer.parseInt(sc.nextLine());
                                switch (opcion) {
                                    case 1:
                                        System.out.println("Introduzca el nombre del Alumno: ");
                                        nombre = sc.nextLine();
                                        System.out.println("Introduzca el apellido del Alumno: ");
                                        apellidos = sc.nextLine();
                                        System.out.println("Introduzca el Id del grupo: ");
                                        grupoId = Integer.parseInt(sc.nextLine());
                                        try {
                                            id = aservice.create(new Alumno(0, nombre, apellidos, grupoId));    
                                            System.out.printf("Grupo creado correctamente (id: %d)\n", id);
                                        } catch (SQLException e) {
                                            if(e.getErrorCode() == 1062){
                                                System.out.println("El Alumno ya existe con ese nombre");
                                            }
                                            
                                        }
                                        break;
                                    case 2:
                                        System.out.println("Elija el alumno a editar");
                                        listarAlumnos(aservice);
                                        id = Integer.parseInt(sc.nextLine());
                                        System.out.println("Introduzca el nombre del alumno: ");
                                        nombre = sc.nextLine();
                                        System.out.println("Introduzca los apelidos del alumno: ");
                                        apellidos = sc.nextLine();
                                        System.out.println("Introduzca el Id del grupo: ");
                                        grupoId = Integer.parseInt(sc.nextLine());
                                        try {
                                            int rowAffected = aservice.update(new Alumno(id, nombre, apellidos, grupoId));
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
                                        System.out.println("Elija el alumno a borrar");
                                        listarAlumnos(aservice);
                                        id = Integer.parseInt(sc.nextLine());
                                        try {
                                            aservice.delete(id);
                                        } catch (SQLException e) {
                                            // TODO: handle exception
                                        }
                                        break;
                                    case 4:
                                        listarAlumnos(aservice);
                                        break;
                                    case 5:
                                        System.out.println("Elija el alumno a visualizar");
                                        listarAlumnos(aservice);
                                        id = Integer.parseInt(sc.nextLine());
                                        Alumno alumno = aservice.requestById(id);
                                        if(alumno!=null)
                                            System.out.println(alumno);
                                        break;
                                    case 6:
                                        System.out.println("Escriba el nombre del fichero CSV desde el cual importar: ");
                                        file = sc.nextLine();
                                        aservice.importFromCSV(file);
                                    case 7:
                                        System.out.println("Escriba el nombre del fichero CSV desde al cual Exportar: ");
                                        file = sc.nextLine();
                                        aservice.exportToCSV(file);
                                    case 8:
                                        salir3 = true;
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
                    case 3:
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



