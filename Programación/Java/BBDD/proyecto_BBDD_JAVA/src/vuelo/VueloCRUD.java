package vuelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.spi.DirStateFactory.Result;

import CRUD.CRUD;
import pasajero.Pasajero;

public class VueloCRUD implements CRUD<Vuelo>{
//Atributo
    Connection conn;

//Constructor
    public VueloCRUD(Connection conn) {
        this.conn = conn;
    }
//Métodos de la Interfaz   
    @Override
    public ArrayList<Vuelo> requestAll() throws SQLException {
        ArrayList<Vuelo> vuelos = new ArrayList<>();
        String sql = "SELECT * FROM vuelos";

        try (PreparedStatement ps = this.conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while(rs.next()){
                Vuelo vuelo = new Vuelo();
                vuelo.setId_vuelo(rs.getLong("id_vuelo"));
                vuelo.setOrigen(rs.getString("origen"));
                vuelo.setDestino(rs.getString("destino"));
                vuelo.setFecha_salida(rs.getDate("fecha_salida"));
                vuelo.setFecha_llegada(rs.getDate("fecha_llegada"));
                vuelos.add(vuelo);
            }
        }catch (SQLTimeoutException e){
            throw new SQLTimeoutException("Error al acceder a la base de datos"+ e.toString());
        }catch (SQLException e){
            throw new SQLException("Error al acceder a la base de datos"+ e.toString());
        }catch (Exception e){
            throw new SQLException("Ha ocurrido un error"+ e.toString());
        } finally {
            return vuelos;
        }
    }
//Método para solicitar un vuelo por su id
    @Override
    public Vuelo requestById(long id) throws SQLException {
        Vuelo vuelo = null;
        String sql = String.format("SELECT * FROM vuelos WHERE id_vuelo=%d",id);

        try (PreparedStatement ps = this.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                vuelo = new Vuelo(id,rs.getString("origen"),rs.getString("destino"),rs.getDate("fecha_salida"),rs.getDate("fecha_llegada"));
            }
        } catch (SQLTimeoutException e){
            System.out.println("Error Al conectar con la Base de Datos "+ e.toString());
        } catch (SQLException e){
            System.out.println("Error Al conectar con la Base de Datos "+ e.toString());
        } catch (Exception e){
            System.out.println("Ha Ocurrido un Error "+ e.toString());
        }finally {
            return vuelo;
        }
    }
//Método para crear un vuelo
    @Override
    public long create(Vuelo model) throws SQLException {
        String sql = String.format("INSERT INTO vuelos (origen,destino,fecha_salida,fecha_llegada) VALUES (?,?,?,?);");
        PreparedStatement ps = this.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, model.getOrigen());
        ps.setString(2, model.getDestino());
        ps.setDate(3, model.getFecha_salida());
        ps.setDate(4, model.getFecha_llegada());
        ps.execute();
        ResultSet rs = ps.getGeneratedKeys();
        if(rs.next()){
            long id = rs.getLong(1);
            ps.close();
            return id;
        }else{
            throw new SQLException("Error al insertar el vuelo");
        }
    }
//Método para actualizar un vuelo
    @Override
    public int update(Vuelo object) throws SQLException {
        String sql = String.format("UPDATE vuelos SET origen='%s', destino='%s', fecha_salida='%s', fecha_llegada='%s' WHERE id_vuelo=%d",object.getOrigen(),object.getDestino(),object.getFecha_salida(),object.getFecha_llegada(),object.getId_vuelo());
        PreparedStatement ps = this.conn.prepareStatement(sql);
        int affectedRows = ps.executeUpdate();
        if(affectedRows == 0){
            throw new SQLException("No Rows Affected");
        }else{
            return affectedRows;
        }
    }
//Método para eliminar un vuelo
    @Override
    public boolean delete(long id) throws SQLException {
        String sql = String.format("DELETE FROM vuelos WHERE id_vuelo=%d",id);
        PreparedStatement ps = this.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        int affected = ps.executeUpdate();
        ps.close();
        if(affected>0){
            return true;
        }else{
            throw new SQLException("No Rows Affected");
        }
    }
    
}
