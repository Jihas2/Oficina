package com.oficina.app.model;

public class Carro extends Veiculo {
    private int portas;

    public Carro() { super(); }

    public Carro(Long id, String vin, String placa, String marca, String modelo, int ano, Long condutorId, int portas) {
        super(id, vin, placa, marca, modelo, ano, condutorId);
        this.portas = portas;
    }

    @Override
    public String getTipo() { return "CARRO"; }

    public int getPortas() { return portas; }
    public void setPortas(int portas) { this.portas = portas; }
}
