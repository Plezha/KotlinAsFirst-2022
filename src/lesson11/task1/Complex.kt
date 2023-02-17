@file:Suppress("UNUSED_PARAMETER")

package lesson11.task1

import java.lang.IllegalArgumentException

/**
 * Фабричный метод для создания комплексного числа из строки вида x+yi
 */
fun Complex(s: String): Complex {
    if (!s.matches(Regex("""(-?\d+)* *((\+|\-) *\d+i)*""")) &&
        !s.matches(Regex("""\d+i"""))
    ) {
        throw IllegalArgumentException("") // TODO: add exception
    }

    var re = 0.0
    var im = 0.0

    val spl = s.replace(Regex("""[\si]"""), "").split('-', '+')
    if (spl.size == 3) {
        re = spl[1].toDouble()
        im = spl[2].toDouble()
    }
    if (spl.size == 2) {
        re = spl[0].toDouble()
        im = spl[1].toDouble()
    }
    if (spl.size == 1) {
        if (s.contains('i')) {
            im = spl[0].toDouble()
        } else {
            re = spl[0].toDouble()
        }
    }
    if (s[0] == '-') re = -re
    if (!s.contains('+') || (s[0] == '-' && spl.size==1 && s.contains('i'))) im = -im

    return Complex(re, im)
}

/**
 * Класс "комплексное число".
 *
 * Общая сложность задания -- лёгкая, общая ценность в баллах -- 8.
 * Объект класса -- комплексное число вида x+yi.
 * Про принципы работы с комплексными числами см. статью Википедии "Комплексное число".
 *
 * Аргументы конструктора -- вещественная и мнимая часть числа.
 */
class Complex(val re: Double, val im: Double) {

    /**
     * Конструктор из вещественного числа
     */
    constructor(x: Double) : this(x, 0.0)


    /**
     * Сложение.
     */
    operator fun plus(other: Complex): Complex =
        Complex(this.re + other.re, this.im + other.im)

    /**
     * Смена знака (у обеих частей числа)
     */
    operator fun unaryMinus(): Complex =
        Complex(-this.re, -this.im )
    /**
     * Вычитание
     */
    operator fun minus(other: Complex): Complex =
        this.plus(-other)

    /**
     * Умножение
     */
    operator fun times(other: Complex): Complex =
        Complex(
            this.re*other.re - this.im*other.im,
            this.re*other.im + this.im*other.re,
        )

    /**
     * Деление
     */
    operator fun div(other: Complex): Complex {
        if (other.re == 0.0 && other.im == 0.0) throw ArithmeticException("Complex division by zero")
        val a = this.re
        val b = this.im
        val c = other.re
        val d = other.im
        return Complex((a*c + b*d) / (c*c + d*d), (b*c - a*d) / (c*c + d*d))
    }

    /**
     * Сравнение на равенство
     */
    override fun equals(other: Any?): Boolean {
        if (other !is Complex ||
            other.re != this.re ||
            other.im != this.im)
            return false
        return true
    }

    /**
     * Преобразование в строку
     */
    override fun toString(): String = "${this.re}${if (this.im >= 0) "+" else ""}${this.im}i"
}
