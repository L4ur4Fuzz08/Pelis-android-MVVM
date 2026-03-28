package com.example.pelisproapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewModelScope
import com.example.pelisproapp.core.Resource
import com.example.pelisproapp.data.remote.PeliculaDataSource
import com.example.pelisproapp.databinding.ActivityMainBinding
import com.example.pelisproapp.di.NetworkProvider
import com.example.pelisproapp.presentacion.PeliculaViewModelFactory
import com.example.pelisproapp.presentacion.viewmodel.PeliculaViewModel
import com.example.pelisproapp.repository.PelisRepository
import com.example.pelisproapp.repository.PelisRepositoryImp
import com.example.pelisproapp.repository.RetrofitClient
import com.example.pelisproapp.repository.WebSerice
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    /*se asigna el factory para los constructores*/
    /*
     Inyección del ViewModel usando un Factory delegado (by viewModels).
     El Factory es necesario porque el ViewModel recibe el 'Repository' por constructor.
     NetworkProvider provee la instancia real del repositorio.
  */
    private val  viewModel by viewModels<PeliculaViewModel> { PeliculaViewModelFactory(NetworkProvider.provideRepository()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configura los observadores que escucharán los cambios en el estado de los datos
        setObservers()



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
                            Log.e("PELICULAS", "upcoming: ${resource.data.t1}")
                            Log.e("PELICULAS", "top-rated: ${resource.data.t2}")
                            Log.e("PELICULAS", "popular: ${resource.data.t3}")
                        }
                        is Resource.Failure->{
                            Toast.makeText(this@MainActivity, "error: ${resource.e.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }
}