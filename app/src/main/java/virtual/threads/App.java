package virtual.threads;

import at.favre.lib.crypto.bcrypt.BCrypt;
import jdk.incubator.concurrent.StructuredTaskScope;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class App {

    private static final int TASKS = 1_000;
    private static final String PASSWORD = "my-super-secret-password-for-bcrypt";

    public static void main(String[] args) {
        System.out.println("Iniciando benchmark de concorrência com " + TASKS + " tarefas CPU-intensivas...");
        System.out.println("==================================================================");

        runWithVirtualThreads();
        runWithStructuredConcurrency();
        runWithCompletableFuture();
        runWithRxJava();

        System.out.println("==================================================================");
        System.out.println("Benchmark finalizado.");
    }

    private static String cpuIntensiveTask() {
        // BCrypt é uma tarefa CPU-intensiva, ótima para simulação.
        return BCrypt.withDefaults().hashToString(12, PASSWORD.toCharArray());
    }

    private static void runWithVirtualThreads() {
        Instant start = Instant.now();
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            IntStream.range(0, TASKS).forEach(i -> executor.submit(App::cpuIntensiveTask));
        }
        printDuration("1. Virtual Threads (Project Loom)", start);
    }

    private static void runWithStructuredConcurrency() {
        Instant start = Instant.now();
        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
            List<Future<String>> futures = new ArrayList<>();
            for (int i = 0; i < TASKS; i++) {
                futures.add(scope.fork(App::cpuIntensiveTask));
            }
            scope.join();
            scope.throwIfFailed();
        } catch (Exception e) {
            e.printStackTrace();
        }
        printDuration("2. Structured Concurrency", start);
    }

    private static void runWithCompletableFuture() {
        Instant start = Instant.now();
        // Usamos um pool de threads de plataforma para comparação
        try (var executor = Executors.newFixedThreadPool(200)) {
            CompletableFuture<?>[] futures = IntStream.range(0, TASKS)
                .mapToObj(i -> CompletableFuture.runAsync(App::cpuIntensiveTask, executor))
                .toArray(CompletableFuture[]::new);
            CompletableFuture.allOf(futures).join();
        }
        printDuration("3. CompletableFuture (Platform Threads)", start);
    }

    private static void runWithRxJava() {
        Instant start = Instant.now();
        Observable.range(0, TASKS)
            .flatMap(i -> Observable.fromCallable(App::cpuIntensiveTask).subscribeOn(Schedulers.computation()))
            .blockingSubscribe();
        printDuration("4. RxJava (Computation Scheduler)", start);
    }

    private static void printDuration(String name, Instant start) {
        long millis = Duration.between(start, Instant.now()).toMillis();
        System.out.printf("%-40s: %d ms%n", name, millis);
    }
}
