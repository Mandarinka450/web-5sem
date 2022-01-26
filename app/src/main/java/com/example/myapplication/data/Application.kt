package com.example.myapplication.data

import android.app.Application

class Application : Application() {
    val database: Db by lazy { Db.getDatabase(this) }
}