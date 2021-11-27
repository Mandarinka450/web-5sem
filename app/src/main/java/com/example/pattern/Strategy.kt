package com.example.pattern

// Поведенческий паттерн - Strategy.
// В шаблоне стратегии мы создаем объекты, которые представляют различные стратегии и объект контекста,
// поведение которого зависит от его стратегии.
// Объект стратегии изменяет алгоритм выполнения объекта контекста.

// Простой пример использования
class GameIndustry(val strategy: (String) -> String) {
    fun print(string: String): String = strategy(string)
}

val lowerCaseFormatter: (String) -> String = String::toLowerCase
val upperCaseFormatter: (String) -> String = String::toUpperCase

fun main(args: Array<String>) {
    val lower = GameIndustry(strategy = lowerCaseFormatter)
    println(lower.print("OUTLAST 3"))
    val upper = GameIndustry(strategy = upperCaseFormatter)
    println(upper.print("FARCRY 6"))
}

// В зависимости от выбранной стратегии название игр будет либо в нижнем регистре показываться либо в верхнем.