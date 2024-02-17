package tajro.demo.eratosthenes

import kotlin.math.sqrt

class PrimeNumbersGenerator(
    private val printResults: Boolean = false
) {
    private fun printMe(message: String) = if (printResults) { print(message) } else Unit

    /**
     * Sieve of Eratosthenes
     */
    private fun getPrimeNumbers(limit: Int): Sequence<Int> {
        val candidates = (2..limit).toMutableSet()
        val sqrt = sqrt(limit.toDouble()).toInt()
        return sequence {
            // Loop will stop once first() returns NoSuchElementException
            try {
                while (true) {
                    candidates.first().let { prime ->
                        yield(prime)
                        if (prime <= sqrt) {
                            candidates.filter { it <= limit / prime }
                                .map { prime * it }
                                .let {
                                    candidates.removeAll(it)
                                }
                        }
                        candidates.remove(prime)
                    }
                }
            } catch (e: Exception) {
                if (e !is NoSuchElementException) {
                    throw e
                }
            }
        }
    }

    fun getNthPrimeNumber(n: Int, limit: Int): Int =
        getPrimeNumbers(limit).iterator().let { iterator ->
            repeat(n-1) {
                printMe(" ${iterator.next()}")
            }
            return iterator.next()
        }

    fun getAllPrimeNumbers(limit: Int): List<Int> =
        getPrimeNumbers(limit).toList()

    fun getNumberOfPrimeNumbers(limit: Int): Int =
        getPrimeNumbers(limit).count()
}