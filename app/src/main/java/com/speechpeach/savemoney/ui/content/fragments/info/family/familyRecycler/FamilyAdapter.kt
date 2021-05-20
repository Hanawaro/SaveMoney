package com.speechpeach.savemoney.ui.content.fragments.info.family.familyRecycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.speechpeach.savemoney.R
import com.speechpeach.savemoney.database.User
import com.speechpeach.savemoney.profile.Profile
import com.speechpeach.savemoney.ui.content.fragments.info.family.FamilyViewModel
import kotlinx.android.synthetic.main.item_family.view.*

class FamilyAdapter(private val viewModel: FamilyViewModel) : ListAdapter<User, FamilyAdapter.FamilyViewHolder>(DiffCallback()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FamilyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_family, parent, false)
        return FamilyViewHolder(itemView, viewModel)
    }

    override fun onBindViewHolder(holder: FamilyViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    class FamilyViewHolder(itemView: View, private val viewModel: FamilyViewModel) : RecyclerView.ViewHolder(itemView) {
        fun bind(user: User) {
            itemView.apply {
                item_family_name.text = user.name
                item_family_remove.setOnClickListener {

                    viewModel.family.value?.remove(user)
                    viewModel.family.postValue(viewModel.family.value)

                    User.get(user.uid) {
                        if (!it.isEmpty) {
                            user.referralCode = ""
                            User.update(user, it.documents[0].reference)
                        }
                    }
                }
                item_family_to_black_list.setOnClickListener {

                    viewModel.family.value?.remove(user)
                    viewModel.family.postValue(viewModel.family.value)

                    User.get(Profile.uid) {
                        if (!it.isEmpty) {
                            Profile.user.blocked.add(user.uid)
                            User.update(Profile.user, it.documents[0].reference)
                        }
                    }
                }
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean =
            oldItem.uid == newItem.uid

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean =
            oldItem == newItem
    }
}