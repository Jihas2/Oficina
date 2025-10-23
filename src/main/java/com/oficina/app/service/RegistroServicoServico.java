package com.oficina.app.service;

import com.oficina.app.model.RegistroServico;
import com.oficina.app.repository.RegistroServicoRepositorio;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RegistroServicoServico {
    private final RegistroServicoRepositorio repo = new RegistroServicoRepositorio();

    public RegistroServico criar(RegistroServico r) throws SQLException { return repo.salvar(r); }
    public List<RegistroServico> listarTodos() throws SQLException { return repo.buscarTodos(); }
    public List<RegistroServico> listarPorVeiculo(Long veiculoId) throws SQLException { return repo.buscarPorVeiculo(veiculoId); }

    public Map<Long, Double> totalPorVeiculo() throws SQLException {
        List<RegistroServico> all = repo.buscarTodos();
        return all.stream().collect(Collectors.groupingBy(RegistroServico::getVeiculoId, Collectors.summingDouble(RegistroServico::getCusto)));
    }
}
