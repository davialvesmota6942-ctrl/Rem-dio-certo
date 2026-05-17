package com.remediocerto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes de integração para ViaCepClient.
 */
class ViaCepClientTest {

    @Test
    @DisplayName("Deve retornar null para CEP nulo")
    void deveRetornarNullParaCepNulo() {
        ViaCepClient client = new ViaCepClient();
        EnderecoDTO endereco = client.buscarEndereco(null);
        assertNull(endereco);
    }

    @Test
    @DisplayName("Deve retornar null para CEP com menos de 8 dígitos")
    void deveRetornarNullParaCepInvalido() {
        ViaCepClient client = new ViaCepClient();
        EnderecoDTO endereco = client.buscarEndereco("000");
        assertNull(endereco);
    }

    @Test
    @DisplayName("Deve retornar null para CEP em branco")
    void deveRetornarNullParaCepEmBranco() {
        ViaCepClient client = new ViaCepClient();
        EnderecoDTO endereco = client.buscarEndereco("   ");
        assertNull(endereco);
    }
}
