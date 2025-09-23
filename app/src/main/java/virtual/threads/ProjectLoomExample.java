package virtual.threads;

import java.util.concurrent.Executors;

public class ProjectLoomExample {
    public static void main(String[] args) {
        System.out.println("Iniciando exemplo com Virtual Threads (Project Loom)...");
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            // Criando múltiplas virtual threads (coroutines)
            for (int i = 0; i < 10; i++) {
                int taskId = i;
                executor.submit(() -> {
                    try {
                        System.out.println("Task " + taskId + " rodando em: " + Thread.currentThread());
                        Thread.sleep(1000); // Simula I/O, não bloqueia threads da plataforma
                        System.out.println("Task " + taskId + " concluída");
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    return null;
                });
            }
        } // O executor.close() aguarda todas as tarefas terminarem
        System.out.println("...exemplo com Virtual Threads finalizado.");
    }
}