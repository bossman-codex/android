package com.example.babybuy

import Language
import Recyclerview
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.babybuy.databinding.ActivityHomePageBinding


class HomePage : AppCompatActivity() {
    private lateinit var binding: ActivityHomePageBinding
    // create reference to the adapter and the list
    // in the list pass the model of Language
    private lateinit var rvAdapter: Recyclerview
    private lateinit var languageList : List<Language>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // load data to language list
        loadLanguage()

        // create  layoutManager
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)

        // pass it to rvLists layoutManager
        binding.rvList.layoutManager = layoutManager

        // initialize the adapter,
        // and pass the required argument
        rvAdapter = Recyclerview(languageList)

        // attach adapter to the recycler view
        binding.rvList.adapter = rvAdapter

    }
    private fun loadLanguage() {
        languageList = listOf(
            Language("Java" , "Exp : 3 years"),
            Language("Kotlin" , "Exp : 2 years"),
            Language("Python" , "Exp : 4 years"),
            Language("JavaScript" , "Exp : 6 years"),
            Language("PHP" , "Exp : 1 years"),
            Language("CPP" , "Exp : 8 years"),
        )
    }
}