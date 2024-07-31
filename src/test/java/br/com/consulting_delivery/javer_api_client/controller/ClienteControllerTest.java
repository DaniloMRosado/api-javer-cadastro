//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package br.com.consulting_delivery.javer_api_client.controller;

import br.com.consulting_delivery.javer_api_client.dto.DadosAtualizacaoCliente;
import br.com.consulting_delivery.javer_api_client.dto.DadosCadastroCliente;
import br.com.consulting_delivery.javer_api_client.dto.DadosListagemCliente;
import br.com.consulting_delivery.javer_api_client.feign.DataManagerClient;
import br.com.consulting_delivery.javer_api_client.service.ServicoCliente;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class ClienteControllerTest {
    @MockBean
    private DataManagerClient dataManagerClient;
    @MockBean
    private ServicoCliente servicoCliente;
    @Autowired
    private MockMvc mvc;
    @Autowired
    private JacksonTester<DadosCadastroCliente> dadosCadastroClienteJson;
    @Autowired
    private JacksonTester<DadosListagemCliente> dadoslistagemClienteJson;
    @Autowired
    private JacksonTester<DadosAtualizacaoCliente> dadosAtualizacaoClienteJson;
    @Autowired
    private JacksonTester<Page<DadosListagemCliente>> paginaJson;
    @Autowired
    private ObjectMapper mapper;

    ClienteControllerTest() {
    }

    @Test
    public void clienteCadastradoComSucesso() throws Exception {
        DadosCadastroCliente dadosCadastro = new DadosCadastroCliente("João", 123456789L, true, 1000.0F);
        DadosListagemCliente dadosListagemCliente = new DadosListagemCliente(1L, "João", 123456789L, true, 1000.0F);
        Mockito.when(this.dataManagerClient.cadastrar((DadosCadastroCliente)ArgumentMatchers.any())).thenReturn(dadosListagemCliente);

        MockHttpServletResponse resposta = this.mvc.perform(
                MockMvcRequestBuilders.post("/clientes", new Object[0]).contentType(MediaType.APPLICATION_JSON).content(this.dadosCadastroClienteJson.write(dadosCadastro).getJson())).andReturn().getResponse();
        String jsonEsperado = this.dadoslistagemClienteJson.write(dadosListagemCliente).getJson();
        Assertions.assertThat(resposta.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        Assertions.assertThat(resposta.getContentAsString(StandardCharsets.UTF_8)).isEqualTo(jsonEsperado);
    }

    @Test
    public void cadastrarClienteSemCorpoDeRequisicao() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.post("/clientes", new Object[0]).contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isBadRequest()).andExpect(MockMvcResultMatchers.content().string("Erro na requisição: corpo da requisição ausente"));
    }

    @Test
    public void cadastrarClienteComCamposInvalido() throws Exception {
        String json = """
                {
                    "nome" : "",
                    "telefone" : "",
                    "correntista" : false,
                    "saldo_cc" : 3500.70
                }
                """;
        this.mvc.perform(MockMvcRequestBuilders.post("/clientes", new Object[0]).contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(MockMvcResultMatchers.status().isBadRequest()).andExpect(MockMvcResultMatchers.jsonPath("$.nome", new Object[0]).value("Nome é obrigatório")).andExpect(MockMvcResultMatchers.jsonPath("$.telefone", new Object[0]).value("Telefone é obrigatório"));
    }

    @Test
    public void listarClientesConformePaginacao() throws Exception {
        List<DadosListagemCliente> clientes = new ArrayList<>();
        clientes.add(new DadosListagemCliente(1L, "João", 123456789L, true, 1000.0F));
        clientes.add(new DadosListagemCliente(2L, "Maria", 98989898923L, true, 8000.0F));
        Page<DadosListagemCliente> page = new PageImpl(clientes);
        Mockito.when(this.dataManagerClient.listar((Pageable)ArgumentMatchers.any(Pageable.class))).thenReturn(page);
        String respostaString = this.mvc.perform(MockMvcRequestBuilders.get("/clientes", new Object[0]).contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
        JsonNode clientesNode = this.mapper.readTree(respostaString).get("content");
        List<DadosListagemCliente> clientesResposta = this.mapper.readValue(clientesNode.toString(), new TypeReference<List<DadosListagemCliente>>() {
        });
        Assertions.assertThat(clientes).isEqualTo(clientesResposta);
    }

    @Test
    public void atualizarCliente() throws Exception {
        DadosAtualizacaoCliente dadosAtualizacao = new DadosAtualizacaoCliente(48L, (String)null, (Long)null, (Boolean)null, 2500.8F);
        DadosListagemCliente clienteAtualizado = new DadosListagemCliente(48L, "Tiago", 33333333L, true, 2500.8F);
        Mockito.when(this.dataManagerClient.atualizar((DadosAtualizacaoCliente)ArgumentMatchers.any(DadosAtualizacaoCliente.class))).thenReturn(clienteAtualizado);
        String resposta = this.mvc.perform(MockMvcRequestBuilders.put("/clientes", new Object[0]).contentType(MediaType.APPLICATION_JSON).content(this.dadosAtualizacaoClienteJson.write(dadosAtualizacao).getJson())).andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
        Assertions.assertThat(resposta).isEqualTo(this.dadoslistagemClienteJson.write(clienteAtualizado).getJson());
    }

    @Test
    public void excluirCliente() throws Exception {
        Long id = 1L;
        ((DataManagerClient)Mockito.doNothing().when(this.dataManagerClient)).deletar(ArgumentMatchers.anyLong());
        this.mvc.perform(MockMvcRequestBuilders.delete("/clientes/" + id)).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void retornarClientePorId() throws Exception {
        Long id = 48L;
        DadosListagemCliente cliente = new DadosListagemCliente(id, "Tiago", 33333333L, true, 2500.8F);
        Mockito.when(this.dataManagerClient.getById(id)).thenReturn(cliente);
        String resposta = this.mvc.perform(MockMvcRequestBuilders.get("/clientes/" + id)).andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
        Assertions.assertThat(resposta).isEqualTo(this.dadoslistagemClienteJson.write(cliente).getJson());
    }

    @Test
    public void retornarScoreCredito() throws Exception {
        Long id = 48L;
        DadosListagemCliente cliente = new DadosListagemCliente(id, "Tiago", 33333333L, true, 2500.8F);
        Float scoreEsperado = cliente.saldo_cc() * 0.1F;
        Mockito.when(this.dataManagerClient.getById(id)).thenReturn(cliente);
        Mockito.when(this.servicoCliente.calcularScoreCredito(cliente.saldo_cc())).thenReturn(scoreEsperado);
        HashMap<Object, Object> respostaEsperada = new HashMap();
        respostaEsperada.put("Score crédito", 250.08);
        respostaEsperada.put("id", 48);
        String respostaRequisicao = this.mvc.perform(MockMvcRequestBuilders.get("/clientes/" + id + "/score", new Object[0])).andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
        JsonNode respostaRequisicaoNode = this.mapper.readTree(respostaRequisicao);
        HashMap clientesResposta = this.mapper.readValue(respostaRequisicaoNode.toString(), HashMap.class);
        Assertions.assertThat(respostaEsperada).isEqualTo(clientesResposta);
    }
}
