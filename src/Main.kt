import java.util.Scanner

class Tablero {
    private val tablero = Array(3) { Array(3) { ' ' } }


    fun mostrarTablero() {
        println("\n  1 2 3")
        for (i in 0..2) {
            print("${i + 1} ")
            for (j in 0..2) {
                print(tablero[i][j])
                if (j < 2) print("|")
            }
            println()
            if (i < 2) println("  -----")
        }
    }


    fun marcarCelda(fila: Int, columna: Int, jugador: Char): Boolean {
        return if (tablero[fila][columna] == ' ') {
            tablero[fila][columna] = jugador
            true
        } else {
            false
        }
    }


    fun hayGanador(): Char? {

        for (i in 0..2) {
            if (tablero[i][0] != ' ' && tablero[i][0] == tablero[i][1] && tablero[i][1] == tablero[i][2]) return tablero[i][0]
            if (tablero[0][i] != ' ' && tablero[0][i] == tablero[1][i] && tablero[1][i] == tablero[2][i]) return tablero[0][i]
        }

        if (tablero[0][0] != ' ' && tablero[0][0] == tablero[1][1] && tablero[1][1] == tablero[2][2]) return tablero[0][0]
        if (tablero[0][2] != ' ' && tablero[0][2] == tablero[1][1] && tablero[1][1] == tablero[2][0]) return tablero[0][2]

        return null
    }

    fun estaLleno(): Boolean {
        return tablero.all { fila -> fila.all { it != ' ' } }
    }
}

class JuegoTresEnRaya {
    private val tablero = Tablero()
    private var turno = 'X'
    private val scanner = Scanner(System.`in`)

    fun jugar() {
        println("¡Bienvenido al Tres en Raya!")

        while (true) {
            tablero.mostrarTablero()
            println("Turno del jugador $turno")
            val (fila, columna) = pedirMovimiento()

            if (tablero.marcarCelda(fila, columna, turno)) {
                val ganador = tablero.hayGanador()
                if (ganador != null) {
                    tablero.mostrarTablero()
                    println("¡Felicidades! El jugador $ganador ha ganado.")
                    break
                } else if (tablero.estaLleno()) {
                    tablero.mostrarTablero()
                    println("¡Empate! No quedan más movimientos.")
                    break
                }

                turno = if (turno == 'X') 'O' else 'X'
            } else {
                println("¡Celda ocupada! Intenta de nuevo.")
            }
        }
    }

    private fun pedirMovimiento(): Pair<Int, Int> {
        while (true) {
            print("Ingrese fila y columna (1-3, separados por espacio): ")
            val entrada = readLine()?.split(" ")

            if (entrada?.size == 2) {
                val fila = entrada[0].toIntOrNull()?.minus(1)
                val columna = entrada[1].toIntOrNull()?.minus(1)

                if (fila != null && columna != null && fila in 0..2 && columna in 0..2) {
                    return Pair(fila, columna)
                }
            }

            println("Entrada inválida. Intenta de nuevo.")
        }
    }

}


fun main() {
    val juego = JuegoTresEnRaya()
    juego.jugar()
}
