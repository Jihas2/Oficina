package com.oficina.app.model;

import java.time.LocalDateTime;

public class RegistroServico {
    private Long id;
    private Long veiculoId;
    private String descricao;
    private LocalDateTime data;
    private double custo;

    public RegistroServico() {}

    public RegistroServico(Long id, Long veiculoId, String descricao, LocalDateTime data, double custo) {
        this.id = id;
        this.veiculoId = veiculoId;
        this.descricao = descricao;
        this.data = data;
        this.custo = custo;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getVeiculoId() { return veiculoId; }
    public void setVeiculoId(Long veiculoId) { this.veiculoId = veiculoId; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public LocalDateTime getData() { return data; }
    public void setData(LocalDateTime data) { this.data = data; }
    public double getCusto() { return custo; }
    public void setCusto(double custo) { this.custo = custo; }
}
