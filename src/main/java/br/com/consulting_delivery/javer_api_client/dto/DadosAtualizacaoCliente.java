package br.com.consulting_delivery.javer_api_client.dto;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoCliente(
        @NotNull Long id,
        String nome,
        Long telefone,
        Boolean correntista,
        Float saldoCc) {



}
