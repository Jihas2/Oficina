package com.oficina.app.view;

import com.oficina.app.model.*;
import com.oficina.app.service.*;
import com.oficina.app.util.ExternalApiClient;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ConsoleView {
    public void mostrarMenu() {
        System.out.println("\n=== Oficina Automotiva ===");
        System.out.println("1) Cadastrar condutor");
        System.out.println("2) Cadastrar veículo");
        System.out.println("3) Registrar serviço");
        System.out.println("4) Listar veículos");
        System.out.println("5) Relatório de custos por veículo");
        System.out.println("6) Consultar API externa (mock/URL)"); 
        System.out.println("0) Sair");
        System.out.print("Escolha: ");
    }

    public void criarCondutor(Scanner sc, CondutorServico servico) {
        try {
            System.out.print("Nome: "); String nome = sc.nextLine();
            System.out.print("CNH: "); String cnh = sc.nextLine();
            servico.criar(new Condutor(null, nome, cnh));
            System.out.println("Condutor criado."); 
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void criarVeiculo(Scanner sc, VeiculoServico servico, CondutorServico condutorServico) {
        try {
            System.out.print("ID do condutor (deixe vazio para nenhum): "); String idStr = sc.nextLine();
            Long condId = idStr.isBlank() ? null : Long.parseLong(idStr);
            if (condId != null) {
                Condutor c = condutorServico.obterPorId(condId);
                if (c == null) { System.out.println("Condutor não encontrado com ID " + condId); return; }
            }
            System.out.print("Tipo (CARRO/MOTO): "); String tipo = sc.nextLine().trim().toUpperCase();
            System.out.print("Placa: "); String placa = sc.nextLine();
            System.out.print("Marca: "); String marca = sc.nextLine();
            System.out.print("Modelo: "); String modelo = sc.nextLine();
            System.out.print("Ano: "); int ano = Integer.parseInt(sc.nextLine());
            if ("CARRO".equalsIgnoreCase(tipo)) {
                Carro carro = new Carro(null, null, placa, marca, modelo, ano, condId, 4);
                servico.criar(carro);
            } else {
                Moto moto = new Moto(null, null, placa, marca, modelo, ano, condId, false);
                servico.criar(moto);
            }
            System.out.println("Veículo criado e vinculado (se ID informado).");
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void registrarServico(Scanner sc, RegistroServicoServico servico) {
        try {
            System.out.print("ID do veículo: "); Long vid = Long.parseLong(sc.nextLine());
            System.out.print("Descrição: "); String desc = sc.nextLine();
            System.out.print("Custo: "); double custo = Double.parseDouble(sc.nextLine());
            servico.criar(new RegistroServico(null, vid, desc, null, custo));
            System.out.println("Serviço registrado.");
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void listarVeiculos(VeiculoServico servico) {
        try {
            List<Veiculo> lista = servico.listarTodos();
            if (lista.isEmpty()) System.out.println("Nenhum veículo cadastrado.");
            for (Veiculo v : lista) {
                System.out.printf("ID:%d - %s %s %d - Placa: %s - Condutor ID: %s\n", v.getId(), v.getMarca(), v.getModelo(), v.getAno(), v.getPlaca(), v.getCondutorId());
            }
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void relatorioTotais(RegistroServicoServico servico) {
        try {
            Map<Long, Double> totals = servico.totalPorVeiculo();
            if (totals.isEmpty()) System.out.println("Nenhum serviço registrado.");
            for (Map.Entry<Long, Double> e : totals.entrySet()) {
                System.out.printf("Veículo ID %d -> Total gasto: R$ %.2f\n", e.getKey(), e.getValue());
            }
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void consultarExterna(Scanner sc, ExternalApiClient client) {
        try {
            System.out.print("Informe URL ou 'file:resources/mock_fipe.json' para mock: "); String url = sc.nextLine();
            String json = client.buscarDadosVeiculo(url);
            System.out.println("Resultado (bruto):\n" + json);
        } catch (Exception e) { e.printStackTrace(); }
    }
}
