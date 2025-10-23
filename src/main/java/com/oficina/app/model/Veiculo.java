package com.oficina.app.model;

public abstract class Veiculo {
    protected Long id;
    protected String vin;
    protected String placa;
    protected String marca;
    protected String modelo;
    protected int ano;
    protected Long condutorId;

    public Veiculo() {}

    public Veiculo(Long id, String vin, String placa, String marca, String modelo, int ano, Long condutorId) {
        this.id = id;
        this.vin = vin;
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.condutorId = condutorId;
    }

    public abstract String getTipo();

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getVin() { return vin; }
    public void setVin(String vin) { this.vin = vin; }
    public String getPlaca() { return placa; }
    public void setPlaca(String placa) { this.placa = placa; }
    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }
    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }
    public int getAno() { return ano; }
    public void setAno(int ano) { this.ano = ano; }
    public Long getCondutorId() { return condutorId; }
    public void setCondutorId(Long condutorId) { this.condutorId = condutorId; }
}
