@file:Suppress("UNUSED_PARAMETER")

package lesson9.task2

import lesson9.task1.Matrix
import lesson9.task1.createMatrix
import java.util.concurrent.TimeUnit
import kotlin.random.Random

// Все задачи в этом файле требуют наличия реализации интерфейса "Матрица" в Matrix.kt

/**
 * Пример
 *
 * Транспонировать заданную матрицу matrix.
 * При транспонировании строки матрицы становятся столбцами и наоборот:
 *
 * 1 2 3      1 4 6 3
 * 4 5 6  ==> 2 5 5 2
 * 6 5 4      3 6 4 1
 * 3 2 1
 */
fun <E> transpose(matrix: Matrix<E>): Matrix<E> {
    if (matrix.width < 1 || matrix.height < 1) return matrix
    val result = createMatrix(height = matrix.width, width = matrix.height, e = matrix[0, 0])
    for (i in 0 until matrix.width) {
        for (j in 0 until matrix.height) {
            result[i, j] = matrix[j, i]
        }
    }
    return result
}

/**
 * Пример
 *
 * Сложить две заданные матрицы друг с другом.
 * Складывать можно только матрицы совпадающего размера -- в противном случае бросить IllegalArgumentException.
 * При сложении попарно складываются соответствующие элементы матриц
 */
operator fun Matrix<Int>.plus(other: Matrix<Int>): Matrix<Int> {
    require(!(width != other.width || height != other.height))
    if (width < 1 || height < 1) return this
    val result = createMatrix(height, width, this[0, 0])
    for (i in 0 until height) {
        for (j in 0 until width) {
            result[i, j] = this[i, j] + other[i, j]
        }
    }
    return result
}

/**
 * Сложная (5 баллов)
 *
 * Заполнить матрицу заданной высоты height и ширины width
 * натуральными числами от 1 до m*n по спирали,
 * начинающейся в левом верхнем углу и закрученной по часовой стрелке.
 *
 * Пример для height = 3, width = 4:
 *  1  2  3  4
 * 10 11 12  5
 *  9  8  7  6
 */
fun generateSpiral(height: Int, width: Int): Matrix<Int> = TODO()

/**
 * Сложная (5 баллов)
 *
 * Заполнить матрицу заданной высоты height и ширины width следующим образом.
 * Элементам, находящимся на периферии (по периметру матрицы), присвоить значение 1;
 * периметру оставшейся подматрицы – значение 2 и так далее до заполнения всей матрицы.
 *
 * Пример для height = 5, width = 6:
 *  1  1  1  1  1  1
 *  1  2  2  2  2  1
 *  1  2  3  3  2  1
 *  1  2  2  2  2  1
 *  1  1  1  1  1  1
 */
fun generateRectangles(height: Int, width: Int): Matrix<Int> = TODO()

/**
 * Сложная (5 баллов)
 *
 * Заполнить матрицу заданной высоты height и ширины width диагональной змейкой:
 * в левый верхний угол 1, во вторую от угла диагональ 2 и 3 сверху вниз, в третью 4-6 сверху вниз и так далее.
 *
 * Пример для height = 5, width = 4:
 *  1  2  4  7
 *  3  5  8 11
 *  6  9 12 15
 * 10 13 16 18
 * 14 17 19 20
 */
fun generateSnake(height: Int, width: Int): Matrix<Int> = TODO()

/**
 * Средняя (3 балла)
 *
 * Содержимое квадратной матрицы matrix (с произвольным содержимым) повернуть на 90 градусов по часовой стрелке.
 * Если height != width, бросить IllegalArgumentException.
 *
 * Пример:    Станет:
 * 1 2 3      7 4 1
 * 4 5 6      8 5 2
 * 7 8 9      9 6 3
 */
fun <E> rotate(matrix: Matrix<E>): Matrix<E> = TODO()

/**
 * Сложная (5 баллов)
 *
 * Проверить, является ли квадратная целочисленная матрица matrix латинским квадратом.
 * Латинским квадратом называется матрица размером n x n,
 * каждая строка и каждый столбец которой содержат все числа от 1 до n.
 * Если height != width, вернуть false.
 *
 * Пример латинского квадрата 3х3:
 * 2 3 1
 * 1 2 3
 * 3 1 2
 */
fun isLatinSquare(matrix: Matrix<Int>): Boolean = TODO()

/**
 * Средняя (3 балла)
 *
 * В матрице matrix каждый элемент заменить суммой непосредственно примыкающих к нему
 * элементов по вертикали, горизонтали и диагоналям.
 *
 * Пример для матрицы 4 x 3: (11=2+4+5, 19=1+3+4+5+6, ...)
 * 1 2 3       11 19 13
 * 4 5 6  ===> 19 31 19
 * 6 5 4       19 31 19
 * 3 2 1       13 19 11
 *
 * Поскольку в матрице 1 х 1 примыкающие элементы отсутствуют,
 * для неё следует вернуть как результат нулевую матрицу:
 *
 * 42 ===> 0
 */
fun sumNeighbours(matrix: Matrix<Int>): Matrix<Int> = TODO()

/**
 * Средняя (4 балла)
 *
 * Целочисленная матрица matrix состоит из "дырок" (на их месте стоит 0) и "кирпичей" (на их месте стоит 1).
 * Найти в этой матрице все ряды и колонки, целиком состоящие из "дырок".
 * Результат вернуть в виде Holes(rows = список дырчатых рядов, columns = список дырчатых колонок).
 * Ряды и колонки нумеруются с нуля. Любой из спискоов rows / columns может оказаться пустым.
 *
 * Пример для матрицы 5 х 4:
 * 1 0 1 0
 * 0 0 1 0
 * 1 0 0 0 ==> результат: Holes(rows = listOf(4), columns = listOf(1, 3)): 4-й ряд, 1-я и 3-я колонки
 * 0 0 1 0
 * 0 0 0 0
 */
fun findHoles(matrix: Matrix<Int>): Holes = TODO()

/**
 * Класс для описания местонахождения "дырок" в матрице
 */
data class Holes(val rows: List<Int>, val columns: List<Int>)

/**
 * Средняя (3 балла)
 *
 * В целочисленной матрице matrix каждый элемент заменить суммой элементов подматрицы,
 * расположенной в левом верхнем углу матрицы matrix и ограниченной справа-снизу данным элементом.
 *
 * Пример для матрицы 3 х 3:
 *
 * 1  2  3      1  3  6
 * 4  5  6  =>  5 12 21
 * 7  8  9     12 27 45
 *
 * К примеру, центральный элемент 12 = 1 + 2 + 4 + 5, элемент в левом нижнем углу 12 = 1 + 4 + 7 и так далее.
 */
fun sumSubMatrix(matrix: Matrix<Int>): Matrix<Int> = TODO()

/**
 * Простая (2 балла)
 *
 * Инвертировать заданную матрицу.
 * При инвертировании знак каждого элемента матрицы следует заменить на обратный
 */
operator fun Matrix<Int>.unaryMinus(): Matrix<Int> = TODO(this.toString())

/**
 * Средняя (4 балла)
 *
 * Перемножить две заданные матрицы друг с другом.
 * Матрицы можно умножать, только если ширина первой матрицы совпадает с высотой второй матрицы.
 * В противном случае бросить IllegalArgumentException.
 * Подробно про порядок умножения см. статью Википедии "Умножение матриц".
 */
operator fun Matrix<Int>.times(other: Matrix<Int>): Matrix<Int> = TODO(this.toString())

/**
 * Сложная (7 баллов)
 *
 * Даны мозаичные изображения замочной скважины и ключа. Пройдет ли ключ в скважину?
 * То есть даны две матрицы key и lock, key.height <= lock.height, key.width <= lock.width, состоящие из нулей и единиц.
 *
 * Проверить, можно ли наложить матрицу key на матрицу lock (без поворота, разрешается только сдвиг) так,
 * чтобы каждой единице в матрице lock (штырь) соответствовал ноль в матрице key (прорезь),
 * а каждому нулю в матрице lock (дырка) соответствовала, наоборот, единица в матрице key (штырь).
 * Ключ при сдвиге не может выходить за пределы замка.
 *
 * Пример: ключ подойдёт, если его сдвинуть на 1 по ширине
 * lock    key
 * 1 0 1   1 0
 * 0 1 0   0 1
 * 1 1 1
 *
 * Вернуть тройку (Triple) -- (да/нет, требуемый сдвиг по высоте, требуемый сдвиг по ширине).
 * Если наложение невозможно, то первый элемент тройки "нет" и сдвиги могут быть любыми.
 */
fun canOpenLock(key: Matrix<Int>, lock: Matrix<Int>): Triple<Boolean, Int, Int> = TODO()

/**
 * Сложная (8 баллов)
 *
 * В матрице matrix размером 4х4 дана исходная позиция для игры в 15, например
 *  5  7  9  1
 *  2 12 14 15
 *  3  4  6  8
 * 10 11 13  0
 *
 * Здесь 0 обозначает пустую клетку, а 1-15 – фишки с соответствующими номерами.
 * Напомним, что "игра в 15" имеет квадратное поле 4х4, по которому двигается 15 фишек,
 * одна клетка всегда остаётся пустой. Цель игры -- упорядочить фишки на игровом поле.
 *
 * В списке moves задана последовательность ходов, например [8, 6, 13, 11, 10, 3].
 * Ход задаётся номером фишки, которая передвигается на пустое место (то есть, меняется местами с нулём).
 * Фишка должна примыкать к пустому месту по горизонтали или вертикали, иначе ход не будет возможным.
 * Все номера должны быть в пределах от 1 до 15.
 * Определить финальную позицию после выполнения всех ходов и вернуть её.
 * Если какой-либо ход является невозможным или список содержит неверные номера,
 * бросить IllegalStateException.
 *
 * В данном случае должно получиться
 * 5  7  9  1
 * 2 12 14 15
 * 0  4 13  6
 * 3 10 11  8
 */
fun fifteenGameMoves(matrix: Matrix<Int>, moves: List<Int>): Matrix<Int> {
    for (move in moves) {
        //println(matrix)
        //print("$move, trying:")
        var row = 0
        var column = 0
        if (move !in 1..15) throw IllegalStateException("Move must be in 1..15")

        while (matrix[row, column] != move) {
            //print("$row, $column: matrix[row, column]")
            if (column == 3) {
                column = -1
                row++
            }
            column++
        }
        //println()
        if (column < 3 && matrix[row, column + 1] == 0) matrix[row, column] = matrix[row, column + 1].also {
            matrix[row, column + 1] = matrix[row, column]
        }
        else if (column > 0 && matrix[row, column - 1] == 0) matrix[row, column] = matrix[row, column - 1].also {
            matrix[row, column - 1] = matrix[row, column]
        }
        else if (row < 3 && matrix[row + 1, column] == 0) matrix[row, column] = matrix[row + 1, column].also {
            matrix[row + 1, column] = matrix[row, column]
        }
        else if (row > 0 && matrix[row - 1, column] == 0) matrix[row, column] = matrix[row - 1, column].also {
            matrix[row - 1, column] = matrix[row, column]
        }
        else throw IllegalStateException("$move has no 0 neighbour")
    }

    return matrix
}

/**
 * Очень сложная (32 балла)
 *
 * В матрице matrix размером 4х4 дана исходная позиция для игры в 15, например
 *  5  7  9  2
 *  1 12 14 15
 *  3  4  6  8
 * 10 11 13  0
 *
 * Здесь 0 обозначает пустую клетку, а 1-15 – фишки с соответствующими номерами.
 * Напомним, что "игра в 15" имеет квадратное поле 4х4, по которому двигается 15 фишек,
 * одна клетка всегда остаётся пустой.
 *
 * Цель игры -- упорядочить фишки на игровом поле, приведя позицию к одному из следующих двух состояний:
 *
 *  1  2  3  4          1  2  3  4
 *  5  6  7  8   ИЛИ    5  6  7  8
 *  9 10 11 12          9 10 11 12
 * 13 14 15  0         13 15 14  0
 *
 * Можно математически доказать, что РОВНО ОДНО из этих двух состояний достижимо из любой исходной позиции.
 *
 * Вернуть решение -- список ходов, приводящих исходную позицию к одной из двух упорядоченных.
 * Каждый ход -- это перемена мест фишки с заданным номером с пустой клеткой (0),
 * при этом заданная фишка должна по горизонтали или по вертикали примыкать к пустой клетке (но НЕ по диагонали).
 * К примеру, ход 13 в исходной позиции меняет местами 13 и 0, а ход 11 в той же позиции невозможен.
 *
 * Одно из решений исходной позиции:
 *
 * [8, 6, 14, 12, 4, 11, 13, 14, 12, 4,
 * 7, 5, 1, 3, 11, 7, 3, 11, 7, 12, 6,
 * 15, 4, 9, 2, 4, 9, 3, 5, 2, 3, 9,
 * 15, 8, 14, 13, 12, 7, 11, 5, 7, 6,
 * 9, 15, 8, 14, 13, 9, 15, 7, 6, 12,
 * 9, 13, 14, 15, 12, 11, 10, 9, 13, 14,
 * 15, 12, 11, 10, 9, 13, 14, 15]
 *
 * Перед решением этой задачи НЕОБХОДИМО решить предыдущую
 */
fun fifteenGameSolution(matrix: Matrix<Int>): List<Int> {
    val ans = MutableList(0){0}
    val whereIs = MutableList(16){-1 to -1} // Some sort of map
    for (i in 0..3) {
        for (j in 0..3) {
            whereIs[matrix[i, j]] = i to j
        }
    }

    fun aMove(n: Int, mode: Int = 0) {
        val row = if (mode == 1) n/4 else whereIs[n].first
        val column = if (mode == 1) n%4 else whereIs[n].second
        for ((i, j) in listOf((0 to 1), (0 to -1), (1 to 0), (-1 to 0))) {
            if (row + i in 0..3 && column + j in 0..3 && matrix[row + i, column + j] == 0)  {
                matrix[row, column] = matrix[row + i, column + j].also {
                    matrix[row + i, column + j] = matrix[row, column]
                }
                whereIs[matrix[row, column]] = whereIs[matrix[row + i, column + j]].also {
                    whereIs[matrix[row + i, column + j]] = whereIs[matrix[row, column]]
                }
                return
            }
        }
        throw IllegalStateException("$n has no 0 neighbour")
    }


    fun ease() {
        while (true) {
            var easy = true
            for (i in 0..7) if (matrix[i/4, i%4] !in 0..7) easy = false
            if (matrix[2,3] != 8) easy = false
            if (easy) break
            // Make random move
            val row = whereIs[0].first
            val column = whereIs[0].second
            for ((i, j) in listOf((0 to 1), (0 to -1), (1 to 0), (-1 to 0))) {
                if (row + i in 0..3 && column + j in 0..3 && Random.nextInt(3) == 0) {
                    ans.add(matrix[row + i, column + j])
                    matrix[row, column] = matrix[row + i, column + j].also {
                        matrix[row + i, column + j] = matrix[row, column]
                    }
                    whereIs[matrix[row, column]] = whereIs[matrix[row + i, column + j]].also {
                        whereIs[matrix[row + i, column + j]] = whereIs[matrix[row, column]]
                    }
                    break
                }
            }
        }
        println(matrix)
    }

    fun solve2x4l() {
        val winCombs = mutableSetOf(
            listOf(9,10,11,12,
                13,14,15,0),
            listOf(9,10,11,12,
                13,15,14,0),
            /*listOf(13,9,10,11,
                14,15,0,12),
            listOf(13,9,10,11,
                15,14,0,12),
            listOf(14,13,9,10,
                15,0,12,11),
            listOf(15,13,9,10,
                14,0,12,11),
            listOf(15,14,13,9,
                0,12,11,10),
            listOf(14,15,13,9,
                0,12,11,10),*/
        )
        //println(winCombs)
        while (true) {
            val curComb = MutableList(0){0}
            for (i in 8..15) curComb.add(matrix[i/4, i%4])
            curComb.toList() // Any better ways to "curComb = [matrix[i//4, i%4] for i in range(8,16)]" in Kotlin?
            if (curComb in winCombs) break
            // Make random move within 2 bottom rows
            val row = whereIs[0].first
            val column = whereIs[0].second
            for ((i, j) in listOf((0 to 1), (0 to -1), (1 to 0), (-1 to 0))) {
                if (row + i in 2..3 && column + j in 0..3 && Random.nextInt(3) == 0) {
                    ans.add(matrix[row + i, column + j])
                    matrix[row, column] = matrix[row + i, column + j].also {
                        matrix[row + i, column + j] = matrix[row, column]
                    }
                    whereIs[matrix[row, column]] = whereIs[matrix[row + i, column + j]].also {
                        whereIs[matrix[row + i, column + j]] = whereIs[matrix[row, column]]
                    }
                    //println("$matrix\n")
                    break
                }
            }
        }
        //println(winCombs)
        println("$matrix\n")
    }

    fun solve2x4u() {
        val winCombs = mutableSetOf(
            listOf(1,2,3,4,
                5,6,7,0),
            listOf(1,2,3,4,
                5,7,6,0))
        //println(winCombs)
        while (true) {
            val curComb = MutableList(0){0}
            for (i in 0..7) curComb.add(matrix[i/4, i%4])
            curComb.toList()
            if (curComb in winCombs) break
            // Make random move within 2 upper rows
            val row = whereIs[0].first
            val column = whereIs[0].second
            for ((i, j) in listOf((0 to 1), (0 to -1), (1 to 0), (-1 to 0))) {
                if (row + i in 0..1 && column + j in 0..3 && Random.nextInt(3) == 0) {
                    ans.add(matrix[row + i, column + j])
                    matrix[row, column] = matrix[row + i, column + j].also {
                        matrix[row + i, column + j] = matrix[row, column]
                    }
                    whereIs[matrix[row, column]] = whereIs[matrix[row + i, column + j]].also {
                        whereIs[matrix[row + i, column + j]] = whereIs[matrix[row, column]]
                    }
                    //println("$matrix\n")
                    break
                }
            }
        }
        //println(winCombs)
        println("$matrix\n")
    }

    ease()
    println("eased")
    TimeUnit.SECONDS.sleep(3)
    solve2x4u()
    if (matrix[1,1] == 7) {
        1
    }
    ans.add(8)
    aMove(8)
    solve2x4l()
    TimeUnit.SECONDS.sleep(3)
    return ans
}
