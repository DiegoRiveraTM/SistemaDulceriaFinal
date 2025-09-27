package com.example.sistemadulceria.dao;

import com.example.sistemadulceria.model.Usuario;
import com.example.sistemadulceria.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsuarioDAO {

    //Metodo para registrar
    public boolean registrar(Usuario usuario)
    {
        boolean exito = false;
        String sql = "INSERT INTO USUARIO (nombre, apellidoPat, apellidoMat, email, contrasena) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getApellidoPat());
            stmt.setString(3, usuario.getApellidoMat());
            stmt.setString(4, usuario.getEmail());
            stmt.setString(5, usuario.getContrasena());


            int filas = stmt.executeUpdate();
            exito = filas > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return exito;
    }

    //Metodo para Login
    public Usuario login(String email, String contrasena)
    {
        //Empezamos con una instancia de usuario vacio
        Usuario usuario = null;
        //Hacemos la consulta para ver si existe un usuario y contrasena con esos datos
        String sql = "SELECT * FROM USUARIO WHERE email = ? AND contrasena = ?";

        //Intentamos conectarnos con Excepcion por si la conexion falla
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            //Reemplazamos los atributos de la consulta con los que se recibieron de los txt
            stmt.setString(1, email);
            stmt.setString(2, contrasena);

            //Ejecutamos la consulta y procesamos el resultado
            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    //Si hay coincidencia, creamos el objeto usuario y lo llenamos con los datos de la base de datos
                    usuario = new Usuario();
                    usuario.setIdUsuario(resultSet.getInt("id_usuario"));
                    usuario.setNombre(resultSet.getString("nombre"));
                    usuario.setApellidoPat(resultSet.getString("apellidoPat"));
                    usuario.setApellidoMat(resultSet.getString("apellidoMat"));
                    usuario.setEmail(resultSet.getString("email"));
                    usuario.setContrasena(resultSet.getString("contrasena"));
                }
            }
            //Excepcion por si falla la conexion con sql mostramos el mensaje
        } catch(Exception e) {
            e.printStackTrace();
        }
        //Retornamos el usuario
        return usuario;
    }

}
