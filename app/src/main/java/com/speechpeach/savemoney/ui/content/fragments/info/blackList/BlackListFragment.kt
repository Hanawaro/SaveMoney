package com.speechpeach.savemoney.ui.content.fragments.info.blackList

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.speechpeach.savemoney.R
import com.speechpeach.savemoney.database.User
import com.speechpeach.savemoney.profile.Profile
import com.speechpeach.savemoney.ui.content.fragments.info.blackList.blackListRecycler.BlackListRecycler
import com.speechpeach.savemoney.ui.content.fragments.info.family.FamilyViewModel
import com.speechpeach.savemoney.ui.content.fragments.info.family.familyRecycler.FamilyRecycler
import kotlinx.android.synthetic.main.fragment_black_list.*
import kotlinx.android.synthetic.main.fragment_family.*

class BlackListFragment : Fragment(R.layout.fragment_black_list) {

    private lateinit var recyclerView: BlackListRecycler

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(BlackListViewModel::class.java)

        recyclerView = BlackListRecycler(requireContext(), recycler_view_black_list, viewModel)

        User.getAll(Profile.uid) {
            viewModel.blackList.value = ArrayList(it.toObjects(User::class.java))
        }

        User.onChange(Profile.uid) {
            viewModel.blackList.value = ArrayList(it.toObjects(User::class.java))
        }

        viewModel.blackList.observe(viewLifecycleOwner, {
            for (value in it) {
                if (Profile.user.blocked.stream().noneMatch(){ blocked -> value.uid == blocked }) {
                    it.remove(value)
                }
            }
            recyclerView.submitList(it)
        })

    }

}