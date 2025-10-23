package com.oficina.app.service;

import com.oficina.app.model.Veiculo;
import com.oficina.app.repository.VeiculoRepositorio;

import java.sql.SQLException;
import java.util.List;

public class VeiculoServico {
    private final VeiculoRepositorio repo = new VeiculoRepositorio();

    public Veiculo criar(Veiculo v) throws SQLException { return repo.salvar(v); }
    public List<Veiculo> listarTodos() throws SQLException { return repo.buscarTodos(); }
    public Veiculo obterPorId(Long id) throws SQLException { return repo.buscarPorId(id); }
}
