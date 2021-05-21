package com.speechpeach.savemoney.ui.content.fragments.stats

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.faskn.lib.Slice
import com.faskn.lib.buildChart
import com.speechpeach.savemoney.R
import com.speechpeach.savemoney.ui.welcome.WelcomeActivity
import kotlinx.android.synthetic.main.fragment_stats.*
import kotlin.random.Random

class StatsFragment : Fragment(R.layout.fragment_stats) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        chart_manager.setPieChart(buildChart {
            slices { arrayListOf(
                Slice(
                    Random.nextInt(1000, 3000).toFloat(),
                    R.color.red,
                    "Google"
                ),
                Slice(
                    Random.nextInt(1000, 2000).toFloat(),
                    R.color.green,
                    "Facebook"
                ),
                Slice(
                    Random.nextInt(1000, 5000).toFloat(),
                    R.color.black,
                    "Twitter"
                ),
                Slice(
                    Random.nextInt(1000, 10000).toFloat(),
                    R.color.semiGray,
                    "Other"
                )
            ) }
            sliceWidth { 80f }
        })

        button_stats.setOnClickListener {
            val intent = Intent(requireActivity(), WelcomeActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }
    }

}