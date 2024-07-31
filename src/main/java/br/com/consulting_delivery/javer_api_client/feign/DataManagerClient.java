package br.com.consulting_delivery.javer_api_client.feign;

import br.com.consulting_delivery.javer_api_client.dto.DadosAtualizacaoCliente;
import br.com.consulting_delivery.javer_api_client.dto.DadosCadastroCliente;
import br.com.consulting_delivery.javer_api_client.dto.DadosListagemCliente;
import br.com.consulting_delivery.javer_api_client.exception.ExceptionConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Service
@FeignClient(
        value = "data-manager",
        url = "${spring.cloud.openfeign.client.config.data-manager.url}:8090",
        configuration = {ExceptionConfiguration.class},
        path = "/clientes"
)
public interface DataManagerClient {
    @PostMapping
    DadosListagemCliente cadastrar(DadosCadastroCliente cliente);

    @GetMapping
    Page<DadosListagemCliente> listar(Pageable paginacao);

    @PutMapping
    DadosListagemCliente atualizar(DadosAtualizacaoCliente dados);

    @DeleteMapping({"/{id}"})
    void deletar(@PathVariable Long id);

    @GetMapping({"/{id}"})
    DadosListagemCliente getById(@PathVariable Long id);
}
