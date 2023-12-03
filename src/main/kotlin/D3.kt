import java.io.File
import java.lang.Exception
import java.lang.StringBuilder

fun main() {
    d3q1()
    d3q2()
}

fun d3q1() {
    var total = 0
    val sides = arrayOf(-1 to -1, -1 to 0, -1 to 1, 1 to -1, 1 to 1, 1 to 0, 0 to -1, 0 to 1)
    val txt = File(ClassLoader.getSystemResource("d3q1.txt").file).readText()
    val charList = txt.lines().map { it.toCharArray() }
    for (i in charList.indices) {
        val digits = StringBuilder()
        val symbols = mutableSetOf<Char>()
        for (j in charList[i].indices + 1) {
            val current = try { charList[i][j] } catch (e: Exception) { '.' }
            if (current.isDigit()) {
                digits.append(current)
                for (side in sides) {
                    val symbol = try { charList[i + side.first][j + side.second] } catch (e: Exception) { '.' }
                    if (!symbol.isDigit() && symbol != '.') symbols.add(symbol)
                }
            } else {
                if (digits.isNotEmpty() && symbols.size > 0) {
                    total += digits.toString().toInt()
                }

                digits.clear()
                symbols.clear()
            }
        }
    }

    println(total)
}

fun d3q2() {
    var total = 0
    val gears:MutableMap<String, MutableList<Int>> = mutableMapOf()
    val sides = arrayOf(-1 to -1, -1 to 0, -1 to 1, 1 to -1, 1 to 1, 1 to 0, 0 to -1, 0 to 1)
    val txt = File(ClassLoader.getSystemResource("d3q2.txt").file).readText()
    val charList = txt.lines().map { it.toCharArray() }
    for (i in charList.indices) {
        val digits = StringBuilder()
        var symbolCoordinate = ""
        for (j in charList[i].indices + 1) {
            val current = try { charList[i][j] } catch (e: Exception) { '.' }
            if (current.isDigit()) {
                digits.append(current)
                for (side in sides) {
                    val symbol = try { charList[i + side.first][j + side.second] } catch (e: Exception) { '.' }
                    if (!symbol.isDigit() && symbol != '.') {
                        symbolCoordinate = "i${i + side.first}-j${j + side.second}-$symbol"
                        break
                    }
                }
            } else {
                if (digits.isNotEmpty() && symbolCoordinate.isNotEmpty()) {
                    if (gears.containsKey(symbolCoordinate)) gears[symbolCoordinate]?.add(digits.toString().toInt())
                    else gears[symbolCoordinate] = mutableListOf(digits.toString().toInt())
                }

                digits.clear()
                symbolCoordinate = ""
            }
        }
    }

    for (gear in gears.filter { it.value.size == 2 }) {
        total += gear.value[0] * gear.value[1]
    }

    println(total)
}