package com.example.myapplication.data

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@ProvidedTypeConverter

class Converter {
    // преобразование объекта в другой тип данных и наоборот
    @TypeConverter
    fun toString(nodes: List<Node>): String {
        return Gson().toJson(nodes)
    }
    @TypeConverter
    fun toNode(nodes: String): List<Node> {
        val convertType = object : TypeToken<List<Node>>() {}.type
        return Gson().fromJson(nodes, convertType)
    }

}