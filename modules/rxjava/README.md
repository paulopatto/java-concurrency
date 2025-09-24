# Módulo RxJava

Este módulo demonstra o uso do **RxJava**, uma das bibliotecas mais populares para programação reativa na JVM. Ele implementa a especificação ReactiveX.

## Filosofia de Concorrência

RxJava baseia-se no **Padrão Observer** e nos conceitos da **programação funcional** para gerenciar sequências de eventos ou dados de forma assíncrona. A filosofia é tratar tudo como um **fluxo (Stream)** de eventos no tempo.

-   **Fluxos de Dados Assíncronos:** Em vez de chamar um método e esperar por um retorno (puxar dados), você cria um `Observable` que emite itens ao longo do tempo. Um `Observer` (ou `Subscriber`) reage a esses itens à medida que eles chegam (empurrar dados).
-   **Operadores Declarativos:** A grande força do RxJava está em seu vasto conjunto de operadores (`map`, `filter`, `flatMap`, `zip`, etc.). Esses operadores permitem compor, transformar e combinar fluxos de maneira declarativa, abstraindo a complexidade do gerenciamento de threads, sincronização e tratamento de erros.
-   **Schedulers para Concorrência:** O controle sobre em qual thread o trabalho é executado é feito de forma explícita através de `Schedulers`. O desenvolvedor pode direcionar tarefas para pools de threads otimizados para I/O (`Schedulers.io()`) ou para CPU (`Schedulers.computation()`), desacoplando a lógica de negócios da estratégia de concorrência.
-   **Backpressure:** RxJava lida de forma robusta com cenários onde um produtor de eventos é mais rápido que o consumidor, através de estratégias de `Backpressure`, evitando o consumo excessivo de memória.

## Documentação e Recursos

-   **Documentação Oficial:**
    -   [Site Oficial do RxJava](https://github.com/ReactiveX/RxJava) - O repositório oficial com links para a documentação.
    -   [Wiki do RxJava 3.x](https://github.com/ReactiveX/RxJava/wiki) - Contém informações detalhadas sobre o uso da biblioteca.
    -   [Operadores RxJava (Visualizados)](https://rxmarbles.com/) - Uma ferramenta interativa essencial para entender como os operadores funcionam.

-   **Artigos e Tutoriais:**
    -   [Introduction to RxJava](https://www.baeldung.com/rx-java) - Guia de introdução da Baeldung.
    -   [Grokking RxJava](https://www.inthelou.pe/grokking-rxjava/) - Uma série de artigos que aprofunda os conceitos.

-   **Livros:**
    -   [Reactive Programming with RxJava](https://www.oreilly.com/library/view/reactive-programming-with/9781491931646/) - Um livro de referência sobre o assunto.
