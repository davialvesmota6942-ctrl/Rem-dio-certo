package com.remedio.certo;

import com.remedio.certo.model.Medicamento;
import com.remedio.certo.service.MedicamentoService;
import com.remedio.certo.storage.JsonStorage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String ARQUIVO_JSON = "data/medicamentos.json";

    public static void main(String[] args) {
        JsonStorage storage = new JsonStorage(ARQUIVO_JSON);
        List<Medicamento> carregados = carregarMedicamentos(storage);
        MedicamentoService service = new MedicamentoService(carregados);
        Scanner scanner = new Scanner(System.in);
        int opcao = -1;

        while (opcao != 0) {
            exibirMenu();
            try {
                opcao = Integer.parseInt(scanner.nextLine());
                switch (opcao) {
                    case 1:
                        cadastrarMedicamento(scanner, service, storage);
                        break;
                    case 2:
                        listarMedicamentos(service);
                        break;
                    case 3:
                        marcarComoTomado(scanner, service, storage);
                        break;
                    case 4:
                        removerMedicamento(scanner, service, storage);
                        break;
                    case 5:
                        service.resetarStatusDiario();
                        salvarMedicamentos(storage, service);
                        System.out.println("Status diário resetado com sucesso.");
                        break;
                    case 0:
                        System.out.println("Encerrando o sistema.");
                        break;
                    default:
                        System.out.println("Opção inválida.");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: digite um número válido.");
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }

        scanner.close();
    }

    private static List<Medicamento> carregarMedicamentos(JsonStorage storage) {
        try {
            return storage.carregar();
        } catch (IOException e) {
            System.out.println("Aviso: não foi possível carregar os dados anteriores.");
            return new ArrayList<>();
        }
    }

    private static void salvarMedicamentos(JsonStorage storage, MedicamentoService service) {
        try {
            storage.salvar(service.listarMedicamentos());
        } catch (IOException e) {
            System.out.println("Erro ao salvar os dados: " + e.getMessage());
        }
    }

    private static void exibirMenu() {
        System.out.println("\n=== REMÉDIO CERTO ===");
        System.out.println("1 - Cadastrar medicamento");
        System.out.println("2 - Listar medicamentos");
        System.out.println("3 - Marcar como tomado");
        System.out.println("4 - Remover medicamento");
        System.out.println("5 - Resetar status diário");
        System.out.println("0 - Sair");
        System.out.print("Escolha: ");
    }

    private static void cadastrarMedicamento(
            Scanner scanner,
            MedicamentoService service,
            JsonStorage storage
    ) {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Dose: ");
        String dose = scanner.nextLine();

        System.out.print("Horário: ");
        String horario = scanner.nextLine();

        service.adicionarMedicamento(nome, dose, horario);
        salvarMedicamentos(storage, service);
        System.out.println("Medicamento cadastrado com sucesso.");
    }

    private static void listarMedicamentos(MedicamentoService service) {
        List<Medicamento> medicamentos = service.listarMedicamentos();
        if (medicamentos.isEmpty()) {
            System.out.println("Nenhum medicamento cadastrado.");
            return;
        }

        for (int i = 0; i < medicamentos.size(); i++) {
            System.out.println(i + " - " + medicamentos.get(i));
        }
    }

    private static void marcarComoTomado(
            Scanner scanner,
            MedicamentoService service,
            JsonStorage storage
    ) {
        System.out.print("Índice do medicamento: ");
        int indice = Integer.parseInt(scanner.nextLine());
        service.marcarComoTomado(indice);
        salvarMedicamentos(storage, service);
        System.out.println("Medicamento marcado como tomado.");
    }

    private static void removerMedicamento(
            Scanner scanner,
            MedicamentoService service,
            JsonStorage storage
    ) {
        System.out.print("Índice do medicamento: ");
        int indice = Integer.parseInt(scanner.nextLine());
        service.removerMedicamento(indice);
        salvarMedicamentos(storage, service);
        System.out.println("Medicamento removido com sucesso.");
    }
}
