package com.oficina.app.repository;

import com.oficina.app.model.Veiculo;
import com.oficina.app.model.Carro;
import com.oficina.app.model.Moto;
import com.oficina.app.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VeiculoRepositorio {
    public Veiculo salvar(Veiculo v) throws SQLException {
        String sql = "INSERT INTO veiculo(vin, placa, marca, modelo, ano, tipo, condutor_id) VALUES(?,?,?,?,?,?,?)";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, v.getVin());
            ps.setString(2, v.getPlaca());
            ps.setString(3, v.getMarca());
            ps.setString(4, v.getModelo());
            ps.setInt(5, v.getAno());
            ps.setString(6, v.getTipo());
            if (v.getCondutorId() != null) ps.setLong(7, v.getCondutorId()); else ps.setNull(7, Types.BIGINT);
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) v.setId(rs.getLong(1));
            }
        }
        return v;
    }

    public List<Veiculo> buscarTodos() throws SQLException {
        String sql = "SELECT * FROM veiculo";
        List<Veiculo> list = new ArrayList<>();
        try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String tipo = rs.getString("tipo");
                Long id = rs.getLong("id");
                String vin = rs.getString("vin");
                String placa = rs.getString("placa");
                String marca = rs.getString("marca");
                String modelo = rs.getString("modelo");
                int ano = rs.getInt("ano");
                Long condId = rs.getLong("condutor_id"); if (rs.wasNull()) condId = null;
                if ("CARRO".equalsIgnoreCase(tipo)) {
                    Carro c = new Carro(id, vin, placa, marca, modelo, ano, condId, 4);
                    list.add(c);
                } else {
                    Moto m = new Moto(id, vin, placa, marca, modelo, ano, condId, false);
                    list.add(m);
                }
            }
        }
        return list;
    }

    public Veiculo buscarPorId(Long id) throws SQLException {
        String sql = "SELECT * FROM veiculo WHERE id = ?";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String tipo = rs.getString("tipo");
                    if ("CARRO".equalsIgnoreCase(tipo)) return new Carro(rs.getLong("id"), rs.getString("vin"), rs.getString("placa"), rs.getString("marca"), rs.getString("modelo"), rs.getInt("ano"), rs.getLong("condutor_id"), 4);
                    else return new Moto(rs.getLong("id"), rs.getString("vin"), rs.getString("placa"), rs.getString("marca"), rs.getString("modelo"), rs.getInt("ano"), rs.getLong("condutor_id"), false);
                }
            }
        }
        return null;
    }
}
