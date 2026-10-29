# Padrões de Projeto na Prática com Java

Projeto final do desafio de **Padrões de Projeto (Design Patterns)** da DIO. Reúne duas implementações que evoluem os laboratórios de referência: uma em **Java puro** e outra com **Spring Boot**.

## Sumário

- [Estrutura do repositório](#estrutura-do-repositório)
- [Padrões implementados](#padrões-implementados)
- [Java puro](#java-puro)
- [Spring Boot](#spring-boot)
- [O que foi evoluído em relação ao laboratório](#o-que-foi-evoluído-em-relação-ao-laboratório)
- [Referências](#referências)

## Estrutura do repositório

```
lab-padroes-projeto/
├── java-puro/
│   └── src/
│       ├── one/digitalinnovation/gof/
│       │   ├── Main.java
│       │   ├── singleton/
│       │   ├── strategy/
│       │   ├── builder/
│       │   ├── chain/
│       │   ├── observer/
│       │   └── facade/
│       ├── subsistema1/crm/
│       └── subsistema2/cep/
└── spring/
    ├── pom.xml
    └── src/
        ├── main/java/one/digitalinnovation/gof/
        │   ├── controller/
        │   ├── service/
        │   ├── validator/
        │   ├── event/
        │   ├── exception/
        │   ├── model/
        │   └── util/
        └── test/java/one/digitalinnovation/gof/
```

## Padrões implementados

| Padrão | Java puro | Spring Boot |
|---|---|---|
| Singleton | `SingletonEager`, `SingletonLazy` (thread-safe), `SingletonLazyHolder`, `SingletonEnum` e `CepApi` | Beans do Spring (`@Service`, `@Component`) são singletons por padrão |
| Strategy | `Comportamento` com quatro implementações trocadas em tempo de execução no `Robo` | Interface `ClienteService` com a implementação `ClienteServiceImpl` |
| Factory Method | `ComportamentoFactory` com `TipoComportamento` | Injeção de dependências do contêiner |
| Builder | `Cliente.builder()` e `toBuilder()` | Não aplicado |
| Chain of Responsibility | `ValidadorNome` → `ValidadorCep` → `ValidadorDuplicidade` | `ValidadorCliente` com `ValidadorNome` e `ValidadorCep` ordenados por `@Order` |
| Observer | `Publicador` e `Observador` com `NotificadorBoasVindas` e `RegistroAuditoria` | `ClienteSalvoEvent` e `ClienteSalvoListener` com eventos do Spring |
| Facade | `Facade.migrarCliente` esconde validação, consulta de CEP, CRM e notificações | `ClienteRestController` expõe uma API REST simples sobre H2 e ViaCEP |
| Repository | Não aplicado | `ClienteRepository` e `EnderecoRepository` (Spring Data JPA) |

## Java puro

**Requisitos:** JDK 11 ou superior.

Compilar e executar a partir da pasta `java-puro`:

```bash
cd java-puro
mkdir -p out
javac -encoding UTF-8 -d out $(find src -name '*.java')
java -cp out one.digitalinnovation.gof.Main
```

No Windows (PowerShell):

```powershell
cd java-puro
mkdir out -Force
javac -encoding UTF-8 -d out (Get-ChildItem -Recurse src -Filter *.java).FullName
java -cp out one.digitalinnovation.gof.Main
```

A classe `Main` demonstra, em ordem:

1. **Singleton:** compara duas chamadas de cada variação e confirma que a instância é a mesma.
2. **Strategy e Factory Method:** percorre todos os `TipoComportamento` e troca o comportamento do `Robo`.
3. **Facade, Chain, Builder e Observer:** migra um cliente válido e mostra três rejeições (nome curto, CEP inválido e cliente duplicado).

## Spring Boot

**Requisitos:** JDK 11 ou superior e Maven 3.6 ou superior.

```bash
cd spring
mvn spring-boot:run
```

Para rodar os testes:

```bash
mvn test
```

Endereços disponíveis com a aplicação em execução:

| Recurso | URL |
|---|---|
| API REST | `http://localhost:8080/clientes` |
| Swagger UI | `http://localhost:8080/swagger-ui.html` |
| Console do H2 | `http://localhost:8080/h2-console` (JDBC URL `jdbc:h2:mem:clientesdb`) |

### Endpoints

| Método | Rota | Descrição | Resposta de sucesso |
|---|---|---|---|
| GET | `/clientes` | Lista todos os clientes | 200 |
| GET | `/clientes/{id}` | Busca um cliente | 200 |
| POST | `/clientes` | Cadastra um cliente | 201 |
| PUT | `/clientes/{id}` | Atualiza um cliente | 200 |
| DELETE | `/clientes/{id}` | Remove um cliente | 204 |

### Exemplos

Cadastrar um cliente (o endereço é completado pela API do ViaCEP):

```bash
curl -X POST http://localhost:8080/clientes \
  -H "Content-Type: application/json" \
  -d '{"nome": "Venilton", "endereco": {"cep": "14801-788"}}'
```

Buscar por id:

```bash
curl http://localhost:8080/clientes/1
```

Erros retornam um JSON padronizado:

```json
{
  "timestamp": "2026-10-29T12:00:00Z",
  "status": 404,
  "erro": "Not Found",
  "mensagem": "Cliente nao encontrado: 99"
}
```

| Situação | Status |
|---|---|
| Nome com menos de 3 caracteres | 400 |
| CEP sem 8 dígitos | 400 |
| CEP inexistente no ViaCEP | 400 |
| Cliente inexistente (consulta, atualização ou remoção) | 404 |

### Fluxo de um cadastro

1. `ClienteRestController` recebe a requisição (Facade).
2. `ClienteServiceImpl` executa a cadeia de `ValidadorCliente` na ordem de `@Order` (Chain of Responsibility).
3. O CEP é normalizado e procurado no banco; se não existir, é consultado no ViaCEP via OpenFeign e persistido.
4. O cliente é salvo pelo repositório.
5. `ClienteSalvoEvent` é publicado e `ClienteSalvoListener` registra o resultado em log (Observer).

### Testes

- `ValidadoresTest`: regras de nome e CEP.
- `ClienteServiceImplTest`: serviço com Mockito, cobrindo consulta ao ViaCEP, reaproveitamento de endereço, CEP inválido, validação antes das integrações, atualização, exclusão e publicação do evento.
- `LabPadroesProjetoSpringApplicationTests`: carregamento do contexto.

## O que foi evoluído em relação ao laboratório

**Java puro**

- Novos padrões: Factory Method, Builder, Chain of Responsibility e Observer.
- Novas variações de Singleton (`SingletonEnum`) e de Strategy (`ComportamentoEvasivo`).
- `SingletonLazy` agora é thread-safe.
- `Facade` passou a validar o cadastro, retornar o `Cliente` migrado e notificar observadores.
- `Robo` inicia com um comportamento padrão e rejeita comportamento nulo.
- Arquivos convertidos para UTF-8 e `Test` renomeada para `Main`.

**Spring Boot**

- Injeção por construtor no lugar de `@Autowired` em campos.
- Chain of Responsibility para validação e Observer com eventos do Spring.
- Tratamento global de erros com `@RestControllerAdvice` e status HTTP corretos (201, 204, 400 e 404).
- Correção da atualização: o `PUT` agora mantém o id informado em vez de criar um novo registro.
- Normalização do CEP (aceita `14801-788` e `14801788`) e tratamento de CEP inexistente.
- `buscarPorId` deixou de lançar `NoSuchElementException` (500) e passou a responder 404.
- Testes unitários com JUnit 5 e Mockito.
- Banco H2 com URL fixa e remoção do Maven Wrapper e de arquivos gerados pela IDE.

## Referências

- Laboratórios base da DIO: [lab-padroes-projeto-java](https://github.com/digitalinnovationone/lab-padroes-projeto-java) e [lab-padroes-projeto-spring](https://github.com/digitalinnovationone/lab-padroes-projeto-spring)
- [ViaCEP](https://viacep.com.br)
- [Spring Cloud OpenFeign](https://spring.io/projects/spring-cloud-openfeign)
