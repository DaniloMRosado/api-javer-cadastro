package br.com.consulting_delivery.javer_api_client.dto;

public record DadosListagemCliente(
        Long id, String nome,
        Long telefone,
        Boolean correntista,
        Float saldo_cc) {


}
