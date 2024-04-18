package com.example.assessment_image.adapter

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.assessment_image.databinding.ItemUnsplashBinding
import com.example.assessment_image.model.UnsplashResponse
import java.util.concurrent.Executors


class UnsplashViewHolder(private val binding: ItemUnsplashBinding) : ViewHolder(binding.root) {

    fun bind(item: UnsplashResponse?) {

        val executor = Executors.newSingleThreadExecutor()
        val handler = Handler(Looper.getMainLooper())
        var image: Bitmap? = null

        // Only for Background process (can take time depending on the Internet speed)
        executor.execute {

            // Image URL
            val imageURL = item?.urls?.small

            // Tries to get the image and post it in the ImageView
            // with the help of Handler
            try {
                val `in` = java.net.URL(imageURL).openStream()
                image = BitmapFactory.decodeStream(`in`)

                // Only for making changes in UI
                handler.post {

                    binding.img.setImageBitmap(image)

                }
            }

            // If the URL doesnot point to
            // image or any other kind of failure
            catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }

    companion object {
        fun create(view: ViewGroup): UnsplashViewHolder {
            val inflater = LayoutInflater.from(view.context)
            val binding = ItemUnsplashBinding.inflate(inflater, view, false)
            return UnsplashViewHolder(binding)
        }
    }

}