# DIO Spring Boot - Projeto Final 05: Spring AI (orçamento)

> Adaptado do projeto original da trilha DIO: utilização do Java 21 (em vez do Java 25) e persistência de dados com PostgreSQL (em vez de MySQL). O restante da arquitetura e do comportamento foi mantido idêntico.

## Introdução

Este módulo final aplica o Spring AI em uma API de gerenciamento financeiro, mantendo a mesma arquitetura em camadas utilizada ao longo da trilha.

O objetivo é integrar recursos de Inteligência Artificial sem ultrapassar os limites das camadas de domínio e dos casos de uso da aplicação.

## Contexto do código

O projeto processa comandos de voz para criar e consultar transações financeiras.

**Fluxo principal:**

1. O cliente envia um arquivo de áudio.
2. O áudio é transcrito para texto.
3. O modelo seleciona uma ferramenta ou caso de uso da aplicação.
4. O caso de uso salva ou consulta os dados das transações.
5. A resposta final é convertida em áudio.

## Estrutura do projeto

- `src/main/java/dio/budgeting/domain`
  - Modelo de domínio e contrato do repositório.
- `src/main/java/dio/budgeting/application`
  - Casos de uso utilizados tanto pelos adaptadores REST quanto pelo recurso de chamada de ferramentas da IA.
- `src/main/java/dio/budgeting/infrastructure`
  - Adaptadores HTTP, adaptadores JPA e componentes de integração.

## Tópicos específicos do módulo

### Conversão de áudio em texto (Speech-to-Text)

- Utiliza `TranscriptionModel` para realizar a transcrição de áudio.
- As configurações do modelo são definidas no arquivo `application.properties`.

### Chamada de ferramentas (Tool Calling)

- O `ChatClient` registra as ferramentas disponibilizadas pelos casos de uso.
- Métodos anotados com `@Tool` expõem as funcionalidades de negócio para o modelo de IA.

### Conversão de texto em áudio (Text-to-Speech)

- O `TextToSpeechModel` gera arquivos de áudio no formato MP3 a partir do texto da resposta final.
- O endpoint de IA retorna o áudio gerado.

## Documentação do Spring AI

- Spring AI Reference: https://docs.spring.io/spring-ai/reference/index.html
- API ChatModel: https://docs.spring.io/spring-ai/reference/api/chatmodel.html
- API ChatClient: https://docs.spring.io/spring-ai/reference/api/chatclient.html
- API Tools: https://docs.spring.io/spring-ai/reference/api/tools.html
- API de transcrição de áudio: https://docs.spring.io/spring-ai/reference/api/audio/transcriptions.html
- API de geração de áudio: https://docs.spring.io/spring-ai/reference/api/audio/speech.html

## Referências da arquitetura compartilhada

Os conceitos gerais de arquitetura estão documentados no README principal do projeto:

- [Camadas DDD (Domain-Driven Design)](../README.md#ddd-layered-architecture)
- [Classe vs. Record](../README.md#java-class-vs-java-record-in-domain-modeling)
- [Identificadores fortemente tipados](../README.md#strong-typed-identifiers)
- [Padrão Repository](../README.md#repository-pattern)
- [Casos de uso e Clean Architecture](../README.md#use-cases-and-clean-architecture)
- [Suporte ao Docker Compose no desenvolvimento](../README.md#docker-compose-support-in-development)

## Como executar

Configure sua chave de API da OpenAI:

```bash
export OPENAI_API_KEY="sua_chave_de_api_aqui"
```

Execute a aplicação e os testes:

```bash
./gradlew bootRun
./gradlew test
```

## Observações

- Projeto educacional final com foco na integração de Inteligência Artificial e na disciplina arquitetural.
- Os testes de integração com provedores externos podem exigir credenciais válidas e ativas.
