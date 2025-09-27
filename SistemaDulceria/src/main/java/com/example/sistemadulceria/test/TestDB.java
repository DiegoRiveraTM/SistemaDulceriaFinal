package com.example.sistemadulceria.test;

import com.example.sistemadulceria.util.DatabaseConnection;

import java.sql.Connection;

public class TestDB
{
    public static void main(String [] args)
    {
        Connection conn = DatabaseConnection.getConnection();
        if(conn != null)
        {
            System.out.println("Conectado");
        }
        else
        {
            System.out.println("No se pudo conectar");
        }
    }
}
