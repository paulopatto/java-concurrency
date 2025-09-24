# Módulo CompletableFuture

Este módulo demonstra o uso da API `CompletableFuture`, introduzida no Java 8. É a abordagem clássica da JVM para programação assíncrona e composição de tarefas usando pools de threads de plataforma.

## Filosofia de Concorrência

`CompletableFuture` adota um modelo de **programação assíncrona e reativa baseada em callbacks**. A filosofia é se afastar do bloqueio explícito de threads e, em vez disso, criar um pipeline de processamento que reage à conclusão de tarefas.

-   **Futuros Composicionais:** Um `CompletableFuture` representa o resultado de uma computação que pode ainda não estar concluída. A sua força reside na capacidade de encadear operações (`thenApply`, `thenCompose`, `thenAccept`) que serão executadas automaticamente quando o resultado estiver disponível, sem bloquear a thread principal.
-   **Gerenciamento Explícito de Threads:** Ao contrário das Virtual Threads, o desempenho do `CompletableFuture` está diretamente ligado ao pool de threads (`Executor`) em que ele é executado. O desenvolvedor é responsável por fornecer e gerenciar o pool de threads apropriado para a carga de trabalho (por exemplo, um `ForkJoinPool` para tarefas de CPU, um pool com mais threads para tarefas de I/O).
-   **Código Funcional e Declarativo:** O estilo de encadeamento de métodos incentiva uma abordagem mais funcional e declarativa, mas pode levar a um código complexo e difícil de depurar (conhecido como "Callback Hell") em cenários mais elaborados.

## Documentação e Recursos

-   **Documentação Oficial:**
    -   [JavaDoc: `java.util.concurrent.CompletableFuture`](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/concurrent/CompletableFuture.html) - A documentação oficial da API.

-   **Artigos e Tutoriais:**
    -   [Guide to CompletableFuture](https://www.baeldung.com/java-completablefuture) - Um guia exaustivo da Baeldung.
    -   [The Java CompletableFuture Guide](https://www.vladmihalcea.com/the-java-completablefuture-guide/) - Um excelente artigo de Vlad Mihalcea com exemplos práticos.
    -   [20 Examples of Using Java's CompletableFuture](https://www.developer.com/java/java-completablefuture/) - Uma lista de exemplos práticos para diferentes cenários.

-   **Discussões:**
    -   [Java 8: The End of the for-loop?](https://blog.takipi.com/java-8-the-end-of-the-for-loop/) - Discute a mudança de paradigma do imperativo para o funcional, onde `CompletableFuture` se encaixa.
