package com.oficina.app.model;

public class Condutor {
    private Long id;
    private String nome;
    private String cnh;

    public Condutor() {}
    public Condutor(Long id, String nome, String cnh) {
        this.id = id; this.nome = nome; this.cnh = cnh;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCnh() { return cnh; }
    public void setCnh(String cnh) { this.cnh = cnh; }
}
