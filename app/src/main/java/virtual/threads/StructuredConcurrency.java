package virtual.threads;

import jdk.incubator.concurrent.StructuredTaskScope;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.function.Supplier;

public class StructuredConcurrency {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        System.out.println("Iniciando exemplo com Structured Concurrency...");
        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
            // Criando subtasks concorrentes que rodam em virtual threads
            Future<String> task1 = scope.fork(() -> fetchData("API 1"));
            Future<String> task2 = scope.fork(() -> fetchData("API 2"));
            
            System.out.println("Aguardando a conclusão das tarefas...");
            scope.join(); // Aguarda ambas completarem
            scope.throwIfFailed(); // Propaga exceções se alguma falhar
            
            System.out.println("Resultado 1: " + task1.get());
            System.out.println("Resultado 2: " + task2.get());
        }
        System.out.println("...exemplo com Structured Concurrency finalizado.");
    }
    
    private static String fetchData(String source) throws InterruptedException {
        System.out.println("Buscando dados de " + source + " em: " + Thread.currentThread());
        Thread.sleep(1000); // Simula operação I/O
        return "Dados de " + source;
    }
}
