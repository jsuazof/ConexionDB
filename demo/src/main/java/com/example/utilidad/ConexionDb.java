package com.example.utilidad;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;

public class ConexionDb {
    static int puerto = 3306;
    static String nombreBd = "tiendaE";
    
public final static String URL="jdbc:mysql://localhost:"+puerto+"/"+nombreBd; 

private static String usuario="root";
private static String pass="@Desafio2020";
private static BasicDataSource pool;

private static BasicDataSource obtenerPool() throws SQLException{
    if (pool==null){
        pool= new BasicDataSource();
        pool.setUrl(URL); //envia URL como rutan de acceso
        pool.setUsername(usuario);//usuario DB
        pool.setPassword(pass);//password DB
        pool.setInitialSize(3); //valor de los parametros iniciales
        pool.setMinIdle(3);// establece el tiempo minimo para establecer la conexión
        pool.setMaxIdle(10);// establece el tiempo maximo para establecer la conexión
    }
    return pool;
}
public static Connection obtenerConexion() throws SQLException{
    return obtenerPool().getConnection(); //retorna una conexion
}




}
