import java.io.File

fun main() {
    d2q1()
    d2q2()
}

fun d2q1() {
    var total = 0
    val red = 12
    val green = 13
    val blue = 14
    val txt = File(ClassLoader.getSystemResource("d2q1.txt").file).readText()
    for (line in txt.lines()) {
        val colonIndex = line.indexOf(":")
        val gameId = line.substring(0, colonIndex).split(" ").last().toInt()
        val sets = line.substring(colonIndex + 1).split(";")
        var possible = true
        for (set in sets) {
            val rgb = set.split(",")
            for (cubes in rgb) {
                val cube = cubes.split(" ")
                if (cube.last().toString() == "red" && cube[cube.size - 2].toInt() > red
                    || cube.last().toString() == "green" && cube[cube.size - 2].toInt() > green
                    || cube.last().toString() == "blue" && cube[cube.size - 2].toInt() > blue) {
                    possible = false
                    break
                }
            }
        }

        if (possible) total += gameId
    }

    println(total)
}

fun d2q2() {
    var total = 0
    val txt = File(ClassLoader.getSystemResource("d2q2.txt").file).readText()
    for (line in txt.lines()) {
        val colonIndex = line.indexOf(":")
        val reds = mutableSetOf(0)
        val greens = mutableSetOf(0)
        val blues = mutableSetOf(0)
        val sets = line.substring(colonIndex + 1).split(";")
        for (set in sets) {
            val rgb = set.split(",")
            for (cubes in rgb) {
                val cube = cubes.split(" ")
                if (cube.last().toString() == "red") {
                    reds.add(cube[cube.size - 2].toInt())
                }
                if (cube.last().toString() == "green") {
                    greens.add(cube[cube.size - 2].toInt())
                }
                if (cube.last().toString() == "blue") {
                    blues.add(cube[cube.size - 2].toInt())
                }
            }
        }

        total += reds.max() * greens.max() * blues.max()
    }

    println(total)
}