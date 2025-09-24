# Laboratório de Coroutines com Java 19+ (Virtual Threads)

Este projeto é um laboratório para explorar e comparar padrões de concorrência em Java, com foco principal nas **Virtual Threads** (Project Loom).

## O que são Virtual Threads?

Virtual Threads são a resposta do Java para coroutines. São tarefas leves que rodam no topo das threads de plataforma do SO. Diferente das threads tradicionais, que são um recurso pesado, milhões de virtual threads podem ser criadas. Elas são ideais para tarefas bloqueadas por **I/O**, pois liberam a thread da plataforma enquanto esperam, permitindo uma escalabilidade massiva.

## Estrutura do Projeto

O código-fonte contém vários exemplos e um benchmark em `app/src/main/java/virtual/threads/`:

- `App.java`: **Benchmark principal.** Executa uma tarefa CPU-intensiva usando quatro modelos de concorrência diferentes e compara seus tempos de execução.
- `ProjectLoomExample.java`: Exemplo original de I/O simulado com `Executors.newVirtualThreadPerTaskExecutor()`.
- `StructuredConcurrency.java`: Demonstra o uso de `StructuredTaskScope` para gerenciar o ciclo de vida de tarefas concorrentes.
- `AsyncCoroutine.java`: Exemplo de programação assíncrona com `CompletableFuture`.
- `RxJavaExample.java`: Exemplo de programação reativa com RxJava.

## O Benchmark

O benchmark (`./gradlew run`) executa 1000 tarefas **CPU-intensivas** (hashing com BCrypt) em paralelo.

**Nota Importante:** Virtual Threads foram projetadas para otimizar tarefas **I/O-bound** (espera por rede, disco, etc.). Para tarefas puramente **CPU-bound** como este benchmark, as threads de plataforma (`CompletableFuture` com um pool fixo ou `RxJava` com `Schedulers.computation()`) podem apresentar desempenho similar ou até superior. O objetivo aqui é comparar a sintaxe e o comportamento das diferentes APIs sob carga.

## Como Usar o Projeto

### Pré-requisitos
- JDK 19 ou superior.

### Compilar o Projeto (Build)
```bash
./gradlew build
```

### Executar o Benchmark Principal
Para rodar o benchmark comparativo, execute:
```bash
./gradlew run
```

### Executando os Exemplos Individuais
- **Virtual Threads (I/O Simulado):**
  ```bash
  ./gradlew runProjectLoomExample
  ```
- **`CompletableFuture`:**
  ```bash
  ./gradlew runCompletableFuture
  ```
- **`RxJava`:**
  ```bash
  ./gradlew runRxJava
  ```
- **`StructuredConcurrency`:**
  ```bash
  ./gradlew runStructuredConcurrency
  ```

- **`Kotlin coreoutins`:**
  ```bash
  ./gradlew runKotlinCoroutines
  ```


### Rodar os Testes
```bash
./gradlew test
```

## Comparação de Kotlin e Java para coroutines


| Feature                  |	Java (Loom como referencia)	| Kotlin  |
| ------------------------ | ---------------------------- | ------- |
| Sintaxe	                 |  Verbosa	                    | Concisa, limpa e intuitiva (bem mais agradável) |
| Integração               | 	Biblioteca	                | Nativa  |
| Curva de aprendizado     | 	Moderada	                  | Fácil   |
| Performance e velocidade |  Normal                      | Equivalente Java mas a compilação foi bem lenta |


