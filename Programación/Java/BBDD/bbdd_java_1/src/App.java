import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.*;
public class App {
    public static void main(String[] args) throws Exception {
        String url = "jdbc:mysql://localhost:3306/curso2425";
        String usuario = "sergii";
        String clave = "1234";
        
        //Declaración de variables
        Connection conexion = null;
        Statement sentencia = null;
        ResultSet resultado = null;
        
        try {
            // Conexión con la BBDD
            conexion = DriverManager.getConnection(url, usuario, clave);

            //Creación de la sentencia SQL
            String sql = "SELECT * FROM alumnos a JOIN nota b ON (a.id=b.id) JOIN materia c ON (b.CodMat = c.CodMat)";
            sentencia = conexion.createStatement();
            
            //Ejeccución de la consulta
            resultado = sentencia.executeQuery(sql);

            //Recorrido del resultado de la consulta
            while(resultado.next()) {
                int id = resultado.getInt("a.id");
                String nombre = resultado.getString("nombre");
                String apellidos = resultado.getString("apellido");
                int codMat = resultado.getInt("b.CodMat");
                int mark = resultado.getInt("Mark");
                String materia = resultado.getString("NomMateria");

                System.out.println("ID: " + id + ", Nombre: "+ nombre + ", Apellidos: " + apellidos + ", Código Materia: " + codMat + ", Nota: " + mark + ", Nombre Materia: "+ materia);
            }


        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            try {
                //Cierre de la conexión
                if (conexion != null) conexion.close();
                if (sentencia != null) sentencia.close();
                if (resultado != null) resultado.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
