package pasajero;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.sql.Statement;
import java.util.ArrayList;

import CRUD.CRUD;

public class PasajeroCRUD implements CRUD<Pasajero> {
//Atributo
    Connection conn;

//Constructor
    public PasajeroCRUD(Connection conn) {
        this.conn = conn;
    }

//Métodos de la Interfaz
//Método para solicitar todos los pasajeros
    @Override
    public ArrayList<Pasajero> requestAll() throws SQLException {
        ArrayList<Pasajero> pasajeros = new ArrayList<Pasajero>();
        String sql = "SELECT * FROM pasajeros";

        try (PreparedStatement ps = this.conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Pasajero pasajero = new Pasajero();
                    pasajero.setId_pasajero(rs.getLong("id_pasajero"));
                    pasajero.setName(rs.getString("name"));
                    pasajero.setEmail(rs.getString("email"));
                    pasajero.setTelefono(rs.getString("telefono"));
                    pasajeros.add(pasajero);
                }
        }catch (SQLTimeoutException e){
            throw new SQLTimeoutException("Error al acceder a la base de datos"+ e.toString());
        }catch (SQLException e){
            throw new SQLException("Error al acceder a la base de datos"+ e.toString());
        }catch (Exception e){
            throw new SQLException("Ha ocurrido un error"+ e.toString());
        } finally {
            return pasajeros;
        }
    }
//Método para solicitar un pasajero por su id
    @Override
    public Pasajero requestById(long id) throws SQLException {
        Pasajero pasajero = null;
        String sql = String.format("SELECT * FROM pasajeros WHERE id_pasajero=%d",id);

        try (PreparedStatement ps = this.conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                pasajero = new Pasajero(id,rs.getString("name"),rs.getString("email"),rs.getString("telefono"));
            }
        } catch (SQLTimeoutException e){
            System.out.println("Error Al conectar con la Base de Datos "+ e.toString());
        } catch (SQLException e){
            System.out.println("Error Al conectar con la Base de Datos "+ e.toString());
        } catch (Exception e){
            System.out.println("Ha Ocurrido un Error "+ e.toString());
        }finally {
            return pasajero;
        }
    }
//Método para crear un pasajero
    @Override
    public long create(Pasajero model) throws SQLException {
        String sql = String.format("INSERT INTO pasajeros (name,email,telefono) VALUES (?,?,?);");
        PreparedStatement ps = this.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, model.getName());
        ps.setString(2, model.getEmail());
        ps.setString(3, model.getTelefono());
        ps.execute();
        ResultSet rs = ps.getGeneratedKeys();
        if(rs.next()){
            long id = rs.getLong(1);
            ps.close();
            return id;
        }else{
            throw new SQLException("Error al insertar el Pasajero");
        }
    }
//Método para actualizar un pasajero
    @Override
    public int update(Pasajero object) throws SQLException {
        String sql = String.format("UPDATE pasajeros SET name='%s', email='%s', telefono='%s' WHERE id_pasajero=%d",object.getName(),object.getEmail(),object.getTelefono(),object.getId_pasajero());
        PreparedStatement ps = this.conn.prepareStatement(sql);
        int affectedRows = ps.executeUpdate();
        if(affectedRows == 0){
            throw new SQLException("No Rows Affected");
        }else{
            return affectedRows;
        }
    }
//Método para eliminar un pasajero
    @Override
    public boolean delete(long id) throws SQLException {
        String sql = String.format("DELETE FROM pasajeros WHERE id_pasajero=%d",id);
        PreparedStatement ps = this.conn.prepareStatement(sql);
        int affected = ps.executeUpdate();
        ps.close();
        if(affected>0){
            return true;
        }else{
            throw new SQLException("No Rows Affected");
        }
    }
    
    
}
