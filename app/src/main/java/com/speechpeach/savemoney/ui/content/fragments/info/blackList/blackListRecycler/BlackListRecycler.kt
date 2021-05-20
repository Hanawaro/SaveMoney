package com.speechpeach.savemoney.ui.content.fragments.info.blackList.blackListRecycler

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.speechpeach.savemoney.database.User
import com.speechpeach.savemoney.profile.Profile
import com.speechpeach.savemoney.ui.content.fragments.info.blackList.BlackListViewModel

class BlackListRecycler(context: Context, recyclerView: RecyclerView, viewModel: BlackListViewModel) {

    private val blackListAdapter = BlackListAdapter(viewModel)

    init {
        recyclerView.apply {
            adapter = blackListAdapter
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }
    }

    fun submitList(list: List<User>) {
        blackListAdapter.submitList(list)
        blackListAdapter.notifyDataSetChanged()
    }

}