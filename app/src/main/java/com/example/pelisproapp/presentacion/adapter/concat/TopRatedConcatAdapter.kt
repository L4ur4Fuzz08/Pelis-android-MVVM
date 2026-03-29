package com.example.pelisproapp.presentacion.adapter.concat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pelisproapp.core.BaseConcatHolder
import com.example.pelisproapp.databinding.TopratedMovieRowBinding
import com.example.pelisproapp.presentacion.adapter.MovieAdapter
import com.example.pelisproapp.presentacion.model.Pelicula

class TopRatedConcatAdapter(private val moviesAdapter: MovieAdapter): RecyclerView.Adapter<BaseConcatHolder<*>>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseConcatHolder<*> {
        val itemBinding = TopratedMovieRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ConcatViewHolder(itemBinding)
    }

    override fun onBindViewHolder(
        holder: BaseConcatHolder<*>,
        position: Int
    ) {
        when(holder){
            is ConcatViewHolder-> holder.bind(moviesAdapter)
        }
    }

    fun updateAdapter(movies:List<Pelicula>){
        moviesAdapter.updateList(movies)
        notifyDataSetChanged()
    }
    //    Se define el tamaño de datos (1 solo recycler)
    override fun getItemCount(): Int =1

    private inner class ConcatViewHolder(val binding: TopratedMovieRowBinding): BaseConcatHolder<MovieAdapter>(binding.root){
        override fun bind(adapter: MovieAdapter) {
            binding.rvTopRated.adapter = adapter
        }

    }
}