package grupos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import CRUD.CRUD;

public class GruposService implements CRUD<Grupo>{
    Connection conn;
    public GruposService(Connection conn){
        this.conn = conn;
    }

    public ArrayList<Grupo> requestAll() throws SQLException{
        Statement statement = null;
        ArrayList<Grupo> result = new ArrayList<Grupo>();
        statement = this.conn.createStatement();   
        String sql = "SELECT id, nombre, profesor FROM Grupos";
        // Ejecución de la consulta
        ResultSet querySet = statement.executeQuery(sql);
        // Recorrido del resultado de la consulta
        while(querySet.next()) {
            int id = querySet.getInt("id");
            String nombre = querySet.getString("nombre");
            String profesor = querySet.getString("profesor");
            result.add(new Grupo(id, nombre, profesor));
        } 
        statement.close();    
        return result;
    }

    public Grupo requestById(long id) throws SQLException{
        Statement statement = null;
        Grupo result = null;
        statement = this.conn.createStatement();    
        String sql = String.format("SELECT id, nombre, profesor FROM Grupos WHERE id=%d", id);
        // Ejecución de la consulta
        ResultSet querySet = statement.executeQuery(sql);
        // Recorrido del resultado de la consulta
        if(querySet.next()) {
            String nombre = querySet.getString("nombre");
            String profesor = querySet.getString("profesor");
            result = new Grupo(id, nombre, profesor);
        }
        statement.close();    
        return result;
    }

    public long create(Grupo grupo) throws SQLException{
        String nombre = grupo.getNombre();
        String profesor = grupo.getProfesor();
        
        Statement statement = null;
        statement = this.conn.createStatement();    
        String sql = String.format("INSERT INTO Grupos (nombre, profesor) VALUES ('%s', '%s')", nombre, profesor);
        // Ejecución de la consulta
        int affectedRows = statement.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);
        if (affectedRows == 0) {
            throw new SQLException("Creating group failed, no rows affected.");
        }
        try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                long id = generatedKeys.getLong(1);
                statement.close();
                return id;
            }
            else {
                statement.close();
                throw new SQLException("Creating group failed, no ID obtained.");
            }
        }
    }

    public int update(Grupo grupo) throws SQLException{
        String nombre = grupo.getNombre();
        String profesor = grupo.getProfesor();
        long id = grupo.getId();
        
        Statement statement = null;
        statement = this.conn.createStatement();    
        String sql = String.format("UPDATE Grupos SET nombre = '%s', profesor = '%s' WHERE id=%d", nombre, profesor, id);
        // Ejecución de la consulta
        int affectedRows = statement.executeUpdate(sql);
        statement.close();
        if (affectedRows == 0)
            throw new SQLException("Creating user failed, no rows affected.");
        else
            return affectedRows;
    }

    public boolean delete(long id) throws SQLException{
        Statement statement = null;
        statement = this.conn.createStatement();    
        String sql = String.format("DELETE FROM Grupos WHERE id=%d", id);
        // Ejecución de la consulta
        int result = statement.executeUpdate(sql);
        statement.close();
        return result==1;
    }

    
}
