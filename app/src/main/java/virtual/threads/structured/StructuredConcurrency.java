package virtual.threads.structured;

import jdk.incubator.concurrent.StructuredTaskScope;
import virtual.threads.core.BenchmarkTasks;
import java.util.stream.IntStream;

public class StructuredConcurrency {

    public void run(int totalTasks) {
        int cpuTasks = (int) (totalTasks * 0.20);
        int apiTasks = (int) (totalTasks * 0.40);
        int diskTasks = (int) (totalTasks * 0.40);

        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
            IntStream.range(0, cpuTasks).forEach(i -> scope.fork(BenchmarkTasks::cpuIntensiveTask));
            IntStream.range(0, apiTasks).forEach(i -> scope.fork(BenchmarkTasks::apiCall));
            IntStream.range(0, diskTasks).forEach(i -> scope.fork(BenchmarkTasks::diskOp));
            
            scope.join();
            scope.throwIfFailed();
        } catch (Exception e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println("Running Structured Concurrency Example standalone...");
        new StructuredConcurrency().run(1000);
        System.out.println("...done.");
    }
}