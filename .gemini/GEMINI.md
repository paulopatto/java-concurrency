# Diretrizes para o Assistente Gemini neste Projeto

Este documento fornece diretrizes essenciais para garantir a consistência e a qualidade das contribuições neste repositório.

## 1. Guias Fundamentais

Antes de fazer qualquer alteração, familiarize-se e siga estritamente as diretrizes definidas nos seguintes arquivos:

-   **[Código de Conduta](./CODE_OF_CONDUCT.md)**: Todas as interações devem aderir a este código.
-   **[Guia de Contribuição](./CONTRIBUTING.md)**: Siga o processo de contribuição, especialmente as convenções para mensagens de commit.

## 2. Atualização do Changelog

**Regra Crítica:** Para CADA sugestão de commit que envolva uma alteração de código (feature, fix, refactor, etc.), você **DEVE** também propor uma atualização correspondente no arquivo `CHANGELOG.md`. As novas entradas devem ser adicionadas na seção `[Unreleased]`.

## 3. Como Adicionar um Novo Módulo de Benchmark

Para adicionar um novo modelo de concorrência ao benchmark, siga este padrão rigorosamente para manter a estrutura do projeto.

**Exemplo:** Adicionando um modelo chamado `NewFramework`.

### Passo a - Estrutura de Arquivos
1.  Crie o diretório do módulo: `mkdir -p modules/newframework/src/main/java/virtual/threads/newframework`
2.  Crie o arquivo de implementação: `modules/newframework/src/main/java/virtual/threads/newframework/NewFrameworkExample.java`

### Passo b - Configuração do Build
1.  **Declare o módulo:** Adicione `include 'modules:newframework'` ao arquivo `settings.gradle`.
2.  **Crie o `build.gradle` do módulo:** Crie o arquivo `modules/newframework/build.gradle` com o seguinte template, adicionando quaisquer dependências específicas necessárias:
    ```groovy
    plugins {
        id 'application'
    }

    dependencies {
        // Dependência obrigatória para o código de benchmark
        implementation project(':modules:core')

        // Adicione aqui quaisquer outras dependências (ex: 'com.newframework:lib:1.0')
    }

    application {
        mainClass = 'virtual.threads.newframework.NewFrameworkExample'
    }
    ```

### Passo c - Implementação
1.  No arquivo `NewFrameworkExample.java`, implemente a seguinte estrutura:
    ```java
    package virtual.threads.newframework;

    import virtual.threads.core.BenchmarkTasks;

    public class NewFrameworkExample {
        // Método que será chamado pelo benchmark runner
        public void run(int totalTasks) {
            // Implemente a lógica de execução das tarefas aqui
            // (20% CPU, 40% API, 40% Disco)
        }

        // Método para execução autônoma
        public static void main(String[] args) {
            new NewFrameworkExample().run(1000);
        }
    }
    ```

### Passo d - Integração com o Runner
1.  **Adicione a dependência:** No arquivo `modules/runner/build.gradle`, adicione `implementation project(':modules:newframework')` ao bloco `dependencies`.
2.  **Atualize o `App.java`:** No arquivo `modules/runner/src/main/java/virtual/threads/benchmark/App.java`, adicione a chamada para o novo módulo dentro do `main`:
    ```java
    runBenchmark("6. New Framework", () -> new NewFrameworkExample().run(TASKS));
    ```

### Passo e - Documentação
1.  **README do Módulo:** Crie um arquivo `modules/newframework/README.md`. Explique a filosofia de concorrência do `NewFramework` e adicione uma seção de "Documentação e Recursos" com links relevantes, seguindo o padrão dos outros módulos.
2.  **README Principal:** Atualize o `README.md` na raiz do projeto para incluir o `NewFramework` na lista de módulos e adicione o comando para sua execução autônoma (`./gradlew :modules:newframework:run`).
