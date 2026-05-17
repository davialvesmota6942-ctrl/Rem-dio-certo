package com.remediocerto;

/**
 * DTO com os dados de endereço retornados pela API ViaCEP.
 */
public class EnderecoDTO {

    private String cep;
    private String logradouro;
    private String bairro;
    private String localidade;
    private String uf;

    /** Retorna o CEP formatado. */
    public String getCep() {
        return cep;
    }

    /** Retorna o logradouro (rua/avenida). */
    public String getLogradouro() {
        return logradouro;
    }

    /** Retorna o bairro. */
    public String getBairro() {
        return bairro;
    }

    /** Retorna a cidade. */
    public String getLocalidade() {
        return localidade;
    }

    /** Retorna o estado (UF). */
    public String getUf() {
        return uf;
    }

    @Override
    public String toString() {
        return String.format("%s, %s - %s/%s (CEP: %s)",
                logradouro, bairro, localidade, uf, cep);
    }
}
