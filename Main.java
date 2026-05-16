package com.remediocerto;

import java.util.List;
import java.util.Scanner;


public class Main {

   private static final MedicamentoService service = new MedicamentoService();
   private static final ViaCepClient viaCepClient = new ViaCepClient();
   private static final Scanner scanner = new Scanner(System.in);


   public static void main(String[] args) {
       exibirBoasVindas();

       boolean rodando = true;
       while (rodando) {
           exibirMenu();
           String opcao = scanner.nextLine().trim();

           switch (opcao) {
               case "1" -> cadastrarMedicamento();
               case "2" -> listarMedicamentos();
               case "3" -> marcarComoTomado();
               case "4" -> removerMedicamento();
               case "5" -> resetarDia();
               case "6" -> consultarFarmacia();
               case "0" -> rodando = false;
               default -> System.out.println("Opção inválida. Tente novamente.");
           }
       }

       System.out.println("\nAté logo! Cuide-se bem. 💊");
       scanner.close();
   }

   private static void exibirBoasVindas() {
       System.out.println("╔══════════════════════════════════╗");
       System.out.println("║        💊 REMÉDIO CERTO          ║");
       System.out.println("║  Controle de Medicamentos v1.1.0 ║");
       System.out.println("╚══════════════════════════════════╝");
       System.out.println();
   }

   private static void exibirMenu() {
       System.out.println("\n--- MENU ---");
       System.out.println("1. Cadastrar medicamento");
       System.out.println("2. Listar medicamentos");
       System.out.println("3. Marcar como tomado");
       System.out.println("4. Remover medicamento");
       System.out.println("5. Resetar dia");
       System.out.println("6. Buscar farmácia por CEP");
       System.out.println("0. Sair");
       System.out.print("Escolha uma opção: ");
   }

   private static void cadastrarMedicamento() {
       System.out.println("\n--- CADASTRAR MEDICAMENTO ---");
       System.out.print("Nome do medicamento: ");
       String nome = scanner.nextLine();

       System.out.print("Dose (ex: 500mg, 1 comprimido): ");
       String dose = scanner.nextLine();

       System.out.print("Horário (ex: 08:00, após o almoço): ");
       String horario = scanner.nextLine();

       if (service.cadastrar(nome, dose, horario)) {
           System.out.println("✓ Medicamento cadastrado com sucesso!");
       } else {
           System.out.println("✗ Erro: preencha todos os campos corretamente.");
       }
   }

   private static void listarMedicamentos() {
       System.out.println("\n--- LISTA DE MEDICAMENTOS ---");
       List<Medicamento> lista = service.listar();

       if (lista.isEmpty()) {
           System.out.println("Nenhum medicamento cadastrado.");
           return;
       }

       for (int i = 0; i < lista.size(); i++) {
           System.out.printf("%d. %s%n", i + 1, lista.get(i));
       }
   }

   private static void marcarComoTomado() {
       listarMedicamentos();
       List<Medicamento> lista = service.listar();
       if (lista.isEmpty()) {
           return;
       }

       System.out.print("\nNúmero do medicamento tomado: ");
       try {
           int indice = Integer.parseInt(scanner.nextLine().trim());
           if (service.marcarComoTomado(indice)) {
               System.out.println("✓ Medicamento marcado como tomado!");
           } else {
               System.out.println("✗ Número inválido.");
           }
       } catch (NumberFormatException e) {
           System.out.println("✗ Por favor, informe um número válido.");
       }
   }

   private static void removerMedicamento() {
       listarMedicamentos();
       List<Medicamento> lista = service.listar();
       if (lista.isEmpty()) {
           return;
       }

       System.out.print("\nNúmero do medicamento a remover: ");
       try {
           int indice = Integer.parseInt(scanner.nextLine().trim());
           if (service.remover(indice)) {
               System.out.println("✓ Medicamento removido com sucesso!");
           } else {
               System.out.println("✗ Número inválido.");
           }
       } catch (NumberFormatException e) {
           System.out.println("✗ Por favor, informe um número válido.");
       }
   }

   private static void resetarDia() {
       service.resetarDia();
       System.out.println("✓ Status do dia resetado. Bom dia! 🌅");
   }

   private static void consultarFarmacia() {
       System.out.println("\n--- BUSCAR ENDEREÇO DE FARMÁCIA ---");
       System.out.print("Digite o CEP (somente números): ");
       String cep = scanner.nextLine().trim();

       System.out.println("🔍 Consultando endereço...");
       EnderecoDTO endereco = viaCepClient.buscarEndereco(cep);

       if (endereco == null) {
           System.out.println("✗ CEP não encontrado ou inválido.");
       } else {
           System.out.println("✓ Endereço encontrado:");
           System.out.println("   " + endereco);
       }
   }
}

