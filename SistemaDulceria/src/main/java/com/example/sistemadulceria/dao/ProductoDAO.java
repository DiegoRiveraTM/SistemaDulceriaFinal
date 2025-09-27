package com.example.sistemadulceria.dao;

import com.example.sistemadulceria.model.Producto;
import com.example.sistemadulceria.model.TipoDulce;
import com.example.sistemadulceria.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {

    public static int insertar(Producto producto) throws SQLException {
        String query = "INSERT INTO producto(nombre, descripcion, productos_url, precio, stock, id_dulce) VALUES (?, ?, ?, ?, ?, ?)";
        int idGenerado = -1;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, producto.getNombre());
            stmt.setString(2, producto.getDescripcion());
            stmt.setString(3, producto.getProductos_url());
            stmt.setDouble(4, producto.getPrecio());
            stmt.setInt(5, producto.getStock());
            stmt.setInt(6, producto.getTipoDulce().getIdDulce());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                idGenerado = rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idGenerado;
    }

    // Metodo para registrar un nuevo producto
    public boolean registrar(Producto producto) {
        boolean exito = false;
        String query = "INSERT INTO producto(nombre, descripcion, productos_url, precio, stock, id_dulce) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, producto.getNombre());
            stmt.setString(2, producto.getDescripcion());
            stmt.setString(3, producto.getProductos_url());
            stmt.setDouble(4, producto.getPrecio());
            stmt.setInt(5, producto.getStock());
            stmt.setInt(6, producto.getTipoDulce().getIdDulce());

            int filas = stmt.executeUpdate();
            exito = filas > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exito;
    }

    // Metodo para obtener todos los productos
    public List<Producto> obtenerTodos() {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT p.id_producto, p.nombre, p.descripcion, p.productos_url, p.stock, p.precio, " +
                "t.id_dulce, t.nombre AS tipoNombre, t.prioridad " +
                "FROM producto p JOIN tipo_dulce t ON p.id_dulce = t.id_dulce";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                TipoDulce tipo = new TipoDulce(
                        rs.getInt("id_dulce"),
                        rs.getString("tipoNombre"),
                        rs.getInt("prioridad")
                );

                Producto producto = new Producto(
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getString("productos_url"),
                        rs.getInt("stock"),
                        rs.getDouble("precio"),
                        tipo
                );

                producto.setIdProducto(rs.getInt("id_producto"));
                productos.add(producto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productos;
    }

    public boolean eliminar(int idProducto) {
        String sql = "DELETE FROM producto WHERE id_producto = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idProducto);
            int filas = stmt.executeUpdate();
            return filas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
