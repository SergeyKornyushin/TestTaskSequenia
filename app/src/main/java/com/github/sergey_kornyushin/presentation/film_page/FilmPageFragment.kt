package com.github.sergey_kornyushin.presentation.film_page

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.sergey_kornyushin.R
import com.github.sergey_kornyushin.databinding.FragmentFilmPageBinding
import com.squareup.picasso.Picasso

class FilmPageFragment : Fragment() {
    private lateinit var binding: FragmentFilmPageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFilmPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Picasso.get().load("https://st.kp.yandex.net/images/film_iphone/iphone360_326.jpg").into(binding.imgFilmPoster)
    }
}