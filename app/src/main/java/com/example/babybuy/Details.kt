package com.example.babybuy

data class Details (
        var title: String,

        var body: String,

        var image: String,

        var username: String // Author of blog post


    ) {

        override fun toString(): String {
            return "BlogPost(title='$title', image='$image', username='$username')"
        }


    }
