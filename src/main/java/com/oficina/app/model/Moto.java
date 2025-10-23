package com.oficina.app.model;

public class Moto extends Veiculo {
    private boolean temSidecar;

    public Moto() { super(); }

    public Moto(Long id, String vin, String placa, String marca, String modelo, int ano, Long condutorId, boolean temSidecar) {
        super(id, vin, placa, marca, modelo, ano, condutorId);
        this.temSidecar = temSidecar;
    }

    @Override
    public String getTipo() { return "MOTO"; }

    public boolean isTemSidecar() { return temSidecar; }
    public void setTemSidecar(boolean temSidecar) { this.temSidecar = temSidecar; }
}
