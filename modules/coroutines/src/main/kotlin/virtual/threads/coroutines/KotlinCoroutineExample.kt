package virtual.threads.coroutines

import kotlinx.coroutines.*
import virtual.threads.core.BenchmarkTasks

fun main() {
    println("Running Kotlin Coroutines Example standalone...")
    KotlinCoroutineExample().run(1000)
    println("...done.")
}

class KotlinCoroutineExample {

    fun run(totalTasks: Int) = runBlocking {
        val cpuTasks = (totalTasks * 0.20).toInt()
        val apiTasks = (totalTasks * 0.40).toInt()
        val diskTasks = (totalTasks * 0.40).toInt()

        val jobs = mutableListOf<Job>()

        // Dispatchers.Default é otimizado para tarefas de CPU
        repeat(cpuTasks) {
            jobs += launch(Dispatchers.Default) { BenchmarkTasks.cpuIntensiveTask() }
        }

        // Dispatchers.IO é otimizado para tarefas de I/O (rede, disco)
        repeat(apiTasks) {
            jobs += launch(Dispatchers.IO) { BenchmarkTasks.apiCall() }
        }
        
        repeat(diskTasks) {
            jobs += launch(Dispatchers.IO) { BenchmarkTasks.diskOp() }
        }

        jobs.joinAll()
    }
}
