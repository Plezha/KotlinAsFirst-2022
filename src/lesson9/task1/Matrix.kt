@file:Suppress("UNUSED_PARAMETER", "unused")

package lesson9.task1

import lesson6.task1.twoDigitStr
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
    operator fun get(n: Int): E

    /**
     * Запись в ячейку.
     * Методы могут бросить исключение, если ячейка не существует
     */
    operator fun set(row: Int, column: Int, value: E)
    operator fun set(cell: Cell, value: E)
    operator fun set(n: Int, value: E)
}

/**
 * Простая (2 балла)
 *
 * Метод для создания матрицы, должен вернуть РЕАЛИЗАЦИЮ Matrix<E>.
 * height = высота, width = ширина, e = чем заполнить элементы.
 * Бросить исключение IllegalArgumentException, если height или width <= 0.
 */
fun <E> createMatrix(height: Int, width: Int, e: E): Matrix<E> = MatrixImpl(height, width, e)

/**
 * Средняя сложность (считается двумя задачами в 3 балла каждая)
 *
 * Реализация интерфейса "матрица"
 */
class MatrixImpl<E>(override val height: Int, override val width: Int, e: E) : Matrix<E> {
    val matrix = MutableList(height*width) {e}
    override fun get(row: Int, column: Int): E = matrix[width*row + column]

    override fun get(cell: Cell): E = matrix[width*cell.row + cell.column]

    override fun get(n: Int): E = matrix[n]


    override fun set(row: Int, column: Int, value: E) {
        matrix[width*row + column] = value
    }
    override fun set(cell: Cell, value: E) {
        matrix[width*cell.row + cell.column] = value
    }
    override fun set(n: Int, value: E) {
        matrix[n] = value
    }

    override fun equals(other: Any?): Boolean {
        if (other !is Matrix<*> || other.width != width || other.height != height) return false

        for (row in 0..height-1) {
            for (column in 0..width-1) {
                if (matrix[width*row + column] != other[row, column]) return false
            }
        }

        return true
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

fun <E> createMatrix(height: Int, width: Int, values: List<List<E>>): Matrix<E> {
    val matrix = createMatrix(height, width, values[0][0])
    for (row in 0 until height) {
        for (column in 0 until width) {
            matrix[row, column] = values[row][column]
        }
    }
    return matrix
}

