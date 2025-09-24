# Laboratório de Concorrência com Java, Kotlin e Virtual Threads

Este projeto é um laboratório para explorar e comparar diferentes modelos de concorrência na JVM, com foco em Virtual Threads (Project Loom), Structured Concurrency, CompletableFuture, RxJava e Coroutines do Kotlin.

O projeto é estruturado como um **build multi-módulo** com Gradle, onde cada modelo de concorrência é um submódulo independente.

## Estrutura do Projeto

O projeto é dividido nos seguintes módulos, localizados no diretório `modules/`:

-   `core`: Uma biblioteca Java que contém a lógica de benchmark compartilhada (`BenchmarkTasks`).
-   `loom`: Aplicação Java demonstrando Virtual Threads.
-   `structured`: Aplicação Java demonstrando Structured Concurrency.
-   `completablefuture`: Aplicação Java com `CompletableFuture` e pools de threads de plataforma.
-   `rxjava`: Aplicação Java com programação reativa usando RxJava.
-   `coroutines`: Aplicação Kotlin demonstrando Coroutines.
-   `runner`: A aplicação principal que depende de todos os outros módulos para executar um benchmark comparativo completo.

## O Benchmark

O benchmark executa uma carga de trabalho mista para simular uma aplicação real:
-   **20% de tarefas CPU-intensivas** (hashing com BCrypt).
-   **40% de tarefas de I/O de rede** (chamadas a uma API REST).
-   **40% de tarefas de I/O de disco/memória** (leitura e tokenização de um arquivo de texto).

## Como Usar o Projeto

### Pré-requisitos
- JDK 19 ou superior.

### Compilar Todos os Módulos (Build)
Para compilar todo o projeto, execute o comando a partir do diretório raiz:
```bash
./gradlew build
```

### Executar o Benchmark Completo
Para rodar o benchmark principal que executa e compara todos os modelos de concorrência, use a tarefa `run` do módulo `runner`:
```bash
./gradlew :modules:runner:run
```

### Executar Exemplos Individualmente
Você pode executar cada módulo de forma independente. Isso é útil para testar ou analisar um modelo específico.

-   **Virtual Threads (Loom):**
    ```bash
    ./gradlew :modules:loom:run
    ```
-   **Structured Concurrency:**
    ```bash
    ./gradlew :modules:structured:run
    ```
-   **CompletableFuture:**
    ```bash
    ./gradlew :modules:completablefuture:run
    ```
-   **RxJava:**
    ```bash
    ./gradlew :modules:rxjava:run
    ```
-   **Kotlin Coroutines:**
    ```bash
    ./gradlew :modules:coroutines:run
    ```

### Gerar um JAR Independente
Você pode empacotar qualquer módulo em um JAR executável. Por exemplo, para empacotar o módulo `loom`:
```bash
./gradlew :modules:loom:jar
```
O JAR será gerado em `modules/loom/build/libs/`.