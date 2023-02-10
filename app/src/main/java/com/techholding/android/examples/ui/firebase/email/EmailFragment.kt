package com.techholding.android.examples.ui.firebase.email

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.techholding.android.examples.databinding.FragmentEmailAuthBinding

class EmailFragment : Fragment() {

    private var _binding: FragmentEmailAuthBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentEmailAuthBinding.inflate(inflater, container, false)



        return binding.root
    }
}