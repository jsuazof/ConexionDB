package com.example;

import java.sql.SQLException;

import com.example.model.Empleado;
import com.example.repositorio.EmpleadoRepositorio;
import com.example.repositorio.Repositorio;
import com.example.vista.SwingApp;

public class Main {
    public static void main(String[] args) throws SQLException {

        Repositorio<Empleado> repositorio = new EmpleadoRepositorio();
        repositorio.encontrarTodos().forEach(System.out::println);
        //repositorio.eliminar(5);
      
        //repositorio.encontrarTodos().forEach(System.out::println);
        SwingApp swingApp = new SwingApp();
        swingApp.setVisible(true);
        

}
}