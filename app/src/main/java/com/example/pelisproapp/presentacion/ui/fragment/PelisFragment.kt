package com.example.pelisproapp.presentacion.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pelisproapp.core.Resource
import com.example.pelisproapp.databinding.FragmentPelisBinding
import com.example.pelisproapp.di.NetworkProvider
import com.example.pelisproapp.presentacion.PeliculaViewModelFactory
import com.example.pelisproapp.presentacion.adapter.MovieAdapter
import com.example.pelisproapp.presentacion.adapter.concat.PopularConcatAdapter
import com.example.pelisproapp.presentacion.adapter.concat.TopRatedConcatAdapter
import com.example.pelisproapp.presentacion.adapter.concat.UpcomingConcatAdapter
import com.example.pelisproapp.presentacion.model.Pelicula
import com.example.pelisproapp.presentacion.viewmodel.PeliculaViewModel
import kotlinx.coroutines.launch


class PelisFragment : Fragment() {


    private  var _binding: FragmentPelisBinding? = null
    private val binding get() = _binding!!

    private lateinit var topRatedMoviesAdapter: TopRatedConcatAdapter
    private lateinit var upcomingMoviesAdapter: UpcomingConcatAdapter
    private lateinit var popularMoviesAdapter: PopularConcatAdapter
    private lateinit var concatAdapter: ConcatAdapter
    private val viewModel by viewModels<PeliculaViewModel> {
        PeliculaViewModelFactory(NetworkProvider.provideRepository())
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       _binding = FragmentPelisBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*initialize all adapters*/
        setAdapters()
        /*initialize concatadapter*/
        setConcat()
        // Configura los observadores que escucharán los cambios en el estado de los datos
        setObservers()
    }

    private fun setConcat() {
        concatAdapter = ConcatAdapter()
        concatAdapter.apply {
            /*add adapters item in order*/
            addAdapter(0, topRatedMoviesAdapter)
            addAdapter(1, upcomingMoviesAdapter)
            addAdapter(2, popularMoviesAdapter)
        }
    }

    private fun setAdapters() {
        topRatedMoviesAdapter = TopRatedConcatAdapter(
            MovieAdapter(requireContext(), listOf()){
                onMovieClick(it)
            }
        )
        upcomingMoviesAdapter = UpcomingConcatAdapter(
            MovieAdapter(requireContext(), listOf()){
                onMovieClick(it)
            }
        )
        popularMoviesAdapter = PopularConcatAdapter(
            MovieAdapter(requireContext(), listOf()){
                onMovieClick(it)
            }
        )
    }


    private fun setObservers() {
        // Lanza una corrutina para observar el Flow de datos del ViewModel
        lifecycleScope.launch {
            /*
               repeatOnLifecycle(STARTED): Suspende la recolección cuando la app va al fondo
               y la reanuda cuando vuelve al frente, optimizando recursos y batería.
            */
            repeatOnLifecycle(Lifecycle.State.STARTED){
                // Se suscribe al StateFlow 'movieState' para recibir actualizaciones
                viewModel.movieState.collect{resource->
                    when(resource){
                        // Evalúa el estado actual de la petición (Cargando, Éxito o Error)
                        is Resource.Loading-> {}
                        is Resource.Success->{
                            binding.rlProgressBar.visibility = View.GONE
                            topRatedMoviesAdapter.updateAdapter(resource.data.topRated)
                            upcomingMoviesAdapter.updateAdapter(resource.data.upcoming)
                            popularMoviesAdapter.updateAdapter(resource.data.popular)
                            /*initialize recycler concat*/
                            setRecyclerMovies()
                        }
                        is Resource.Failure->{
                            Toast.makeText(requireContext(), "error: ${resource.e.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }

    private fun onMovieClick(item: Pelicula){
        Toast.makeText(requireContext(), "click: ${item.peliculaTitle}", Toast.LENGTH_SHORT).show()
    }

    private fun setRecyclerMovies(){
        binding.rvPeliculas.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = concatAdapter
        }
    }


}