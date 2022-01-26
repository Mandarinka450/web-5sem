package com.example.myapplication.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Node::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class Db : RoomDatabase() {
    // подключаем абстрактный интерфейс, относящийся к базе данных
    abstract fun nodeDao(): Dao
    companion object {
        // ключевое слово применяется к полям и гарантируют, что считываемое значение поступает из основной памяти,
        // поэтому все участники процесса будут ожидать окончания параллельной записи, прежде чем считать значение.
        @Volatile
        private var INSTANCE: Db? = null
        private val converterInstance = Converter()
        fun getDatabase(context: Context): Db {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    Db::class.java,
                    "node_database"
                )
                    .allowMainThreadQueries()
                    .addTypeConverter(converterInstance)
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}