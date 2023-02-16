package com.techholding.android.examples.ui.firebase.email

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.techholding.android.examples.databinding.FragmentEmailAuthBinding


class EmailFragment : Fragment() {

    private var _binding: FragmentEmailAuthBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth
    private lateinit var email: String
    private lateinit var password: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentEmailAuthBinding.inflate(inflater, container, false)

        initListeners()

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
    }

    private fun check(email: String, password: String): Boolean {
        return email.isNotEmpty() && password.isNotEmpty()
    }

    private fun initListeners(){
        binding.signUp.setOnClickListener {
            signUpActivity()
        }
        binding.signIn.setOnClickListener {
            signInActivity()
        }
        binding.signOut.setOnClickListener {
            signOutActivity()
        }
    }

    private fun signUpActivity(){
        binding.signUp.setOnClickListener {
            email = binding.emailText.text.trim().toString()
            password = binding.passwordText.text.trim().toString()

            if (check(email, password)) {
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(requireActivity()) { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(requireContext(),"Please check your email and verify that", Toast.LENGTH_LONG).show()
                            sendEmailVerification()
                        }
                        else {
                            task.exception?.printStackTrace()
                            Toast.makeText(requireContext(),"Failed to Sign up", Toast.LENGTH_LONG).show()
                        }
                    }
            }
            else {
                Toast.makeText(requireContext(),"Enter valid email and password", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun signInActivity(){

        binding.signIn.setOnClickListener {
            email = binding.emailText.text.trim().toString()
            password = binding.passwordText.text.trim().toString()

            if (check(email, password)) {
                if (auth.currentUser?.isEmailVerified == true) {
                    Toast.makeText(requireContext(), "Please Sign in", Toast.LENGTH_LONG).show()
                    Log.d("jinil", auth.currentUser.toString())
                }
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(requireContext(),"Successfully Logged-in", Toast.LENGTH_LONG).show()
                            binding.emailText.text.clear()
                            binding.passwordText.text.clear()
                        }
                        else {
                            Toast.makeText(requireContext(),"Logged-in Failed", Toast.LENGTH_LONG).show()
                        }
                    }

            } else {
                Toast.makeText(requireContext(),"Enter valid email and password", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun sendEmailVerification() {
        Log.d("jinil","firebaseUser: ${auth.currentUser}")
        auth.currentUser?.let {
            it.sendEmailVerification().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(requireContext(),"verification sent on your email",Toast.LENGTH_LONG).show()
                    Log.d("jinil","verification sent")
                    binding.emailText.text.clear()
                    binding.passwordText.text.clear()
                }
                else{
                    task.exception?.printStackTrace()
                }
            }
        }

    }

    private fun signOutActivity(){
        binding.signOut.setOnClickListener {
            Firebase.auth.signOut()
            Log.d("jinil", "signed-out")
            Toast.makeText(requireContext(), "Successfully signed-out!", Toast.LENGTH_LONG).show()
            binding.emailText.text.clear()
            binding.passwordText.text.clear()
        }
    }

}