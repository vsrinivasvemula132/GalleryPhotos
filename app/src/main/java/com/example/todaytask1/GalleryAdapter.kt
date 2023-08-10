package com.example.todaytask1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class GalleryAdapter(private val listPhotos: GalleryPhotos): RecyclerView.Adapter<GalleryAdapter.ViewHolder>() {


    var itemClick : ((url : String,title : String) -> Unit)? = null

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val tvImage1: ImageView = itemView.findViewById(R.id.tvImage)
        val tvTitle: TextView = itemView.findViewById(R.id.tvText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.each_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvTitle.text = listPhotos.photos.photo[position].title
        val farm = listPhotos.photos.photo[position].farm
        val server = listPhotos.photos.photo[position].server
        val id = listPhotos.photos.photo[position].id
        val secret = listPhotos.photos.photo[position].secret
        val url = "https://farm" +farm.toString()+ ".staticflickr.com/" + server + "/" + id + "_" + secret + ".jpg"
        Picasso.get().load(url).into(holder.tvImage1)

        holder.itemView.setOnClickListener{
            itemClick?.invoke(url,listPhotos.photos.photo[position].title)
        }
    }

    override fun getItemCount(): Int {
        return listPhotos.photos.photo.size
    }

}