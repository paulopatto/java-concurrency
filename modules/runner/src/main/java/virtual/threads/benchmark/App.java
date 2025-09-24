package virtual.threads.benchmark;

import virtual.threads.completablefuture.CompletableFutureExample;
import virtual.threads.coroutines.KotlinCoroutineExample;
import virtual.threads.loom.ProjectLoomExample;
import virtual.threads.rxjava.RxJavaExample;
import virtual.threads.structured.StructuredConcurrency;

import java.time.Duration;
import java.time.Instant;

public class App {

    private static final int TASKS = 1_000;

    public static void main(String[] args) {
        System.out.println("Iniciando benchmark de concorrência com " + TASKS + " tarefas mistas (CPU, I/O)...");
        System.out.println("=================================================================================");

        runBenchmark("1. Virtual Threads (Project Loom)", () -> new ProjectLoomExample().run(TASKS));
        runBenchmark("2. Structured Concurrency", () -> new StructuredConcurrency().run(TASKS));
        runBenchmark("3. CompletableFuture (Platform Threads)", () -> new CompletableFutureExample().run(TASKS));
        runBenchmark("4. RxJava (Schedulers)", () -> new RxJavaExample().run(TASKS));
        runBenchmark("5. Kotlin Coroutines (Dispatchers)", () -> new KotlinCoroutineExample().run(TASKS));

        System.out.println("=================================================================================");
        System.out.println("Benchmark finalizado.");
    }

    private static void runBenchmark(String name, Runnable task) {
        Instant start = Instant.now();
        task.run();
        long millis = Duration.between(start, Instant.now()).toMillis();
        System.out.printf("%-45s: %d ms%n", name, millis);
    }
}