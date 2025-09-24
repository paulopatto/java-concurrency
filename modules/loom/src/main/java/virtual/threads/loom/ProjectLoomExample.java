package virtual.threads.loom;

import virtual.threads.core.BenchmarkTasks;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class ProjectLoomExample {

    public void run(int totalTasks) {
        int cpuTasks = (int) (totalTasks * 0.20);
        int apiTasks = (int) (totalTasks * 0.40);
        int diskTasks = (int) (totalTasks * 0.40);

        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            IntStream.range(0, cpuTasks).forEach(i -> executor.submit(BenchmarkTasks::cpuIntensiveTask));
            IntStream.range(0, apiTasks).forEach(i -> executor.submit(BenchmarkTasks::apiCall));
            IntStream.range(0, diskTasks).forEach(i -> executor.submit(BenchmarkTasks::diskOp));
        }
    }

    public static void main(String[] args) {
        System.out.println("Running Project Loom Example standalone...");
        new ProjectLoomExample().run(1000);
        System.out.println("...done.");
    }
}
