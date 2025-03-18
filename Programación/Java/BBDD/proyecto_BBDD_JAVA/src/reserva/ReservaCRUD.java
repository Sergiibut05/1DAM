package reserva;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.sql.Statement;
import java.util.ArrayList;
import CRUD.CRUD;


public class ReservaCRUD implements CRUD<Reserva>{
//Atributo
    Connection conn;

//Constructor
    public ReservaCRUD(Connection conn){
        this.conn = conn;
    }

//Métodos de la Interfaz
//Método para solicitar todas las reservas
    @Override
    public ArrayList requestAll() throws SQLException {
        ArrayList<Reserva> reservas = new ArrayList<>();
        String sql = "SELECT * FROM reservas";

        try (PreparedStatement ps = this.conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while(rs.next()){
                Reserva reserva = new Reserva();
                reserva.setId_reserva(rs.getLong("id_reserva"));
                reserva.setFecha_reserva(rs.getDate("fecha_reserva"));
                reserva.setId_pasajero(rs.getLong("id_pasajero"));
                reserva.setId_vuelo(rs.getLong("id_vuelo"));
                reservas.add(reserva);
            }
        }catch (SQLTimeoutException e){
            throw new SQLTimeoutException("Error al acceder a la base de datos"+ e.toString());
        }catch (SQLException e){
            throw new SQLException("Error al acceder a la base de datos"+ e.toString());
        }catch (Exception e){
            throw new SQLException("Ha ocurrido un error"+ e.toString());
        } finally {
            return reservas;
        }

    }
//Método para solicitar una reserva por su id
    @Override
    public Reserva requestById(long id) throws SQLException {
        Reserva reserva = null;
        String sql = String.format("SELECT * FROM reservas WHERE id_reserva=%d",id);

        try (PreparedStatement ps = this.conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                 reserva = new Reserva(id,rs.getDate("fecha_reserva"),rs.getLong("id_pasajero"),rs.getLong("id_vuelo"));
            }
        } catch (SQLTimeoutException e){
            System.out.println("Error Al conectar con la Base de Datos "+ e.toString());
        } catch (SQLException e){
            System.out.println("Error Al conectar con la Base de Datos "+ e.toString());
        } catch (Exception e){
            System.out.println("Ha Ocurrido un Error "+ e.toString());
        }finally {
            return reserva;
        }
    }
//Método para crear una reserva
    @Override
    public long create(Reserva model) throws SQLException {
        String sql = String.format("INSERT INTO reservas (fecha_reserva,id_pasajero,id_vuelo) VALUES (?,?,?);");
        PreparedStatement ps = this.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setDate(1, model.getFecha_reserva());
        ps.setLong(2, model.getId_pasajero());
        ps.setLong(3, model.getId_vuelo());
        ps.execute();
        ResultSet rs = ps.getGeneratedKeys();
        if(rs.next()){
            long id = rs.getLong(1);
            ps.close();
            return id;
        }else{
            throw new SQLException("Error al crear la reserva, no se ha creado ninguna reserva");
        }
    }
//Método para actualizar una reserva
    @Override
    public int update(Reserva object) throws SQLException {
        String sql = String.format("UPDATE reservas SET fecha_reserva='%s', id_pasajero=%d, id_vuelo=%d WHERE id_reserva=%d",object.getFecha_reserva(),object.getId_pasajero(),object.getId_vuelo(),object.getId_reserva());
        PreparedStatement ps = this.conn.prepareStatement(sql);
        int affectedRows = ps.executeUpdate();
        if(affectedRows == 0){
            throw new SQLException("No Rows Affected");
        }else{
            return affectedRows;
        }
    }
//Método para eliminar una reserva
    @Override
    public boolean delete(long id) throws SQLException {
        String sql = String.format("DELETE FROM reservas WHERE id_reserva=%d",id);
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
