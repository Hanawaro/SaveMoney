package com.speechpeach.savemoney.ui.content.fragments.manager

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.faskn.lib.Slice
import com.faskn.lib.buildChart
import com.speechpeach.savemoney.R
import com.speechpeach.savemoney.database.Purchase
import kotlinx.android.synthetic.main.fragment_manager.*
import kotlin.random.Random

class ManagerFragment : Fragment(R.layout.fragment_manager) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button_manager.setOnClickListener {
//            for(i in 1..3) {
//                Purchase.add(Purchase("uid", "name $i", Purchase.Category.FOOD.categoryName, 100 * i))
//            }
        }

    }

}