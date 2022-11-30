@file:Suppress("UNUSED_PARAMETER", "unused")

package lesson9.task1

import java.lang.StringBuilder

// Урок 9: проектирование классов
// Максимальное количество баллов = 40 (без очень трудных задач = 15)

/**
 * Ячейка матрицы: row = ряд, column = колонка
 */
data class Cell(val row: Int, val column: Int)

/**
 * Интерфейс, описывающий возможности матрицы. E = тип элемента матрицы
 */
interface Matrix<E> {
    /** Высота */
    val height: Int

    /** Ширина */
    val width: Int

    /**
     * Доступ к ячейке.
     * Методы могут бросить исключение, если ячейка не существует или пуста
     */
    operator fun get(row: Int, column: Int): E
    operator fun get(cell: Cell): E


    /**
     * Запись в ячейку.
     * Методы могут бросить исключение, если ячейка не существует
     */
    operator fun set(row: Int, column: Int, value: E)
    operator fun set(cell: Cell, value: E)
}

/**
 * Простая (2 балла)
 *
 * Метод для создания матрицы, должен вернуть РЕАЛИЗАЦИЮ Matrix<E>.
 * height = высота, width = ширина, e = чем заполнить элементы.
 * Бросить исключение IllegalArgumentException, если height или width <= 0.
 */
fun <E> createMatrix(height: Int, width: Int, e: E): Matrix<E> {
    if (height * width == 0) throw IllegalArgumentException("0 size else")
    return MatrixImpl(height, width, e)
}
/**
 * Средняя сложность (считается двумя задачами в 3 балла каждая)
 *
 * Реализация интерфейса "матрица"
 */
class MatrixImpl<E>(override val height: Int, override val width: Int, e: E) : Matrix<E> {
    val matrix = MutableList(height*width) {e}
    override fun get(row: Int, column: Int): E = matrix[width*row + column]

    override fun get(cell: Cell): E = matrix[width*cell.row + cell.column]



    override fun set(row: Int, column: Int, value: E) {
        matrix[width*row + column] = value
    }
    override fun set(cell: Cell, value: E) {
        matrix[width*cell.row + cell.column] = value
    }


    override fun equals(other: Any?): Boolean {
        if (other !is MatrixImpl<*> || other.width != width || other.height != height) return false

        return matrix == other.matrix
    }

    override fun toString(): String {
        val s = StringBuilder()
        for (row in 0 until height) {
            for (column in 0 until width) {
                s.append(matrix[width*row + column])
                if (column != width-1) s.append('\t')
            }
            if (row != height-1) s.append('\n')
        }

        return s.toString()
    }
    override fun hashCode(): Int {
        var result = height
        result = 31 * result + width
        result = 31 * result + matrix.hashCode()
        return result
    }
}

operator fun <E> Matrix<E>.get(n: Int): E = this[n/4, n%4]

operator fun <E> Matrix<E>.set(n: Int, value: E) {
    this[n/4, n%4] = value
}

fun <E> createMatrix(height: Int, width: Int, values: List<List<E>>): Matrix<E> {
    val matrix = createMatrix(height, width, values[0][0])
    for (row in 0 until height) {
        for (column in 0 until width) {
            matrix[row, column] = values[row][column]
        }
    }
    return matrix
}
