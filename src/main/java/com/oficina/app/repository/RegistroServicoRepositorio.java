package com.oficina.app.repository;

import com.oficina.app.model.RegistroServico;
import com.oficina.app.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RegistroServicoRepositorio {
    public RegistroServico salvar(RegistroServico r) throws SQLException {
        String sql = "INSERT INTO registro_servico(veiculo_id, descricao, custo) VALUES(?,?,?)";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setLong(1, r.getVeiculoId());
            ps.setString(2, r.getDescricao());
            ps.setDouble(3, r.getCusto());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) r.setId(rs.getLong(1));
            }
        }
        return r;
    }

    public java.util.List<RegistroServico> buscarTodos() throws SQLException {
        String sql = "SELECT * FROM registro_servico";
        java.util.List<RegistroServico> out = new ArrayList<>();
        try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                RegistroServico s = new RegistroServico();
                s.setId(rs.getLong("id"));
                s.setVeiculoId(rs.getLong("veiculo_id"));
                s.setDescricao(rs.getString("descricao"));
                Timestamp t = rs.getTimestamp("data");
                if (t != null) s.setData(t.toLocalDateTime());
                s.setCusto(rs.getDouble("custo"));
                out.add(s);
            }
        }
        return out;
    }

    public java.util.List<RegistroServico> buscarPorVeiculo(Long veiculoId) throws SQLException {
        String sql = "SELECT * FROM registro_servico WHERE veiculo_id = ?";
        java.util.List<RegistroServico> out = new ArrayList<>();
        try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, veiculoId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    RegistroServico s = new RegistroServico();
                    s.setId(rs.getLong("id"));
                    s.setVeiculoId(rs.getLong("veiculo_id"));
                    s.setDescricao(rs.getString("descricao"));
                    Timestamp t = rs.getTimestamp("data");
                    if (t != null) s.setData(t.toLocalDateTime());
                    s.setCusto(rs.getDouble("custo"));
                    out.add(s);
                }
            }
        }
        return out;
    }
}
