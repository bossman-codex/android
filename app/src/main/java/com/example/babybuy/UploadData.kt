package com.example.babybuy

import android.content.ContentValues.TAG
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.babybuy.databinding.ActivityUploadDataBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import java.io.IOException

class  UploadData : AppCompatActivity() {
    private lateinit var binding : ActivityUploadDataBinding
    private lateinit var itemTitle: TextView
    private lateinit var itemPrice: TextView
    private lateinit var itemDesc: TextView
    private lateinit var imageView: ImageView
    private lateinit var uploadButton: FloatingActionButton
    private lateinit var imageUri :Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding= ActivityUploadDataBinding.inflate(layoutInflater)
        setContentView(binding.root)
        itemTitle= binding.itemTitle
        itemPrice= binding.itemPrice
        itemDesc = binding.itemDescription
        imageView = binding.ImageView
        uploadButton = binding.uploadItem

        imageView.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST)
        }

        uploadButton.setOnClickListener {
            val storage = FirebaseStorage.getInstance()
            val db = Firebase.firestore
            val storageRef = storage.reference
            val imageRef = storageRef.child("images/" + imageUri.lastPathSegment)
            imageRef.putFile(imageUri)
                .continueWithTask { task ->
                if (!task.isSuccessful) {
                    task.exception?.let {
                        throw it
                    }
                }
                imageRef.downloadUrl
            }.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val downloadUri  = task.result
                    // Create a new user with a first, middle, and last name
                    val user = hashMapOf(
                        "title" to itemTitle.getText().toString(),
                        "price" to itemPrice.getText().toString(),
                        "description" to itemDesc.getText().toString(),
                        "image" to downloadUri.toString()
                    )

// Add a new document with a generated ID
                    db.collection("Data").document("okparathankgod55@gmail.com")
                        .update("items", FieldValue.arrayUnion(user))
                        .addOnSuccessListener {
                            Log.d(TAG, "DocumentSnapshot successfully written!")
                            Toast.makeText(baseContext, "success.",
                                Toast.LENGTH_SHORT).show()
                        }
                        .addOnFailureListener {
                                e -> Log.w(TAG, "Error writing document", e)
                            Toast.makeText(baseContext, "failed.",
                                Toast.LENGTH_SHORT).show()
                        }

                } else {
                    Toast.makeText(baseContext, "failure.",
                        Toast.LENGTH_SHORT).show()
                    // Handle failures
                    // ...
                }
            }
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
            && data != null && data.data != null
        ) {
            imageUri = data.data!!
            try {
                val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, imageUri)
                imageView.setImageBitmap(bitmap)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    companion object {
        private const val PICK_IMAGE_REQUEST = 1
    }
}
