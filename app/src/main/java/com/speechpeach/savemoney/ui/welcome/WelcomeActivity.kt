package com.speechpeach.savemoney.ui.welcome

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.speechpeach.savemoney.R

class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        supportActionBar?.hide()
    }
}