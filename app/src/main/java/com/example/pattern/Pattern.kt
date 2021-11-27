package com.example.pattern

// Поведенческий паттерн Command.

// Команда - это интерфейс с методом выполнения. Это суть контракта.
// Клиент создает экземпляр реализации команды и связывает его с получателем.
// Вызывающий инструктирует команду выполнить действие.
// Экземпляр реализации Command создает привязку между получателем и действием.
// Получатель - это объект, который знает фактические шаги для выполнения действия.

// У нас UniversalRemote будет только две кнопки, одна для включения, а другая для отключения всех связанных ConsumerElectronics.

// Когда создается экземпляр вызывающего, ему передается конкретная команда. Invoker не знает, как выполнять действие. Все, что он делает, - это соблюдает соглашение с Command. Invoker вызывает execute() метод заданной конкретной команды.

interface Command {

    fun execute()
}

// OnCommand используется для включения ConsumerElectronics. При OnCommand создании экземпляра клиенту необходимо установить получателя. Здесь либо Televisionили SoundSystem.

class OnCommand(private val ce: ConsumerElectronics) : Command {

    override fun execute() {
        ce.on()
    }
}

class MuteAllCommand(internal var ceList: List<ConsumerElectronics>) : Command {

    override fun execute() {

        for (ce in ceList) {
            ce.mute()
        }
    }
}

// Для ConsumerElectronics, у нас будет пара реализаций Televisionи SoundSystem. Кнопка - это класс, вызывающий действие.

interface ConsumerElectronics {
    fun on()
    fun mute()
}

class Television : ConsumerElectronics {

    override fun on() {
        println("Television is on!")
    }

    override fun mute() {
        println("Television is muted!")
    }
}

class SoundSystem : ConsumerElectronics {

    override fun on() {
        println("Sound system is on!")
    }
    override fun mute() {
        println("Sound system is muted!")
    }
}

class Button(var c: Command) {

    fun click() {
        c.execute()
    }
}

// Класс UniversalRemote будет поддерживать все приемники и предоставит метод для идентификации
// текущего активного приемника.

class UniversalRemote {

    // электронная схема, поддерживающая устройство
    fun getActiveDevice() : ConsumerElectronics{
        val tv = Television()
        return tv
    }
}

fun main(args: Array<String>) {

    // OnCommand is instantiated based on active device supplied by Remote
    val ce = UniversalRemote.getActiveDevice()
    val onCommand = OnCommand(ce)
    val onButton = Button(onCommand)
    onButton.click()

    val tv = Television()
    val ss = SoundSystem()
    val all = ArrayList<ConsumerElectronics>()
    all.add(tv)
    all.add(ss)
    val muteAll = MuteAllCommand(all)
    val muteAllButton = Button(muteAll)
    muteAllButton.click()
}

// Пораждающий паттерн Factory.
// Сложности могут возникнуть, когда в нашем приложении есть некоторый класс, у которого есть множество наследников,
// и необходимо создавать экземпляр определенного класса в зависимости от некоторых условий.
// Фабрика — это шаблон проектирования,
// который помогает решить проблему создания различных объектов в зависимости от некоторых условий.
// Реализуем класс вирус, у которого будет несколько видов.

// Напишем интерфейс для нашего вируса.

interface Virus {
    public fun mutate()
    public fun spread() {
        println("Spreading the virus...")
    }
}

// Теперь нам нужно создать разные классы, реализующие Virus интерфейс.

// Как правило, этот шаблон более полезен, когда много классов, потому что, когда их всего несколько,
// можно обойти эту проблему и, возможно, избежать использования этого шаблона.

class CoronaVirus: Virus {
    override fun mutate() {
        println("Mutating the corona virus...")
    }
}

class InfluenzaVirus: Virus {
    override fun mutate() {
        println("Mutating the flu virus...")
    }
}

class HIVVirus: Virus {
    override fun mutate() {
        println("Mutating the HIV virus...")
    }
}

// Создание перечисления.
enum class VirusType {
    CORONA_VIRUS, INFLUENZA, HIV
}

// Создание фабрики, реализация VirusFactory.

class VirusFactory {
    fun makeVirus(type: VirusType): Virus? {
        return when(type) {
            VirusType.CORONA_VIRUS -> CoronaVirus()
            VirusType.INFLUENZA -> InfluenzaVirus()
            VirusType.HIV -> HIVVirus()
            else -> null
        }
    }
}

// Использование фабрики

//  когда вызвали функции mutate и spread поставили вопросительный знак перед доступом к свойствам и / или методам объекта. Этот вопросительный знак означает,
// что функции будут вызываться только в том случае, если virus объект не равен NULL.

fun main() {
    val factory = VirusFactory()
    val virus = factory.makeVirus(VirusType.CORONA_VIRUS)
    virus?.spread()
    virus?.mutate()
}

// Структурный паттерн Decorator.

interface Component {
    // примеры методов интерфейса
    fun methodA()
    fun methodB()
}
// реализация методов
class ConcreteComponent : Component {
    override fun methodA() {}
    override fun methodB() {}
}
// поле `component` защищено, что делает его доступным для наследующих классов
abstract class Decorator(protected val component: Component) : Component

// конкретная реализация Decorator с принудительным конструктором, требующим экземпляр Component
class ConcreteDecorator1(component: Component) : Decorator(component) {
    // методы должны быть переопределены
    // в этом случае Decorator вызывает обернутые методы экземпляра без каких-либо изменений
    override fun methodA() = component.methodA()
    override fun methodB() = component.methodB()
}
// другая реализация `Decorator`
class ConcreteDecorator2(component: Component) : Decorator(component) {
    override fun methodA(){
        // в этой реализации вы не можете использовать methodA()
        // это может быть связано с проверкой параметров Component, например
        throw Exception("you can't do this")
    }
    override fun methodB(){
        println("running methodB")
        component.methodB()
    }
}

fun mainDec(){
    val component: Component = ConcreteComponent()
    // первый декоратор, оборачивающий компонент
    val dec1: Component = ConcreteDecorator1(component)
    // второй декоратор, обертывающий уже завернутый компонент
    val dec2: Component = ConcreteDecorator2(dec1)
}