package com.speechpeach.savemoney.ui.content

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.speechpeach.savemoney.R
import com.speechpeach.savemoney.database.User
import com.speechpeach.savemoney.profile.Profile
import com.speechpeach.savemoney.ui.content.dialog.InfoDialog
import kotlinx.android.synthetic.main.activity_content.*

class ContentActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    private var isNavBottomMenu = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_graph_content_fragment) as NavHostFragment
        navController = navHostFragment.findNavController().apply {
            addOnDestinationChangedListener { _, destination, _ ->
                isNavBottomMenu = when (destination.id) {
                    R.id.statsFragment, R.id.infoFragment, R.id.purchaseFragment -> true
                    else -> false
                }
            }
        }

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.purchaseFragment,
                R.id.statsFragment,
                R.id.infoFragment
            )
        )

        setupActionBarWithNavController(navController, appBarConfiguration)

        bottom_nav.setupWithNavController(navController)

        Profile.onChange {
            if (!it.isEmpty) {

                val user = it.documents[0].toObject(User::class.java)
                if (Profile.referralCode != user!!.referralCode && user.referralCode.isEmpty()) {
                    Profile.user.referralCode = user.referralCode

                    InfoDialog().show(supportFragmentManager, "info")
                    navController.navigate(R.id.infoFragment)
                }
            }
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed() {
        if (!isNavBottomMenu)
            super.onBackPressed()
        else
            finish()
    }
}