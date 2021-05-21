package com.speechpeach.savemoney.ui.content.fragments.info

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.speechpeach.savemoney.R
import com.speechpeach.savemoney.database.User
import com.speechpeach.savemoney.profile.Profile
import com.speechpeach.savemoney.ui.content.fragments.info.referralCodeDialog.ReferralCodeDialog
import com.speechpeach.savemoney.ui.welcome.WelcomeActivity
import kotlinx.android.synthetic.main.fragment_info.*

class InfoFragment : Fragment(R.layout.fragment_info) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        text_view_info_name.text = Profile.name
        text_view_info_email.text = Profile.email

        button_edit_referral_code.setOnClickListener {
            val dialog = ReferralCodeDialog()
            dialog.referralCode = Profile.referralCode
            dialog.show(requireActivity().supportFragmentManager, "Referral code")
        }

        button_edit_get_referral_code.setOnClickListener {
            val textToCopy = Profile.uid
            val clipboardManager = requireActivity().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clipData = ClipData.newPlainText("text", textToCopy)
            clipboardManager.setPrimaryClip(clipData)
            Toast.makeText(requireContext(), "Referral code copied", Toast.LENGTH_LONG).show()
        }

        button_show_family.setOnClickListener {
            val action = InfoFragmentDirections.actionInfoFragmentToFamilyFragment()
            findNavController().navigate(action)
        }

        button_show_black_list.setOnClickListener {
            val action = InfoFragmentDirections.actionInfoFragmentToBlackListFragment()
            findNavController().navigate(action)
        }

        button_exit.setOnClickListener {
            Profile.logout(requireContext(), getString(R.string.default_web_client_id))

            val intent = Intent(requireActivity(), WelcomeActivity::class.java)
            startActivity(intent)

            requireActivity().finish()
        }

    }

}