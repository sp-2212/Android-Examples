package com.techholding.android.examples.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.techholding.android.examples.R
import com.techholding.android.examples.databinding.FragmentAuthBinding
import com.techholding.android.examples.ui.firebase.FirebaseAuthFragment
import com.techholding.android.examples.ui.firebase.FirebaseAuthFragmentDirections

class AuthFragment : Fragment() {

    private lateinit var navController: NavController
    private var _binding: FragmentAuthBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAuthBinding.inflate(inflater, container, false)

//        binding.firebaseAuthentication.setOnClickListener {
//            view?.findNavController()?.navigate(R.id.action_authFragment_to_firebaseAuthFragment)
//        }

        binding.awsAuthentication.isEnabled = false

//        binding.firebaseAuthentication.setOnClickListener {
//            initListeners()
//        }

        return binding.root
    }

    private fun initListeners(){
        view?.findNavController()?.navigate(
            R.id.action_authFragment_to_firebaseAuthFragment
        )
        binding.awsAuthentication.isEnabled = false

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.firebaseAuthentication.setOnClickListener {
            initListeners()
        }
    }

}