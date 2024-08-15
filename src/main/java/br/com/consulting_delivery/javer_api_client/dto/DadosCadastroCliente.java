package br.com.consulting_delivery.javer_api_client.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

public record DadosCadastroCliente(
        @NotBlank(message = "Nome é obrigatório")
        String nome,

        @NotNull(message = "Telefone é obrigatório")
        Long telefone,

        @NotNull(message = "Informe se o cliente é correntista [true/false]")
        Boolean correntista,

        @NotNull(message = "Insira um saldo inicial")
        Float saldoCc,

        @NotNull(message = "CPF é obrigatório")
        String cpf
        ){


}
