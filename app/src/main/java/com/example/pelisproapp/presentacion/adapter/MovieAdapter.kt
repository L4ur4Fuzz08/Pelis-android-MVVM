package com.example.pelisproapp.presentacion.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pelisproapp.BuildConfig
import com.example.pelisproapp.R
import com.example.pelisproapp.core.BaseViewHolder
import com.example.pelisproapp.databinding.MovieItemBinding
import com.example.pelisproapp.presentacion.model.Pelicula


class MovieAdapter(private val context: Context, private var movieList: List<Pelicula> = listOf(), private val onItemClick: (pelicula:Pelicula)->Unit): RecyclerView.Adapter<BaseViewHolder<*>>() {

    interface OnMovieClickListener{
        fun onMovieClick(movie: Pelicula)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding = MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = MoviesViewHolder(itemBinding)

        itemBinding.root.setOnClickListener{
            val position = holder.bindingAdapterPosition.takeIf{ it != DiffUtil.DiffResult.NO_POSITION}
                ?:return@setOnClickListener
            onItemClick.invoke(movieList[position])


        }

        return holder
    }

    fun updateList(newList: List<Pelicula>){
        movieList = newList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = movieList.size

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder){
            is MoviesViewHolder-> holder.bind(movieList[position])
        }
    }

    private inner class MoviesViewHolder(val binding: MovieItemBinding) : BaseViewHolder<Pelicula>(binding.root) {
        override fun bind(item: Pelicula) {

            Glide.with(context)
                .load("${BuildConfig.BASE_URL_IMAGE}${item.peliculaPosterPath}")
                .placeholder(R.drawable.green_snake_poster)
                .centerCrop()
                .into(binding.imgMovieScreen)


        }

    }
}