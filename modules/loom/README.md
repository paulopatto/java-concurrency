# Módulo Loom (Virtual Threads)

Este módulo demonstra o uso de **Virtual Threads**, a principal feature do Projeto Loom, introduzida oficialmente no Java 21 (e em preview desde o Java 19).

## Filosofia de Concorrência

Virtual Threads representam uma mudança de paradigma na programação concorrente em Java. A filosofia é permitir que os desenvolvedores escrevam código concorrente em um estilo **síncrono e imperativo**, que é simples de ler, escrever e depurar, mas com a escalabilidade massiva de um sistema assíncrono.

-   **Threads como um Recurso Abundante:** Diferente das threads de plataforma (threads do SO), que são um recurso escasso e pesado, as Virtual Threads são extremamente leves. Milhões delas podem ser criadas em uma única JVM.
-   **Ideal para I/O-bound:** Elas brilham em aplicações com muitas tarefas que esperam por operações de I/O (rede, banco de dados, disco). Quando uma virtual thread bloqueia em uma operação de I/O, ela "desmonta" da sua thread de plataforma, liberando-a para executar outra tarefa. Isso é feito de forma transparente pela JVM.
-   **Código Simples, Desempenho Assíncrono:** O resultado é um código que se parece com o código bloqueante tradicional, mas que escala para centenas de milhares ou milhões de tarefas concorrentes sem consumir uma quantidade enorme de recursos do sistema.

## Documentação e Recursos

-   **Documentação Oficial:**
    -   [JEP 444: Virtual Threads (Java 21)](https://openjdk.org/jeps/444) - A JEP oficial que finalizou as Virtual Threads.
    -   [JavaDoc: `java.lang.Thread.Builder`](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/lang/Thread.Builder.html) - A API para criar Virtual Threads.

-   **Artigos e Tutoriais:**
    -   [Inside Java: An Introduction to Virtual Threads](https://inside.java/2021/05/10/virtual-threads/) - Uma ótima introdução do time da Oracle.
    -   [Baeldung: Introduction to Java Virtual Threads](https://www.baeldung.com/java-virtual-threads) - Um guia prático e detalhado.
    -   [The Ultimate Guide to Java Virtual Threads](https://www.youtube.com/watch?v=gC2I-6I4_e4) (Vídeo) - Uma apresentação profunda de Nicolai Parlog.

-   **Papers e Discussões:**
    -   [Project Loom: Fibers and Continuations for the Java Virtual Machine](https://cr.openjdk.java.net/~rpressler/loom/Loom-Proposal.html) - A proposta original e a visão por trás do projeto.
