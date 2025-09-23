package virtual.threads;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class AsyncCoroutine {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        System.out.println("Iniciando exemplo com CompletableFuture...");
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("Processando de forma assíncrona em: " + Thread.currentThread());
            try {
                // Simula uma tarefa demorada
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return "Resultado";
        });
        
        future.thenApply(result -> result + " processado")
            .thenAccept(finalResult -> {
                System.out.println("Resultado final: " + finalResult);
            });
        
        System.out.println("Aguardando a conclusão do futuro...");
        future.join(); // Aguarda a conclusão
        System.out.println("...exemplo com CompletableFuture finalizado.");
    }
}
