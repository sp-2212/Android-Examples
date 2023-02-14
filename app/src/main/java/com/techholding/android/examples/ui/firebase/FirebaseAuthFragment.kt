package com.techholding.android.examples.ui.firebase

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.techholding.android.examples.R
import com.techholding.android.examples.databinding.FragmentFirebaseAuthBinding

class FirebaseAuthFragment : Fragment() {

    private var _binding: FragmentFirebaseAuthBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirebaseAuthBinding.inflate(inflater, container, false)

//        binding.emailAuthentication.setOnClickListener {
//            view?.findNavController()?.navigate(
//                FirebaseAuthFragmentDirections.actionFirebaseAuthFragmentToEmailFragment()
//            )
//        }
//
//        binding.phoneAuthentication.setOnClickListener {
//            view?.findNavController()?.navigate(
//                FirebaseAuthFragmentDirections.actionFirebaseAuthFragmentToPhoneFragment()
//            )
//        }
//
//        binding.googleAuthentication.setOnClickListener {
//            view?.findNavController()?.navigate(
//                FirebaseAuthFragmentDirections.actionFirebaseAuthFragmentToGoogleFragment()
//            )
//        }

        return binding.root
    }

    private fun emailButton(){
        view?.findNavController()?.navigate(
            FirebaseAuthFragmentDirections.actionFirebaseAuthFragmentToEmailFragment()
        )
    }

    private fun phoneButton(){
        view?.findNavController()?.navigate(
            FirebaseAuthFragmentDirections.actionFirebaseAuthFragmentToPhoneFragment()
        )
    }

    private fun googleButton(){
        view?.findNavController()?.navigate(
            FirebaseAuthFragmentDirections.actionFirebaseAuthFragmentToGoogleFragment()
        )
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.emailAuthentication.setOnClickListener {
            emailButton()
        }
        binding.phoneAuthentication.setOnClickListener {
            phoneButton()
        }
        binding.googleAuthentication.setOnClickListener {
            googleButton()
        }

    }


}