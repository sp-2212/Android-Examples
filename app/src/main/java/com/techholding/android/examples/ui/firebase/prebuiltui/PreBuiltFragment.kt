package com.techholding.android.examples.ui.firebase.prebuiltui

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.techholding.android.examples.R
import com.techholding.android.examples.databinding.FragmentPreBuiltAuthBinding
import com.techholding.android.examples.utils.UserDetail

class PreBuiltFragment : Fragment() {

    private var _binding: FragmentPreBuiltAuthBinding? = null
    private val binding get() = _binding!!
    private val signInLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()
    ) { res ->
        this.onSignInResult(res)
    }

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
            val providers = arrayListOf(
                AuthUI.IdpConfig.EmailBuilder().build(),
                AuthUI.IdpConfig.PhoneBuilder().build(),
                AuthUI.IdpConfig.GoogleBuilder().build(),
                AuthUI.IdpConfig.FacebookBuilder().build())

            val signInIntent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .setLogo(R.drawable.ic_launcher_foreground)
                .build()
            signInLauncher.launch(signInIntent)
        }
    }

    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {
        if (result.resultCode == RESULT_OK) {
            UserDetail.authState.value = true
        } else {
            UserDetail.authState.value = false
            Toast.makeText(context, "Sign-In Failed", Toast.LENGTH_SHORT).show()
        }
    }

    private fun initSignOutButton() {
        binding.signOut.setOnClickListener {
            UserDetail.signOut()
            Toast.makeText(context, "Sign-out Successful", Toast.LENGTH_SHORT).show()
        }
    }
}