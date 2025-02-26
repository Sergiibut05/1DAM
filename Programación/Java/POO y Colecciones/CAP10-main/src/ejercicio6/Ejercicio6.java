package ejercicio6;

import java.util.HashMap;

public class Ejercicio6 {
    public static void main(String[] args) {
        HashMap<String, String> usuarios = new HashMap<String, String>();
        usuarios.put("juan", "123456");
        usuarios.put("ale", "654321");
        System.out.println("Login");
        System.out.println("Usuario: ");
        String usuario = System.console().readLine();
        System.out.println("Contraseña: ");
        String password = System.console().readLine();
        if(usuarios.get(usuario)!= null && usuarios.get(usuario).equals(password)){
            System.out.println("Bienvenido al área restringida");
        }
        else{
            System.out.println("Usuario o contaseña incorrecta");
        }

    }
    
}
