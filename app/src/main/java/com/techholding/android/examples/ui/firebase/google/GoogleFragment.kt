package com.techholding.android.examples.ui.firebase.google

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.techholding.android.examples.R
import com.techholding.android.examples.databinding.FragmentGoogleAuthBinding
import com.techholding.android.examples.utils.FireBaseAuthManager


class GoogleFragment : Fragment() {

    private var _binding: FragmentGoogleAuthBinding? = null
    private val binding get() = _binding!!
    private val reqCode = 123
    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGoogleAuthBinding.inflate(inflater, container, false)

        initSignInButton()
        initSignOutButton()

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)
        firebaseAuth = FirebaseAuth.getInstance()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        FireBaseAuthManager.authState.observe(viewLifecycleOwner, Observer {
            when(FireBaseAuthManager.authState.value)
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

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode)
        {
            reqCode -> {
                    try {
                        val account: GoogleSignInAccount? = GoogleSignIn.getSignedInAccountFromIntent(data)
                            .getResult(ApiException::class.java)

                        if(account!=null)
                        {
                            val credential = GoogleAuthProvider.getCredential(account.idToken, null)
                            firebaseAuth.signInWithCredential(credential).addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    FireBaseAuthManager.authState.value=true
                                    Toast.makeText(context, "Sign-in Successful", Toast.LENGTH_SHORT).show()
                                }
                                else
                                {
                                    Toast.makeText(context, "Sign-in Unsuccessful", Toast.LENGTH_SHORT).show()
                                }
                            }
                        }
                        else
                        {
                            Toast.makeText(context, "Sign-in Unsuccessful", Toast.LENGTH_SHORT).show()
                        }
                    }
                    catch(e: ApiException) {
                        Toast.makeText(context, "Sign-in Cancelled", Toast.LENGTH_SHORT).show()
                    }
            }
        }
    }

    private fun initSignInButton() {
        binding.signIn.setOnClickListener {
            val signInIntent: Intent = mGoogleSignInClient.signInIntent
            startActivityForResult(signInIntent, reqCode)
        }
    }

    private fun initSignOutButton() {
        binding.signOut.setOnClickListener {
            FireBaseAuthManager.signOut()
            Toast.makeText(context, "Sign-out Successful", Toast.LENGTH_SHORT).show()
        }
    }
}