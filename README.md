# API Cadastro de Clientes do Banco Javer
#### Esta aplicação permite realizar um CRUD de clientes do banco Javer e um endpoint adicional para o cálculo do score de crédito. Ela se comunica com outro microsserviço [(Data Manager)](https://github.com/DaniloMRosado/javer-banco-cadastro) que persistirá os dados no MySQL.

## Como executar a aplicação:
### Requisitos:
- JDK 17 instalado. (Recomendado configurar a variável de ambiente JAVA_HOME)
- Instalar Maven 3.9 (versão utilizada no projeto)
- Estar com o microsserviço [Data Manager](https://github.com/DaniloMRosado/javer-banco-cadastro) em execução na mesma máquina
### 1. Baixe o projeto
- Fazer o download do arquivo ZIP deste repositório
  ##### OU
- Clonar o repositório com o comando  ```git clone https://github.com/DaniloMRosado/api-javer-cadastro.git``` sendo executado no terminal
### 2. Rode a aplicação
- Com o projeto na IDE, execute a classe _JaverApiClientApplication.java_
  ##### OU
- Abra o terminal na pasta que está o projeto e execute o comando maven ```mvn spring-boot:run```
## Acesse os endspoints:
#### Utilize a URL http://localhost:8080/clientes para realizar as seguintes operações de CRUD
### POST 
> Cadastra um cliente
### GET
> Lista clientes (suporta paginação customizável)
### GET /{id}
> Selecionar um cliente por id
### PUT
> Atualiza um cliente
### DELETE /{id}
> Exclui um cliente por id
### GET /{id}/score
> Retorna o score de crédito de um cliente

### Para mais informações sobre como enviar dados a API, acesse a documentação 
http://localhost:8080/swagger-ui/index.html

