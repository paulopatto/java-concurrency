# Módulo Structured Concurrency

Este módulo demonstra o uso de **Structured Concurrency**, uma API em preview (incubada) que simplifica o desenvolvimento de software concorrente. Ela trata tarefas concorrentes como uma única unidade de trabalho, melhorando a confiabilidade e a observabilidade.

## Filosofia de Concorrência

A Concorrência Estruturada aplica os princípios da programação estruturada (como em `if/else` ou `try/catch`) ao código concorrente. A ideia central é que, se um fluxo de código se divide em múltiplos fluxos concorrentes, eles devem se juntar novamente no mesmo local.

-   **Ciclo de Vida Contido:** As tarefas filhas (`forks`) devem ser concluídas antes que a tarefa pai possa continuar. Isso elimina o risco de "vazamento" de threads, onde uma tarefa filha continua executando em segundo plano mesmo depois que o escopo que a criou já terminou.
-   **Propagação de Erros Clara:** Se uma tarefa filha falhar, o escopo (`StructuredTaskScope`) pode cancelar automaticamente as outras tarefas irmãs e propagar o erro para a tarefa pai. Isso torna o tratamento de erros em código concorrente tão robusto quanto em código sequencial.
-   **Código Mais Legível e Confiável:** A estrutura do código reflete o fluxo de execução concorrente, tornando-o mais fácil de entender e manter. O escopo define uma fronteira clara para a concorrência.

## Documentação e Recursos

-   **Documentação Oficial:**
    -   [JEP 453: Structured Concurrency (Preview)](https://openjdk.org/jeps/453) - A JEP oficial.
    -   [JavaDoc: `java.util.concurrent.StructuredTaskScope`](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/concurrent/StructuredTaskScope.html) - A API principal.

-   **Artigos e Tutoriais:**
    -   [Structured Concurrency in Java](https://www.baeldung.com/java-structured-concurrency) - Um guia prático da Baeldung.
    -   [Inside Java: The Power of Structured Concurrency](https://inside.java/2022/11/07/structured-concurrency/) - Artigo da Oracle explicando os benefícios.

-   **Papers e Discussões:**
    -   [Notes on Structured Concurrency, or: Go Statement Considered Harmful](https://vorpus.org/blog/notes-on-structured-concurrency-or-go-statement-considered-harmful/) - O artigo seminal de Nathaniel J. Smith que popularizou o conceito.
    -   [The "Structured" in Structured Concurrency](https://www.youtube.com/watch?v=10X0-84-G-Q) (Vídeo) - Apresentação de Ron Pressler (líder do Projeto Loom).
