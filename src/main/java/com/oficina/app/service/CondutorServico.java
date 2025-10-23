package com.oficina.app.service;

import com.oficina.app.model.Condutor;
import com.oficina.app.repository.CondutorRepositorio;

import java.sql.SQLException;
import java.util.List;

public class CondutorServico {
    private final CondutorRepositorio repo = new CondutorRepositorio();

    public Condutor criar(Condutor c) throws SQLException { return repo.salvar(c); }
    public List<Condutor> listarTodos() throws SQLException { return repo.buscarTodos(); }
    public Condutor obterPorId(Long id) throws SQLException { return repo.buscarPorId(id); }
}
