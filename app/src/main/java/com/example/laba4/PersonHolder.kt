package com.example.laba4

import androidx.annotation.DrawableRes
import kotlinx.coroutines.flow.Flow


object PersonHolder {
    private val namePerson = arrayOf(
        "Хаяо Миядзаки",
        "Норман Ридус",
        "Билли Джо",
        "Оливер Сайкс",
        "Маргарет Тэтчер",
        "Генри Форд",
        "Фредди Меркьюри",
        "Фил Найт",
        "Леонардо ди Каприо",
        "Lana Del Rey"
    )
    private val sexPerson = arrayOf(
        "мужчина",
        "мужчина",
        "мужчина",
        "мужчина",
        "женщина",
        "мужчина",
        "мужчина",
        "мужчина",
        "мужчина",
        "женщина"
    )
    private val datePerson = arrayOf(
        "1941 - н.в.",
        "1969 - н.в.",
        "1972 - н.в.",
        "1986 - н.в.",
        "1925 - 2013",
        "1863 - 1947",
        "1946 - 1991",
        "1938 - н.в.",
        "1974 - н.в.",
        "1985 - н.в.",
    )

    private val infoPerson = arrayOf(
        "Всемирно знаменитый японский аниматор, режиссер и сценарист. Обладатель «Оскарa» за фильм («Унесенные призраками»). ",
        "Американский актёр и фотомодель, известный по своим ролям в сериале «Ходячие мертвецы», игре Death Stranding.",
        "Американский музыкант, певец, автор песен и актёр, наиболее известный как лид-вокалист и гитарист панк-рок группы Green Day. ",
        "Музыкант, лид-вокалист рок-группы Bring Me the Horizon. Также он создал фирму одежды Drop Dead Clothing. ",
        "Премьер-министр Великобритании в 1979-1990 годах, лидер Консервативной партии в 1975-1990 годах, баронесса с 1992 года.",
        "Американский промышленник, владелец заводов по производству автомобилей по всему миру, изобретатель. ",
        "Британский певец парсийского происхождения, автор песен и вокалист рок-группы Queen. Меркьюри в детстве филателистом.",
        "Американский бизнесмен, один из основателей компании Nike. Выпускник Орегонского университета и Стэнфордской школы бизнеса.",
        "Американский актёр и продюсер. Лауреат премии «Оскар», трёхкратный лауреат премии «Золотой глобус», «Серебряного медведя».",
        "Американская певица, автор песен и поэтесса. Её музыка была отмечена критиками за кинематографический стиль, меланхолией."
    )

    private val photo = arrayOf(
        R.drawable.miadzaki,
        R.drawable.norman,
        R.drawable.billi,
        R.drawable.oliver,
        R.drawable.tet,
        R.drawable.ford,
        R.drawable.freddi,
        R.drawable.fil,
        R.drawable.leonardo,
        R.drawable.lana
    )


    fun createCollectionPerson(): ArrayList<Person>{
        val persons: ArrayList<Person> = ArrayList<Person>()
        for (i in 0..9){
            val person = Person(
                namePerson[i],
                sexPerson[i],
                datePerson[i],
                infoPerson[i],
                photo[i]
            )
            persons.add(person)
        }
        return persons
    }


}

data class Person(
    val name: String,
    val sex: String,
    val date: String,
    val info: String,
    @DrawableRes val photoPerson: Int
)

