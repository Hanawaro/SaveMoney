package com.speechpeach.savemoney.ui.content.fragments.stats

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.faskn.lib.Slice
import com.faskn.lib.buildChart
import com.speechpeach.savemoney.R
import com.speechpeach.savemoney.database.Purchase
import com.speechpeach.savemoney.database.User
import com.speechpeach.savemoney.profile.Profile
import com.speechpeach.savemoney.ui.welcome.WelcomeActivity
import kotlinx.android.synthetic.main.fragment_stats.*
import java.util.logging.Logger
import kotlin.random.Random

class StatsFragment : Fragment(R.layout.fragment_stats) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        chart_manager.setPieChart(buildChart {
            slices { arrayListOf(
                Slice(
                    1000f,
                    R.color.free,
                    "Free"
                )
            ) }
            sliceWidth { 80f }
            sliceStartPoint { 0f }
        })

        Purchase.getAll(if (Profile.referralCode.isEmpty()) Profile.uid else Profile.referralCode) {

            if (!it.isEmpty) {

                val dataPoints = ArrayList<Float>().apply {
                    add(0f)
                    add(0f)
                    add(0f)
                    add(0f)
                }

                for (value in it.toObjects(Purchase::class.java)) {
                    when(value.category) {
                        Purchase.Category.FOOD.categoryName -> dataPoints[0] = dataPoints[0] + value.price
                        Purchase.Category.ENTERTAINMENT.categoryName -> dataPoints[1] = dataPoints[1] + value.price
                        Purchase.Category.HOUSE.categoryName -> dataPoints[2] = dataPoints[2] + value.price
                        Purchase.Category.OTHER.categoryName -> dataPoints[3] = dataPoints[3] + value.price
                    }
                }

                val list = ArrayList<Slice>()
                if (!dataPoints.stream().allMatch { value -> value != 0f }) {
                    list.apply {
                        add(Slice(dataPoints[0], R.color.food, Purchase.Category.FOOD.categoryName))
                        add(Slice(dataPoints[1], R.color.entertainment, Purchase.Category.ENTERTAINMENT.categoryName))
                        add(Slice(dataPoints[2], R.color.house, Purchase.Category.HOUSE.categoryName))
                        add(Slice(dataPoints[3], R.color.other, Purchase.Category.OTHER.categoryName))
                    }
                } else {
                    list.apply {
                        add(Slice(1000f, R.color.free, "Free"))
                    }
                }

                text_view_stats.text = "Ваш баланс: ${list.sumBy { it.dataPoint.toInt() }}Р"

                chart_manager.setPieChart(buildChart {
                    slices { list }
                    sliceWidth { 80f }
                    sliceStartPoint { 0f }
                })
            }
        }
    }

}