package br.com.consulting_delivery.javer_api_client.service;

import org.springframework.stereotype.Service;

@Service
public class ServicoCliente {
    public ServicoCliente() {
    }

    public Float calcularScoreCredito(Float saldo) {
        return saldo * 0.1F;
    }
}
