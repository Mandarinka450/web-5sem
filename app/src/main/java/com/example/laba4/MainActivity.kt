package com.example.laba4


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.laba4.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(){

//    private var personCreate = PersonHolder.createCollectionPerson()

    private val verticalLinearLayoutManager: LinearLayoutManager =
        LinearLayoutManager(this, RecyclerView.VERTICAL, false)

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecycleView()
    }

    private fun setupRecycleView(){
        binding.cardPerson.layoutManager = verticalLinearLayoutManager
        binding.cardPerson.adapter = PersonAdapter(PersonHolder.createCollectionPerson(), ::showSnackbar, ::showSnackbarLike)

    }

    private fun showSnackbar(person: Person): Unit{
        Snackbar.make(binding.root, "Нажата карточка: " + person.name, 3000).show()
    }
    private  fun showSnackbarLike(person: Person): Unit{
        Snackbar.make(binding.root, "Нажат лайк:  " + person.name, 3000).show()
    }



}