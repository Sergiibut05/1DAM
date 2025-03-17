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
    Connection conn;


    public PasajeroCRUD(Connection conn) {
        this.conn = conn;
    }


    @Override
    public ArrayList<Pasajero> requestAll() throws SQLException {
        ArrayList<Pasajero> pasajeros = new ArrayList<Pasajero>();
        String sql = "SELECT * FROM pasajero";

        try (PreparedStatement ps = this.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
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

    @Override
    public Pasajero requestById(long id) throws SQLException {
        Pasajero pasajero = null;
        String sql = String.format("SELECT * FROM pasajero WHERE id_pasajero=%d",id);

        try (PreparedStatement ps = this.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
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

    @Override
    public long create(Pasajero model) throws SQLException {
        String sql = String.format("INSERT INTO pasajeros (name,email,telefono) VALUES (?,?,?);",model.name,model.email,model.telefono);
        PreparedStatement ps = this.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, model.name);
        ps.setString(2, model.email);
        ps.setString(3, model.telefono);
        ps.execute();
        ResultSet rs = ps.getGeneratedKeys();
        if(rs.next()){
            long id = rs.getLong(1);
            ps.close();
            return id;
        }else{
            throw new SQLException("User Creation Error, No User has been Created");
        }
    }

    @Override
    public int update(Pasajero object) throws SQLException {
        String sql = String.format("UPDATE pasajeros SET name=%s, email=%s, telefono=%s WHERE id_pasajero=%d",object.name,object.email,object.telefono,object.id_pasajero);
        PreparedStatement ps = this.conn.prepareStatement(sql);
        int affectedRows = ps.executeUpdate();
        if(affectedRows == 0){
            throw new SQLException("No Rows Affected");
        }else{
            return affectedRows;
        }
    }

    @Override
    public boolean delete(long id) throws SQLException {
        String sql = String.format("DELETE FROM pasajeros WHERE id_pasajero=%d",id);
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
