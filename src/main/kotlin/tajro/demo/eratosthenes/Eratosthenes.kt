package tajro.demo.eratosthenes

import kotlin.time.measureTimedValue

fun main() {
    println("Hello Eratosthenes!")
    val maxNumber = 10000000
    val repeat = 1
    val generator = PrimeNumbersGenerator(true)

    measureTimedValue {
        var result: Any? = null
        repeat(repeat) {
            result = generator
                .getNumberOfPrimeNumbers(maxNumber)
//                .getNthPrimeNumber(150, maxNumber)
//                .getAllPrimeNumbers(maxNumber)

        }
        result
    }.also {
        println("Time elapsed: ${it.duration}, Result: ${it.value}")
    }
}