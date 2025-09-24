package virtual.threads.rxjava;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import virtual.threads.core.BenchmarkTasks;

public class RxJavaExample {

    public void run(int totalTasks) {
        int cpuTasks = (int) (totalTasks * 0.20);
        int apiTasks = (int) (totalTasks * 0.40);
        int diskTasks = (int) (totalTasks * 0.40);

        // Para tarefas de CPU, o Computation Scheduler é o ideal
        Observable<String> cpuObs = Observable.range(0, cpuTasks)
                .flatMap(i -> Observable.fromCallable(BenchmarkTasks::cpuIntensiveTask).subscribeOn(Schedulers.computation()));

        // Para tarefas de I/O (API, disco), o IO Scheduler é o ideal
        Observable<String> apiObs = Observable.range(0, apiTasks)
                .flatMap(i -> Observable.fromCallable(BenchmarkTasks::apiCall).subscribeOn(Schedulers.io()));
        
        Observable<Integer> diskObs = Observable.range(0, diskTasks)
                .flatMap(i -> Observable.fromCallable(BenchmarkTasks::diskOp).subscribeOn(Schedulers.io()));

        // Combina e espera a conclusão
        Observable.merge(cpuObs, apiObs, diskObs.map(Object::toString)).blockingSubscribe();
    }

    public static void main(String[] args) {
        System.out.println("Running RxJava Example standalone...");
        new RxJavaExample().run(1000);
        System.out.println("...done.");
    }
}