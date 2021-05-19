package com.speechpeach.savemoney.ui.welcome.fragments.pickReferralCode

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.speechpeach.savemoney.R
import com.speechpeach.savemoney.ui.content.ContentActivity
import kotlinx.android.synthetic.main.fragment_pick_referral_code.*


class PickReferralCodeFragment : Fragment(R.layout.fragment_pick_referral_code) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button_pick_referral_code.setOnClickListener {
            val intent = Intent(requireActivity(), ContentActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }

    }

}