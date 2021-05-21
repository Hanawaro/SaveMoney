package com.speechpeach.savemoney.ui.content.fragments.info.referralCodeDialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.speechpeach.savemoney.R
import com.speechpeach.savemoney.database.User
import com.speechpeach.savemoney.profile.Profile
import kotlinx.android.synthetic.main.dialog_referral_code.*

class ReferralCodeDialog : DialogFragment() {

    var referralCode: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setStyle(STYLE_NO_TITLE, android.R.style.Theme_Material_Light_Dialog_NoActionBar_MinWidth);
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_referral_code, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dialog_referral_code.setText(referralCode)
        dialog_accept_referral_code.setOnClickListener {

            Profile.setReferral( dialog_referral_code.text.toString(),
            {
                Profile.referralCode
                if (context != null) {
                    Toast.makeText(requireContext(), "All is right", Toast.LENGTH_SHORT).show()
                    dismiss()
                }
            }, {
                if (context != null)
                    Toast.makeText(requireContext(), "failed", Toast.LENGTH_SHORT).show()
            })
        }

        dialog_clear_referral_code.setOnClickListener {
            User.get(Profile.uid) {
                if (!it.isEmpty) {
                    Profile.user.referralCode = ""
                    User.update(Profile.user, it.documents[0].reference)

                    Toast.makeText(requireContext(), "All is right", Toast.LENGTH_SHORT).show()
                    dismiss()
                }
            }
        }
    }

}