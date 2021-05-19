package com.speechpeach.savemoney.ui.content.fragments.manager

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.speechpeach.savemoney.R
import com.speechpeach.savemoney.ui.welcome.WelcomeActivity
import kotlinx.android.synthetic.main.fragment_manager.*

class ManagerFragment : Fragment(R.layout.fragment_manager) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button_manager.setOnClickListener {
            val intent = Intent(requireActivity(), WelcomeActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }

    }

}