package com.speechpeach.savemoney.ui.welcome.fragments.splashScreen

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.speechpeach.savemoney.R
import com.speechpeach.savemoney.database.FirebaseModule
import com.speechpeach.savemoney.database.User
import com.speechpeach.savemoney.profile.Profile
import com.speechpeach.savemoney.ui.content.ContentActivity
import com.speechpeach.savemoney.ui.welcome.fragments.signIn.SignInController
import com.speechpeach.savemoney.ui.welcome.fragments.signIn.SignInFragmentDirections

class SplashScreenFragment: Fragment(R.layout.fragment_splash_screen) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        SplashScreenController().apply {
            successCallback = { forTheFirstTime, referralCode, blocked ->
                Profile.init(User(
                        FirebaseModule.firebaseAuth.currentUser!!.uid,
                        FirebaseModule.firebaseAuth.currentUser!!.email!!,
                        FirebaseModule.firebaseAuth.currentUser!!.displayName!!,
                        referralCode,
                        blocked
                ))

                val intent = Intent(requireActivity(), ContentActivity::class.java)
                startActivity(intent)
                requireActivity().finish()
            }

            failedCallback = {
                val action = SplashScreenFragmentDirections.actionSplashScreenToSignInFragment()
                findNavController().navigate(action)
            }

            init()
        }

    }

}