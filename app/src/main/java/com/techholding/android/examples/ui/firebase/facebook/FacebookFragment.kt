package com.techholding.android.examples.ui.firebase.facebook

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.facebook.*
import com.facebook.login.LoginResult
import com.techholding.android.examples.databinding.FragmentFacebookAuthBinding
import com.techholding.android.examples.utils.FireBaseAuthManager

class FacebookFragment : Fragment() {

    private var _binding: FragmentFacebookAuthBinding? = null
    private val binding get() = _binding!!
    lateinit var callbackManager: CallbackManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFacebookAuthBinding.inflate(inflater, container, false)

        binding.loginButton.setReadPermissions(listOf("email"))
        callbackManager = CallbackManager.Factory.create()

        binding.loginButton.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onCancel() {
                Toast.makeText(context, "Sign-in Cancelled",Toast.LENGTH_SHORT).show()
            }

            override fun onError(error: FacebookException) {
                Toast.makeText(context, "Sign-in Unsuccessful",Toast.LENGTH_SHORT).show()
            }

            override fun onSuccess(result: LoginResult) {
                Toast.makeText(context, "Sign-in Successful",Toast.LENGTH_SHORT).show()
                FireBaseAuthManager.authState.value = true
            }
        })

        return binding.root
    }

    @Deprecated("Deprecated")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }
}