package com.example.repositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.model.Empleado;
import com.example.utilidad.ConexionDb;

public class EmpleadoRepositorio implements Repositorio<Empleado> {

    private Connection obtenerConexion() throws SQLException {
        return ConexionDb.obtenerConexion();
    }

    @Override
    public void eliminar(int id) throws SQLException {
        try (
                Connection connection = obtenerConexion(); // conexión a la DB
                PreparedStatement preparedStatement = connection
                        .prepareStatement("DELETE FROM empleados WHERE id = ?")) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }

    }

    @Override
    public List<Empleado> encontrarTodos() throws SQLException {
        List<Empleado> empleados = new ArrayList<>();
        try (
                Connection connection = obtenerConexion(); // conexión a la DB
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM empleados"))
                 {
                    while (resultSet.next())
                     {
                        Empleado empleado = crearEmpleado(resultSet);
                        empleados.add(empleado);

                    }
        }

        return empleados;

    }

    private Empleado crearEmpleado(ResultSet resultSet) throws SQLException {
        Empleado empleado = new Empleado();
        empleado.setId(resultSet.getInt("id"));
        empleado.setNombre(resultSet.getString("nombre"));
        empleado.setPrimerApellido(resultSet.getString("primerApellido"));
        empleado.setSegundoApellido(resultSet.getString("segundoApellido"));
        empleado.setEmail(resultSet.getString("email"));
        empleado.setSalario(resultSet.getFloat("salario"));
        return empleado;
    }

    @Override
    public void guardar(Empleado empleado) {
        String sql;
        if(empleado.getId()>0){

            sql = "UPDATE empleados SET nombre =?, primerApellido =?, segundoApellido =?, email =?, salario =? WHERE id =?";
        }
        else{
            sql = "INSERT INTO empleados (nombre, primerApellido, segundoApellido, email, salario) VALUES (?,?,?,?,?)";
        }
        try(
            Connection connection = obtenerConexion();
            PreparedStatement preparedStatement = connection.prepareStatement(sql))
        {
            preparedStatement.setString(1, empleado.getNombre());
            preparedStatement.setString(2, empleado.getPrimerApellido());
            preparedStatement.setString(3, empleado.getSegundoApellido());
            preparedStatement.setString(4, empleado.getEmail());
            preparedStatement.setFloat(5, empleado.getSalario());

            if(empleado.getId()>0)
            {
                preparedStatement.setInt(6, empleado.getId());
            }
            preparedStatement.executeUpdate();
            }
            catch(SQLException e){
            
                throw new RuntimeException(e);
                //e.printStackTrace();

            }
            
    }

    @Override
    public Empleado obternerPorId(int id) throws SQLException {

        Empleado empleado = null;
        try (
                Connection connection = obtenerConexion(); // conexión a la DB
                PreparedStatement preparedStatement = connection
                        .prepareStatement("SELECT * FROM empleados WHERE id =?"))
        {
            preparedStatement.setInt(1, id);
            try (
                    ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    empleado = crearEmpleado(resultSet);
                }
            }

        } 
        return empleado;
    }
   
}
