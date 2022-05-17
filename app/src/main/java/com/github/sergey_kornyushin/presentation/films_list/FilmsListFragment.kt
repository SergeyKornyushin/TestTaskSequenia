package com.github.sergey_kornyushin.presentation.films_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.sergey_kornyushin.databinding.FragmentFilmsListBinding

class FilmsListFragment : Fragment() {
    private lateinit var binding: FragmentFilmsListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFilmsListBinding.inflate(inflater, container, false)
        return binding.root
    }
}