package com.speechpeach.savemoney.ui.content

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.speechpeach.savemoney.R
import kotlinx.android.synthetic.main.activity_content.*

class ContentActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    private var isNavBottomMenu = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_graph_content_fragment) as NavHostFragment
        navController = navHostFragment.findNavController().apply {
            addOnDestinationChangedListener { _, destination, _ ->
                isNavBottomMenu = when (destination.id) {
                    R.id.managerFragment, R.id.statsFragment, R.id.infoFragment -> true
                    else -> false
                }
            }
        }

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.managerFragment,
                R.id.statsFragment,
                R.id.infoFragment
            )
        )

        setupActionBarWithNavController(navController, appBarConfiguration)

        bottom_nav.setupWithNavController(navController)

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