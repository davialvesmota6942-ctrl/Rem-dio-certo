# Remédio Certo

## Descrição do projeto
**Remédio Certo** é uma aplicação de linha de comando (CLI) desenvolvida em Java, voltada para o controle de medicamentos de idosos e pessoas que precisam gerenciar rotinas de medicação.

O problema abordado é o esquecimento de doses, situação comum e potencialmente perigosa para a saúde. A aplicação ajuda a organizar o uso de medicamentos, permitindo registrar nome, dose e horário, acompanhar quais remédios já foram tomados no dia e manter as informações salvas localmente.

## Problema real
Muitas pessoas, especialmente idosos e pacientes em tratamento contínuo, podem esquecer de tomar medicamentos nos horários corretos. Isso pode comprometer o tratamento, gerar riscos à saúde e dificultar o acompanhamento da rotina diária.

## Proposta da solução
A proposta do sistema é oferecer uma solução simples e acessível em linha de comando para:
- cadastrar medicamentos;
- listar medicamentos do dia;
- marcar medicamentos como tomados;
- remover medicamentos;
- resetar o status diário.

## Público-alvo
- idosos;
- cuidadores;
- pessoas em tratamento contínuo;
- usuários que precisam organizar medicações diárias.

## Funcionalidades principais
- cadastro de medicamento com nome, dose e horário;
- listagem dos medicamentos cadastrados;
- indicação de quais medicamentos já foram tomados;
- marcação individual como tomado;
- remoção de medicamento;
- reset diário do status;
- persistência de dados em arquivo JSON.

## Tecnologias utilizadas
- Java
- Maven
- JUnit 5
- Checkstyle
- GitHub Actions
- JSON para persistência local

## Como rodar os testes
mvn test

## Como rodar o lint
mvn checkstyle:check

## Versionamento
Versão atual: 1.0.0

## Autor
Davi Alves Mota

## Link do repositório público
https://github.com/SEU-USUARIO/remedio-certo
