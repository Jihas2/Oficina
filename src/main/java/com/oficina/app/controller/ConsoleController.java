package com.oficina.app.controller;

import com.oficina.app.view.ConsoleView;
import com.oficina.app.service.*;
import com.oficina.app.util.ExternalApiClient;

import java.util.Scanner;

public class ConsoleController {
    private final ConsoleView view = new ConsoleView();
    private final VeiculoServico veiculoServico = new VeiculoServico();
    private final CondutorServico condutorServico = new CondutorServico();
    private final RegistroServicoServico registroServicoServico = new RegistroServicoServico();
    private final ExternalApiClient externalApiClient = new ExternalApiClient();

    public void executar() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            view.mostrarMenu();
            String line = sc.nextLine();
            int opt;
            try { opt = Integer.parseInt(line); } catch (Exception e) { opt = -1; }
            switch (opt) {
                case 1: view.criarCondutor(sc, condutorServico); break;
                case 2: view.criarVeiculo(sc, veiculoServico, condutorServico); break;
                case 3: view.registrarServico(sc, registroServicoServico); break;
                case 4: view.listarVeiculos(veiculoServico); break;
                case 5: view.relatorioTotais(registroServicoServico); break;
                case 6: view.consultarExterna(sc, externalApiClient); break;
                case 0: System.out.println("Saindo..."); return;
                default: System.out.println("Opção inválida"); break;
            }
        }
    }
}
