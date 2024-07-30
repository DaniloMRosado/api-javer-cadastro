# API de Cadastro Banco Javer
#### Esta aplicação permite realizar um CRUD de clientes do banco Javer e um endpoint adicional para o cálculo do score de crédito. Ela se comunica com outro microsserviço [(Data Manager)](https://github.com/DaniloMRosado/javer-banco-cadastro) que persistirá os dados no MySQL.

## Requisitos:
- JDK 17 instalado. (Recomendado configurar a variável de ambiente JAVA_HOME)
- Instalar Maven 3.9 (versão utilizada no projeto)
- Estar com o microsserviço [Data Manager](https://github.com/DaniloMRosado/javer-banco-cadastro) em execução
## Como executar a aplicação:
### 1. Baixar o projeto
- Fazer o download do arquivo ZIP deste repositório
  ##### OU
- Clonar o repositório com o comando  ```git clone https://github.com/DaniloMRosado/api-javer-cadastro.git``` sendo executado no terminal
### 2. Rodar a aplicação
- Com o projeto na IDE, execute a classe _JaverApiClientApplication.java_
  ##### OU
- Abra o terminal na pasta que está o projeto e execute o comando maven ```mvn spring-boot:run```
## Como acessar:

