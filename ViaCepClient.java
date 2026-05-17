package com.remediocerto;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Cliente para consulta de endereços via API pública ViaCEP.
 * Permite buscar informações de farmácias ou responsáveis pelo CEP.
 */
public class ViaCepClient {

    private static final String BASE_URL = "https://viacep.com.br/ws/%s/json/";
    private final HttpClient httpClient;
    private final Gson gson;

    /** Construtor padrão com HttpClient real. */
    public ViaCepClient() {
        this.httpClient = HttpClient.newHttpClient();
        this.gson = new Gson();
    }

    /** Construtor para injeção de dependência (testes). */
    public ViaCepClient(HttpClient httpClient) {
        this.httpClient = httpClient;
        this.gson = new Gson();
    }

    /**
     * Busca endereço pelo CEP informado.
     *
     * @param cep CEP no formato 00000000 ou 00000-000
     * @return EnderecoDTO com os dados ou null se não encontrado
     */
    public EnderecoDTO buscarEndereco(String cep) {
        if (cep == null || cep.isBlank()) {
            return null;
        }

        String cepLimpo = cep.replaceAll("[^0-9]", "");
        if (cepLimpo.length() != 8) {
            return null;
        }

        try {
            String url = String.format(BASE_URL, cepLimpo);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(
                    request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                return null;
            }

            EnderecoDTO endereco = gson.fromJson(response.body(), EnderecoDTO.class);

            if (endereco == null || endereco.getCep() == null) {
                return null;
            }

            return endereco;

        } catch (IOException | InterruptedException e) {
            System.err.println("Erro ao consultar ViaCEP: " + e.getMessage());
            return null;
        }
    }
}
