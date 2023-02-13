package com.techholding.android.examples.ui.firebase.phone

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.techholding.android.examples.databinding.FragmentPhoneAuthBinding
import java.util.concurrent.TimeUnit

class PhoneFragment : Fragment() {

    private var _binding: FragmentPhoneAuthBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth:FirebaseAuth
    private lateinit var number: String
    private lateinit var storedVerificationId: String
    private lateinit var resendToken: PhoneAuthProvider.ForceResendingToken
    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    private lateinit var otp: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPhoneAuthBinding.inflate(inflater,container,false)

        initSignin()
        initSignout()

        return binding.root

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
        FirebaseApp.initializeApp(requireContext())
//        val credential = PhoneAuthProvider.getCredential(verificationId!!, code)
        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            // This method is called when the verification is completed
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
//                signInWithPhoneAuthCredential(credential)
                Log.d("Jinil" , "onVerificationCompleted Success")
            }
            override fun onVerificationFailed(e: FirebaseException) {

            }
            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {

                storedVerificationId = verificationId
                resendToken = token

            }
        }
    }

//    private fun Check(number:String){
//        var isValid=true
//        if(number.isNotEmpty()){
//            if (number.length==10){
//
//            }
//        }
//    }

    private fun initSignin(){
        binding.verficationBtn.setOnClickListener {
            number = binding.phoneLogin.text.trim().toString()
            if(number.isNotEmpty()){
                if(number.length==10){
                    number="+91$number"

                    sendVerification(number)
                    verifyOtp()
                }
                else{
                    Toast.makeText(requireContext(),"Please enter valid number",Toast.LENGTH_LONG).show()
                }
            }
            else{
                Toast.makeText(requireContext(),"Please enter a number",Toast.LENGTH_LONG).show()
            }
        }
        binding.resendCode.setOnClickListener {
            number = binding.phoneLogin.text.trim().toString()
            number="+91$number"
            binding.verificationCode.text.clear()
            resendVerificationCode(number)
            verifyOtp()
        }
    }

    private fun sendVerification(number: String){
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(number)       // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(requireActivity())                 // Activity (for callback binding)
            .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
        Log.d("jinil" , "Verification sent")
    }

    private fun resendVerificationCode(number: String){

        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(number)       // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(requireActivity())                 // Activity (for callback binding)
            .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
        Log.d("jinil" ,number.toString())

    }

    private fun verifyOtp(){

        binding.phoneButton.setOnClickListener {

            otp = binding.verificationCode.text.trim().toString()

            if (otp.isNotEmpty()) {
                val credential: PhoneAuthCredential =
                    PhoneAuthProvider.getCredential(storedVerificationId, otp)
                signInWithPhoneAuthCredential(credential)
            } else {
                Toast.makeText(requireContext(), "Enter OTP", Toast.LENGTH_LONG).show()
            }
        }
    }
    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    binding.verificationCode.text.clear()
                    Toast.makeText(requireContext(),"Successfully Logged In",Toast.LENGTH_LONG).show()

                } else {

                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        binding.verificationCode.text.clear()
                        Toast.makeText(requireContext(),"Invalid OTP", Toast.LENGTH_LONG).show()
                    }

                }
            }
    }
    private fun initSignout(){

        binding.phnSignOut.setOnClickListener {
            Firebase.auth.signOut()
            Log.d("jinil", "signed-out")
            Toast.makeText(requireContext(),"Successfully signed-out!", Toast.LENGTH_LONG).show()
            binding.phoneLogin.text.clear()
            binding.verificationCode.text.clear()
        }

    }
}