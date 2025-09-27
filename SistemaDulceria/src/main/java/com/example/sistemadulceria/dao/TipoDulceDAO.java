package com.example.sistemadulceria.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.example.sistemadulceria.util.DatabaseConnection;

public class TipoDulceDAO
{
    public List<String> listarTipos()
    {
        List<String> tipos = new ArrayList<>();
        String sql = "SELECT nombre FROM TIPO_DULCE ORDER BY prioridad ASC";

        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery())
        {
            while(rs.next())
            {
                tipos.add(rs.getString("nombre"));
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return tipos;
    }
}
