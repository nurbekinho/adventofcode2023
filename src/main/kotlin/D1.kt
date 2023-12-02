import java.io.File

fun main() {
    d1q1()
    d1q2()
}

fun d1q1() {
    var total = 0
    val txt = File(ClassLoader.getSystemResource("d1q1.txt").file).readText()
    for (line in txt.lines()) {
        val numbers = line.toCharArray().filter { char -> char.isDigit() }
        total += numbers.first().toString().plus(numbers.last().toString()).toInt()
    }

    println(total)
}

fun d1q2() {
    var total = 0
    val map = mapOf(
        "one" to 1,
        "two" to 2,
        "three" to 3,
        "four" to 4,
        "five" to 5,
        "six" to 6,
        "seven" to 7,
        "eight" to 8,
        "nine" to 9,
        "1" to 1,
        "2" to 2,
        "3" to 3,
        "4" to 4,
        "5" to 5,
        "6" to 6,
        "7" to 7,
        "8" to 8,
        "9" to 9,
    )
    val txt = File(ClassLoader.getSystemResource("d1q2.txt").file).readText()
    for (line in txt.lines()) {
        var left = 0
        var right = 0
        var indexL = line.length + 1
        var indexR = -1
        for (pair in map) {
            val iLeft = line.indexOf(pair.key)
            if (iLeft > -1 && iLeft < indexL) {
                indexL = iLeft
                left = pair.value
            }

            val iRight = line.indexOf(pair.key)
            if (iRight > indexR) {
                indexR = iRight
                right = pair.value
            }
        }

        total += left * 10 + right
    }

    println(total)
}