package com.speechpeach.savemoney.ui.welcome.fragments.signIn

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.speechpeach.savemoney.R
import kotlinx.android.synthetic.main.fragment_sign_in.*

class SignInFragment : Fragment(R.layout.fragment_sign_in) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button_sign_in.setOnClickListener {
            val action = SignInFragmentDirections.actionSignInFragmentToPickReferralCodeFragment()
            findNavController().navigate(action)
        }

    }

}