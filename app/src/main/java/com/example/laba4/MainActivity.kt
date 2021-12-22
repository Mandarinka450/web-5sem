package com.example.laba4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.laba4.databinding.ActivityMainBinding

interface TaskCallbacks {
    fun onPreExecuted()
    fun onCancelled()
    fun onPostExecute(i: String)
}

class MainActivity : AppCompatActivity(){

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val lastFragmentAsyncTask = supportFragmentManager.findFragmentByTag(AsyncFragment.MyTag)
        if (lastFragmentAsyncTask == null) {
            val transactionInitialization = supportFragmentManager
                .beginTransaction()
                .add(
                    R.id.fragment_container,
                    AsyncFragment(),
                    AsyncFragment.MyTag
                )
                .addToBackStack("added fragment")
            transactionInitialization.commit()
        }else {
            Log.d("fragment", "fragment is already exists")
        }

    }
}

