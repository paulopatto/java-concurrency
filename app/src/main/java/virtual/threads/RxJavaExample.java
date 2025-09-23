package virtual.threads;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RxJavaExample {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Iniciando exemplo com RxJava...");
        Observable.range(1, 5)
            .map(i -> {
                System.out.println("Mapeando " + i + " em: " + Thread.currentThread().getName());
                return i * 2;
            })
            .subscribeOn(Schedulers.computation()) // Executa em uma thread de computação
            .subscribe(result -> System.out.println("Recebido: " + result + " em: " + Thread.currentThread().getName()));
        
        // Aguarda um pouco para a thread assíncrona terminar
        Thread.sleep(1000);
        System.out.println("...exemplo com RxJava finalizado.");
    }
}
