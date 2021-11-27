package com.example.pattern

// Паттерн - фабрика.
// Это порождающий паттерн, в котором изначально создается один класс или интерфейс,
// от которого после создаются подклассы с расширенными свойствами.

// В данном случаем реализуем интерфейс - Program, на основе которого будут создаваться классы JavaProgram,
// PythonProgram. Они будут содержать свою логику - запуститься. Но имеют свои особенности - создавать
// проект на языке java или python.

interface Program {
    public fun startToGo() {
        println("I'm ready to work")
    }
}

// создаем подклассы со своей логикой
class JavaProgram: Program {
    override fun createProject() {
        println("I'm creating project on java")
    }
}

class PythonProgram: Program {
    override fun createProject() {
        println("I am creating project on python")
    }
}

// создаем фабрику
class Factory {
    fun makeProgram(type: ProgType): Program? {
        return when(type) {
            ProgType.JAVA -> JavaProgram()
            ProgType.PYTHON -> PythonProgram()
            else -> null
        }
    }
}

// создаем перечисление
enum class ProgType {
    JAVA,
    PYTHON
}

// использование фабрики
fun main() {
    val factory = Factory()
    val program = factory.makeProgram(ProgType.JAVA)
    program?.startToGo()
    program?.createProject()
}