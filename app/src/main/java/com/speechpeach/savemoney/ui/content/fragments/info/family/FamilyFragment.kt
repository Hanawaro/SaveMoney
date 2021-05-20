package com.speechpeach.savemoney.ui.content.fragments.info.family

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.speechpeach.savemoney.R
import com.speechpeach.savemoney.database.User
import com.speechpeach.savemoney.profile.Profile
import com.speechpeach.savemoney.ui.content.fragments.info.family.familyRecycler.FamilyRecycler
import kotlinx.android.synthetic.main.fragment_family.*

class FamilyFragment : Fragment(R.layout.fragment_family) {

    private lateinit var recyclerView: FamilyRecycler

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(FamilyViewModel::class.java)

        recyclerView = FamilyRecycler(requireContext(), recycler_view_family, viewModel)

        User.getAll(Profile.uid) {
            viewModel.family.value = ArrayList(it.toObjects(User::class.java))
        }

        User.onChange(Profile.uid) {
            viewModel.family.value = ArrayList(it.toObjects(User::class.java))
        }

        viewModel.family.observe(viewLifecycleOwner, {
            for (value in it) {
                if (Profile.user.blocked.stream().anyMatch{ blocked -> value.uid == blocked }) {
                    it.remove(value)
                }
            }
            recyclerView.submitList(it)
        })
    }

}