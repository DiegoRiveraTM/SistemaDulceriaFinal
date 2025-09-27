package com.example.sistemadulceria.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DatabaseConnection
{
    public static Connection getConnection()
    {
        Connection connection = null;
        try(InputStream input = DatabaseConnection.class.getClassLoader().getResourceAsStream("config.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            String url = prop.getProperty("db.url");
            String user = prop.getProperty("db.user");
            String password = prop.getProperty("db.password");

            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Conexion Exitosa");
        } catch(Exception ex)
        {
            ex.printStackTrace();
            System.out.println("Error en la conexion a la base de datos");
        }
        return connection;
    }
}