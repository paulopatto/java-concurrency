package virtual.threads

import java.time.Instant
import java.time.Duration
import kotlinx.coroutines.*
import at.favre.lib.crypto.bcrypt.BCrypt

object KotlinCoroutineExample {
    private const val TASKS = 1_000
    private const val PASSWORD = "password123"

    private fun cpuInstensiveTask(): String {
        // Simula uma tarefa intensiva em CPU, como hashing de senha
        return BCrypt
            .withDefaults()
            .hashToString(12, PASSWORD.toCharArray())
    }


    // Esta função será chamada pelo código Java
    // por isso tem de usar essa anotação @JvmStatic
    @JvmStatic
    fun run() {
        runBlocking { // runBlocking é usado para iniciar a coroutine a partir de um código não-suspenso
            val jobs = List(TASKS) {
                launch(Dispatchers.Default) { // Lança a coroutine em um dispatcher otimizado para tarefas de CPU
                    cpuInstensiveTask()
                }
            }
            jobs.joinAll() // Aguarda todas as coroutines terminarem que nem o Promise.all do Node.js
        }
    }

    fun main() {
        println("Iniciando exemplo autônomo com Coroutines do Kotlin...") 
        val start = Instant.now()
        KotlinCoroutineExample.run()
        val duration = Duration.between(start, Instant.now()).toMillis()
        println("...exemplo com Coroutines do Kotlin finalizado em $duration ms.")
    }
}