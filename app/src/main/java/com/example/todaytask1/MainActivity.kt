package com.example.todaytask1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerview: RecyclerView

    //https://www.flickr.com/services/rest/?method=flickr.galleries.getPhotos&api_key=f9736f4d370f9c7115a952951b506569&gallery_id=66911286-72157647277042064&format=json&nojsoncallback=1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerview = findViewById(R.id.recyclerview1)


        val retrofit1 = Retrofit.Builder()
            .baseUrl("https://www.flickr.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val retro2 = retrofit1.create(ApiInterface::class.java)
        val result1 = retro2.getData()
        result1.enqueue(object:Callback<GalleryPhotos>{
            override fun onResponse(call: Call<GalleryPhotos>, response: Response<GalleryPhotos>) {
                if(response.code() == 200){
                    val result = response.body()
                    Log.d("srinivas ",result.toString())
                    if (result != null) {
                        galleryData(result)

                    }
                }
            }
            override fun onFailure(call: Call<GalleryPhotos>, t: Throwable) {

            }

        })
    }
    fun galleryData(result: GalleryPhotos){
        val adapter1 = GalleryAdapter(result)
        val layoutManager = GridLayoutManager(this,2)
        recyclerview.layoutManager = layoutManager
        recyclerview.adapter = adapter1

        adapter1.itemClick = { url,title ->
            val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra("Image", url)
            intent.putExtra("Title",title)

            Toast.makeText(this@MainActivity, "the $title image is clicked", Toast.LENGTH_LONG).show()
            startActivity(intent)
        }
    }
}