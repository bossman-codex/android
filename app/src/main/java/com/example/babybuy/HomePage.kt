package com.example.babybuy

import Language
import Recyclerview
import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.babybuy.databinding.ActivityHomePageBinding
import com.google.firebase.firestore.Source
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


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


        val db = Firebase.firestore
        val docRef = db.collection("Data").document("okparathankgod55@gmail.com")

// Source can be CACHE, SERVER, or DEFAULT.
        val source = Source.DEFAULT

// Get the document, forcing the SDK to use the offline cache
        docRef.get(source).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // Document found in the offline cache
                val document = task.result
                for (doc in document?.data?.values!!){
                    Log.d(TAG, "Cached document data1: ${doc}")

                }
                Log.d(TAG, "Cached document data: ${document?.data?.values!!}")
            } else {
                Log.d(TAG, "Cached get failed: ", task.exception)
            }
        }



    }
    private fun loadLanguage() {
        languageList = listOf(
            Language("Java" , "Exp : 3 years", "https://th.bing.com/th/id/R.5ffeb9b88264485f1b265ff3c94e2dc2?rik=q1VHggJH5dpvmQ&riu=http%3a%2f%2fwww.thewowstyle.com%2fwp-content%2fuploads%2f2015%2f01%2fnature-image.jpg&ehk=bsDaBmDZgtasRhqb4OUzSkOAcwVCMTWMQ0XkPj4nrvQ%3d&risl=&pid=ImgRaw&r=0"),
            Language("Kotlin" , "Exp : 2 years"),
            Language("Python" , "Exp : 4 years"),
            Language("JavaScript" , "Exp : 6 years"),
            Language("PHP" , "Exp : 1 years"),
            Language("CPP" , "Exp : 8 years"),
        )
    }
}