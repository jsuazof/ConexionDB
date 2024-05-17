package com.example.repositorio;

import java.sql.SQLException;
import java.util.List;

public interface Repositorio<T> {

    List<T> encontrarTodos() throws SQLException; 

        T obternerPorId(int id) throws SQLException;

        void guardar(T t) throws SQLException;

        void eliminar(int id) throws SQLException;

}
