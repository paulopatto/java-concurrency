# Módulo Kotlin Coroutines

Este módulo demonstra o uso de **Coroutines**, a abordagem idiomática do Kotlin para programação assíncrona. Elas são uma feature da linguagem que permite escrever código assíncrono de forma sequencial.

## Filosofia de Concorrência

As Coroutines do Kotlin são baseadas no conceito de **computação suspensível**. A filosofia é fornecer abstrações de alto nível para concorrência que são tão fáceis de usar quanto o código bloqueante, mas com a eficiência do código assíncrono.

-   **Código Assíncrono Sequencial:** Usando as palavras-chave `suspend` e `resume`, as coroutines permitem que uma função de longa duração (como uma chamada de rede) seja "pausada" sem bloquear a thread em que está sendo executada. Quando a operação termina, a função "continua" de onde parou. Isso elimina a necessidade de callbacks aninhados ("Callback Hell").
-   **Concorrência Estruturada (Structured Concurrency):** Assim como o Projeto Loom, as coroutines implementam a concorrência estruturada por padrão. O ciclo de vida de uma coroutine está sempre vinculado a um `CoroutineScope`. Se o escopo for cancelado, todas as coroutines dentro dele são automaticamente canceladas, prevenindo vazamentos de recursos e simplificando o tratamento de erros.
-   **Dispatchers para Controle de Threads:** A execução de uma coroutine é controlada por `Dispatchers`. O desenvolvedor pode facilmente especificar em qual pool de threads o trabalho deve ser executado (`Dispatchers.Default` para CPU, `Dispatchers.IO` para I/O), separando a lógica da execução.
-   **Leveza:** Coroutines são extremamente leves. Milhares delas podem ser executadas em uma única thread.

## Documentação e Recursos

-   **Documentação Oficial:**
    -   [Guia de Coroutines do Kotlin](https://kotlinlang.org/docs/coroutines-guide.html) - O guia oficial e completo.
    -   [Documentação da API `kotlinx.coroutines`](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/) - Referência completa da API.

-   **Artigos e Tutoriais:**
    -   [Coroutines on Android (Official Guide)](https://developer.android.com/kotlin/coroutines) - Embora focado em Android, é uma das melhores introduções aos conceitos fundamentais.
    -   [Kotlin Coroutines Tutorial for Beginners](https://www.youtube.com/watch?v=C38lG2_p-bA) (Vídeo) - Um tutorial em vídeo do canal "Philipp Lackner".
    -   [Baeldung: Guide to Kotlin Coroutines](https://www.baeldung.com/kotlin/coroutines) - Um guia prático da Baeldung.

-   **Discussões:**
    -   [The suspension mechanism of Kotlin Coroutines](https://kt.academy/article/suspension-mechanism) - Um artigo que explica em detalhes como a suspensão funciona por baixo dos panos.
