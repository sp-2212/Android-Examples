package com.techholding.android.examples.ui.firebase.prebuiltui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.techholding.android.examples.databinding.FragmentPreBuiltAuthBinding
import com.techholding.android.examples.utils.UserDetail

class PreBuiltFragment : Fragment() {

    private var _binding: FragmentPreBuiltAuthBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentPreBuiltAuthBinding.inflate(inflater, container, false)

        initSignInButton()
        initSignOutButton()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        UserDetail.authState.observe(viewLifecycleOwner, Observer {
            when(UserDetail.authState.value)
            {
                true -> {
                    binding.signIn.isEnabled=false
                    binding.signOut.isEnabled=true
                }
                else -> {
                    binding.signIn.isEnabled=true
                    binding.signOut.isEnabled=false
                }
            }
        })
    }

    private fun initSignInButton() {
        binding.signIn.setOnClickListener {

        }
    }

    private fun initSignOutButton() {
        binding.signOut.setOnClickListener {
            UserDetail.signOut()
            Toast.makeText(context, "Sign-out Successful", Toast.LENGTH_SHORT).show()
        }
    }
}