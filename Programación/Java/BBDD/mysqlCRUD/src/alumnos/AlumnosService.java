package alumnos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Types;

import CRUD.CRUD;
import dataset.DataSetInterface;

public class AlumnosService implements CRUD<Alumno>, DataSetInterface{
    Connection conn;
    public AlumnosService(Connection conn){
        this.conn = conn;
    }

    public ArrayList<Alumno> requestAll() throws SQLException{
        Statement statement = null;
        ArrayList<Alumno> result = new ArrayList<Alumno>();
        statement = this.conn.createStatement();   
        String sql = "SELECT id, nombre, apellidos, grupo_id FROM alumnos";
        // Ejecución de la consulta
        ResultSet querySet = statement.executeQuery(sql);
        // Recorrido del resultado de la consulta
        while(querySet.next()) {
            int id = querySet.getInt("id");
            String nombre = querySet.getString("nombre");
            String apellidos = querySet.getString("apellidos");
            Long grupoId = querySet.getLong("grupo_id")==0?null:querySet.getLong("grupo_id");
            result.add(new Alumno(id, nombre, apellidos, grupoId));
        } 
        statement.close();    
        return result;
    }

    public Alumno requestById(long id) throws SQLException{
        Statement statement = null;
        Alumno result = null;
        statement = this.conn.createStatement();    
        String sql = String.format("SELECT id, nombre, apellidos FROM alumnos WHERE id=%d", id);
        // Ejecución de la consulta
        ResultSet querySet = statement.executeQuery(sql);
        // Recorrido del resultado de la consulta
        if(querySet.next()) {
            String nombre = querySet.getString("nombre");
            String apellidos = querySet.getString("apellidos");
            
            Long grupoId = querySet.getLong("grupo_id")==0?null:querySet.getLong("grupo_id");
            result = new Alumno(id, nombre, apellidos, grupoId);
        }
        statement.close();    
        return result;
    }

    public long create(Alumno object) throws SQLException{
        String nombre = object.getNombre();
        String apellidos = object.getApellidos();
        //Statement statement = null;
        //statement = this.conn.createStatement();    
        
        //String sql = String.format("INSERT INTO alumnos (nombre, apellidos, grupo_id) VALUES ('%s', '%s', NULL)", nombre, apellidos);
        String sqlaux = String.format("INSERT INTO alumnos (nombre, apellidos, grupo_id) VALUES (?, ?, ?)");
        PreparedStatement prepst = this.conn.prepareStatement(sqlaux, Statement.RETURN_GENERATED_KEYS);
        prepst.setString(1, object.getNombre());
        prepst.setString(2, object.getApellidos());
        if(object.getGrupoId()==null)
            prepst.setNull(3,Types.INTEGER);
        else
            prepst.setLong(3, object.getGrupoId());
        // Ejecución de la consulta
        //int affectedRows = statement.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);
        prepst.execute();
        ResultSet keys = prepst.getGeneratedKeys();
        if(keys.next()){
            long id = keys.getLong(1);
                prepst.close();
                return id;
        }
        else{
            throw new SQLException("Creating user failed, no rows affected.");
        }
        /*
        if (affectedRows == 0) {
            throw new SQLException("Creating user failed, no rows affected.");
        }
        try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                long id = generatedKeys.getLong(1);
                statement.close();
                return id;
            }
            else {
                statement.close();
                throw new SQLException("Creating user failed, no ID obtained.");
            }
        }
        */
    }

    public int update(Alumno object) throws SQLException{
        long id = object.getId();
        String nombre = object.getNombre();
        String apellidos = object.getApellidos();
        Statement statement = null;
        statement = this.conn.createStatement();    
        String sql = String.format("UPDATE alumnos SET nombre = '%s', apellidos = '%s' WHERE id=%d", nombre, apellidos, id);
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
        String sql = String.format("DELETE FROM alumnos WHERE id=%d", id);
        // Ejecución de la consulta
        int result = statement.executeUpdate(sql);
        statement.close();
        return result==1;
    }

    @Override
    public void importFromCSV(String file) throws Exception {
        BufferedReader br = null;
        PreparedStatement prep = null;
        try {
            br = new BufferedReader(new FileReader(file, StandardCharsets.UTF_8));
            String line = "";
            while((line=br.readLine())!= null){
                Alumno al = new Alumno(line);
                String sql = "INSERT INTO alumnos (id, nombre, apellidos, grupo_id) VALUES (?, ?, ?, ?) ON DUPLICATE KEY UPDATE nombre=VALUES(nombre), apellidos=VALUES(apellidos), grupo_id=VALUES(grupo_id)";
                prep = this.conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
                prep.setInt(1, (int)al.id);
                prep.setString(2, al.nombre);
                prep.setString(3, al.apellidos);
                if(al.grupoId!=null)
                    prep.setInt(4, (int)al.grupoId.longValue());
                else
                    prep.setNull(4, Types.INTEGER);
                prep.execute();
            }    
        } catch (IOException e) {
            throw new Exception("Ocurrión un error de E/S"+ e.toString());
        } catch (SQLTimeoutException e){
            throw new Exception("Ocurrión un error al acceder a la base de datos"+ e.toString());
        } catch (SQLException e){
            throw new Exception("Ocurrión un error al acceder a la base de datos"+ e.toString());
        } catch (Exception e){
            throw new Exception("Ocurrión un error "+ e.toString());
        } finally {
            if(prep != null)
                prep.close();
            if(br != null)
                br.close();
        }

        
    }

    @Override
    public void exportToCSV(String file) throws Exception {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(file, StandardCharsets.UTF_8));
            ArrayList<Alumno> alumnos = this.requestAll();
            for(Alumno al:alumnos){
                bw.write(al.serialize()+"\n");
            }
            bw.close();
        } catch(IOException e){
            throw new Exception("Ocurrión un error de E/S "+ e.toString());
        } catch(SQLException e){
            throw new Exception("Ocurrión un error al acceder a la base de datos "+ e.toString());
        }catch (Exception e) {
            throw new Exception("Ocurrión un error "+ e.toString());
        } finally {
            if(bw!=null)
                bw.close();
        }
        
    }

}
