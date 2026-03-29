package com.example.pelisproapp.presentacion.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.pelisproapp.BuildConfig
import com.example.pelisproapp.R
import com.example.pelisproapp.databinding.FragmentPelisDetailBinding
import androidx.navigation.findNavController


class PelisDetailFragment : Fragment() {

   private var _binding: FragmentPelisDetailBinding? = null
   private val binding get() = _binding!!
    private val args by navArgs<PelisDetailFragmentArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPelisDetailBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        args.PELICULADETAIL?.let { it->
            binding.tvTitleMovie.text = it.peliculaTitle
            binding.tvDescription.text = it.peliculaOverview
            binding.tvRating.text = "${it.peliculaVoteAverage} (${it.peliculaPopularity} Votes)"
            binding.tvLanguage.text = "Language ${it.peliculaOriginalLanguage}"
            binding.tvRelease.text = "Released ${it.peliculaFechaLanzamiento}"
            Glide.with(requireContext())
                .load("${BuildConfig.BASE_URL_IMAGE}${it.peliculaPosterPath}")
                .error(R.drawable.green_snake_poster)
                .into(binding.imgPath)
            Glide.with(requireContext())
                .load("${BuildConfig.BASE_URL_IMAGE}${it.peliculaBackdrop}")
                .error(R.drawable.green_snake_backdrop)
                .into(binding.imgBackground)
        }

        binding.btnBackMovies.setOnClickListener {
            binding.root.findNavController().popBackStack(R.id.pelisFragment, false)
        }

    }


}