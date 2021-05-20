package com.speechpeach.savemoney.ui.content.fragments.info.family.familyRecycler

import android.content.Context
import android.os.Vibrator
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.speechpeach.savemoney.database.User
import com.speechpeach.savemoney.profile.Profile
import com.speechpeach.savemoney.ui.content.fragments.info.family.FamilyViewModel

class FamilyRecycler(context: Context, recyclerView: RecyclerView, viewModel: FamilyViewModel) {

    private val familyAdapter = FamilyAdapter(viewModel)

    init {
        recyclerView.apply {
            adapter = familyAdapter
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }
    }

    fun submitList(list: List<User>) {
        familyAdapter.submitList(list)
        familyAdapter.notifyDataSetChanged()
    }

}