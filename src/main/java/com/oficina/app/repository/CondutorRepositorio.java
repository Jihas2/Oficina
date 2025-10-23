package com.oficina.app.repository;

import com.oficina.app.model.Condutor;
import com.oficina.app.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CondutorRepositorio {
    public Condutor salvar(Condutor c) throws SQLException {
        String sql = "INSERT INTO condutor(nome, cnh) VALUES(?,?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, c.getNome());
            ps.setString(2, c.getCnh());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) c.setId(rs.getLong(1));
            }
        }
        return c;
    }

    public List<Condutor> buscarTodos() throws SQLException {
        String sql = "SELECT * FROM condutor";
        List<Condutor> out = new ArrayList<>();
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                out.add(new Condutor(rs.getLong("id"), rs.getString("nome"), rs.getString("cnh")));
            }
        }
        return out;
    }

    public Condutor buscarPorId(Long id) throws SQLException {
        String sql = "SELECT * FROM condutor WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return new Condutor(rs.getLong("id"), rs.getString("nome"), rs.getString("cnh"));
            }
        }
        return null;
    }
}
