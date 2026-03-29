package com.example.pelisproapp.presentacion.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pelisproapp.R
import com.example.pelisproapp.databinding.FragmentPelisDetailBinding


class PelisDetailFragment : Fragment() {

   private var _binding: FragmentPelisDetailBinding? = null
   private val binding get() = _binding!!


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


}