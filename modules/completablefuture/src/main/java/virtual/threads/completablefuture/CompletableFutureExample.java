package virtual.threads.completablefuture;

import virtual.threads.core.BenchmarkTasks;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

public class CompletableFutureExample {

    public void run(int totalTasks) {
        int cpuTasks = (int) (totalTasks * 0.20);
        int apiTasks = (int) (totalTasks * 0.40);
        int diskTasks = (int) (totalTasks * 0.40);

        // Usamos um pool de threads de plataforma para uma comparação justa
        try (var executor = Executors.newFixedThreadPool(200)) {
            CompletableFuture<?>[] cpuFutures = Stream.generate(() -> CompletableFuture.runAsync(BenchmarkTasks::cpuIntensiveTask, executor))
                    .limit(cpuTasks)
                    .toArray(CompletableFuture[]::new);

            CompletableFuture<?>[] apiFutures = Stream.generate(() -> CompletableFuture.runAsync(BenchmarkTasks::apiCall, executor))
                    .limit(apiTasks)
                    .toArray(CompletableFuture[]::new);
            
            CompletableFuture<?>[] diskFutures = Stream.generate(() -> CompletableFuture.runAsync(BenchmarkTasks::diskOp, executor))
                    .limit(diskTasks)
                    .toArray(CompletableFuture[]::new);

            CompletableFuture.allOf(cpuFutures).join();
            CompletableFuture.allOf(apiFutures).join();
            CompletableFuture.allOf(diskFutures).join();
        }
    }

    public static void main(String[] args) {
        System.out.println("Running CompletableFuture Example standalone...");
        new CompletableFutureExample().run(1000);
        System.out.println("...done.");
    }
}