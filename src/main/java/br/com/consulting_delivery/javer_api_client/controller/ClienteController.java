package br.com.consulting_delivery.javer_api_client.controller;

import br.com.consulting_delivery.javer_api_client.dto.DadosAtualizacaoCliente;
import br.com.consulting_delivery.javer_api_client.dto.DadosCadastroCliente;
import br.com.consulting_delivery.javer_api_client.dto.DadosListagemCliente;
import br.com.consulting_delivery.javer_api_client.feign.DataManagerClient;
import br.com.consulting_delivery.javer_api_client.service.ServicoCliente;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private DataManagerClient dataManagerClient;

    @Autowired
    private ServicoCliente servicoCliente;

    @PostMapping
    public ResponseEntity<DadosListagemCliente> cadastrar(@RequestBody @Valid DadosCadastroCliente dados, UriComponentsBuilder uriBuilder) {
        DadosListagemCliente clienteCadastrado = this.dataManagerClient.cadastrar(dados);
        URI uri = uriBuilder.path("clientes/{id}").buildAndExpand(clienteCadastrado.id()).toUri();
        return ResponseEntity.created(uri).body(clienteCadastrado);
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemCliente>> listar(Pageable paginacao) {
        return ResponseEntity.ok(this.dataManagerClient.listar(paginacao));
    }

    @PutMapping
    public ResponseEntity<DadosListagemCliente> atualizar(@RequestBody DadosAtualizacaoCliente dados) {
        DadosListagemCliente response = this.dataManagerClient.atualizar(dados);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(@PathVariable Long id) {
        this.dataManagerClient.deletar(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosListagemCliente> getClienteById(@PathVariable Long id) {
        DadosListagemCliente cliente = this.dataManagerClient.getById(id);
        return ResponseEntity.ok(cliente);
    }

    @GetMapping("/{id}/score")
    public ResponseEntity<?> getScoreCredito(@PathVariable Long id) {
        DadosListagemCliente cliente = this.dataManagerClient.getById(id);
        Float score = this.servicoCliente.calcularScoreCredito(cliente.saldo_cc());
        HashMap<Object, Object> resposta = new HashMap<>();
        resposta.put("Score crédito", score);
        resposta.put("id", id);
        return ResponseEntity.ok(resposta);
    }
}
