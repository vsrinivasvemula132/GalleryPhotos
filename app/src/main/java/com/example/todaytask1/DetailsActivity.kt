package com.example.todaytask1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val image3 = intent.getStringExtra("Image")
        val textView3 = intent.getStringExtra("Title")

        val textView31:TextView = findViewById(R.id.tvText_2)
        val image31:ImageView = findViewById(R.id.tvImage_2)

        textView31.text = textView3  //title
        Picasso.get().load(image3).into(image31)


    }

}