package com.speechpeach.savemoney.ui.welcome.fragments.signIn

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.speechpeach.savemoney.R
import com.speechpeach.savemoney.database.FirebaseModule
import com.speechpeach.savemoney.database.User
import com.speechpeach.savemoney.profile.Profile
import com.speechpeach.savemoney.ui.content.ContentActivity
import kotlinx.android.synthetic.main.fragment_sign_in.*


class SignInFragment : Fragment(R.layout.fragment_sign_in) {

    private lateinit var controller: SignInController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        controller = SignInController(requireContext(), requireActivity(), getString(R.string.default_web_client_id)).apply {
            successCallback = { forTheFirstTime, referralCode, blocked ->
                Profile.init(User(
                    FirebaseModule.firebaseAuth.currentUser!!.uid,
                    FirebaseModule.firebaseAuth.currentUser!!.email!!,
                    FirebaseModule.firebaseAuth.currentUser!!.displayName!!,
                    referralCode,
                    blocked
                ))

                if (forTheFirstTime) {
                    firestore.collection(User.collection).add(Profile.user)
                    val action = SignInFragmentDirections.actionSignInFragmentToPickReferralCodeFragment()
                    findNavController().navigate(action)
                } else {
                    val intent = Intent(requireActivity(), ContentActivity::class.java)
                    startActivity(intent)
                    requireActivity().finish()
                }
            }

            failedCallback = {

            }

            init()
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button_sign_in.setOnClickListener {
            controller.launch()
        }
    }

}