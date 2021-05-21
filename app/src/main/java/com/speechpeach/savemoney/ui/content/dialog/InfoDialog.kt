package com.speechpeach.savemoney.ui.content.dialog

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

class InfoDialog : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setStyle(STYLE_NO_TITLE, android.R.style.Theme_Material_Light_Dialog_NoActionBar_MinWidth);
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_disconnect, container)
    }

}