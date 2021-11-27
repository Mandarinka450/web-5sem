package com.example.pattern

// Структурный паттерн - Bridge.

// Допустим, у меня есть джинсы - их несколько моделей: скини (очень приталенные)
// и Mama (свободный тип, а-ля шаровары такие). И есть цвета у этих джинс - синий, черный.
// В будущем хочу себе бежевые, например, или еще какие.
// И вот есть класс Джинсы с подклассами.
// И для наших подклассов нужно будет создать 4 комбинации подклассов: Синие скини джинсы, черные скини джинсы,
// синие Mama джинсы и черные Mama джинсы.
// Добавление новой модели джинс и цвета потребует создание еще трех комбинацией для каждой модели джинс
// Да-да, вот такой я шопоголик, скупаю все джинсы.

// Цель данного паттерна - отделить абстракцию от ее реализации, чтобы они могли
// различаться независимо друг от друга.

// В данном случае абстракция - джинсы, реализация - цвет.

// Реализуем интерфейс для получения цвета. Реализатор
interface Color {
    fun getColor()
}

// Классы цветов

class Blue: Color {
    override fun getColor() {
        println("Синие джинсы")
    }
}

class Black: Color {
    override fun getColor() {
        println("Черные джинсы")
    }
}
// Абстракция - джинсы
interface Jeans {
    val color: Color
    fun show()
}

// Классы моделей джинс (расширяет абстракцию)
class SkiniJeans(override val color: Color): Jeans {
    override fun show() {
        print("Скини джинсы цвета ")
        color.getColor()
    }
}

class MamaJeans(override val color: Color): Jeans {
    override fun show() {
        print("Mama джинсы цвета ")
        color.getColor()
    }
}

// Джинсы и их цвета связываются уже в функции main, вызываясь непосредственно в самом коде
fun main() {
    val blueSkiniJeans = SkiniJeans(color = Blue())
    blueSkiniJeans.show()
    val blueMamaJeans = MamaJeans(color = Blue())
    blueMamaJeans.show()
    val blackSkiniJeans = SkiniJeans(color = Black())
    blackSkiniJeans.show()
    val blackMamaJeans = MamaJeans(color = Black())
    blackMamaJeans.show()
}

// В результате то паттерн Bridge отделил абстракцию, которая является Jeans, от его реализации,
// которая является Color, так что они могут различаться независимо друг от друга.