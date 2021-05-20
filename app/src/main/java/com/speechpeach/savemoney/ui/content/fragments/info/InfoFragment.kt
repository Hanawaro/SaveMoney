package com.speechpeach.savemoney.ui.content.fragments.info

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.speechpeach.savemoney.R
import com.speechpeach.savemoney.profile.Profile
import com.speechpeach.savemoney.ui.welcome.WelcomeActivity
import kotlinx.android.synthetic.main.fragment_info.*

class InfoFragment : Fragment(R.layout.fragment_info) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button_info.text = Profile.name

        button_info.setOnClickListener {
            Profile.logout(requireContext(), getString(R.string.default_web_client_id))

            val intent = Intent(requireActivity(), WelcomeActivity::class.java)
            startActivity(intent)

            requireActivity().finish()
        }

    }

}