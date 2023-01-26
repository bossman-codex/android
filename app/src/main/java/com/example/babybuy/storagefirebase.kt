package com.example.babybuy

import android.net.Uri
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

import java.io.File

class storagefirebase {
    val storage = Firebase.storage

    // [START upload_create_reference]
    // Create a storage reference from our app
    private val storageRef = storage.reference
    var file = Uri.fromFile(File("path/to/images/rivers.jpg"))
    val imageRef = storageRef.child("images/${file.lastPathSegment}")
    var uploadTask = imageRef.putFile(file)

    val urlTask = uploadTask.continueWithTask { task ->
        if (!task.isSuccessful) {
            task.exception?.let {
                throw it
            }
        }
        imageRef.downloadUrl
    }.addOnCompleteListener { task ->
        if (task.isSuccessful) {
            val downloadUri = task.result
        } else {
            // Handle failures
            // ...
        }
    }
}