# Módulo Core

Este módulo é o coração do benchmark. Ele não implementa nenhum modelo de concorrência, mas fornece a lógica de negócios e as tarefas compartilhadas que todos os outros módulos utilizam para garantir uma comparação justa.

## Propósito

-   **Centralizar a Lógica:** Evita a duplicação de código, garantindo que cada implementação de concorrência execute exatamente o mesmo trabalho.
-   **Fornecer as Tarefas de Benchmark:** Contém a implementação das três cargas de trabalho:
    1.  `cpuIntensiveTask`: Uma tarefa que consome CPU (hashing com BCrypt).
    2.  `apiCall`: Uma tarefa de I/O-bound de rede (chamada a uma API REST).
    3.  `diskOp`: Uma tarefa de I/O-bound de disco/memória (leitura e tokenização de um arquivo).

## Dependências

-   **BCrypt:** Para a tarefa de uso intensivo da CPU.
-   **JTokkit:** Para a tarefa de tokenização de texto.
