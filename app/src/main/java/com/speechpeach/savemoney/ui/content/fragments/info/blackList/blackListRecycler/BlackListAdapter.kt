package com.speechpeach.savemoney.ui.content.fragments.info.blackList.blackListRecycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.speechpeach.savemoney.R
import com.speechpeach.savemoney.database.User
import com.speechpeach.savemoney.profile.Profile
import com.speechpeach.savemoney.ui.content.fragments.info.blackList.BlackListViewModel
import com.speechpeach.savemoney.ui.content.fragments.info.family.FamilyViewModel
import kotlinx.android.synthetic.main.item_black_list.view.*
import kotlinx.android.synthetic.main.item_family.view.*

class BlackListAdapter(private val viewModel: BlackListViewModel) : ListAdapter<User, BlackListAdapter.BlackListViewHolder>(DiffCallback()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlackListViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_black_list, parent, false)
        return BlackListViewHolder(itemView, viewModel)
    }

    override fun onBindViewHolder(holder: BlackListViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    class BlackListViewHolder(itemView: View, private val viewModel: BlackListViewModel) : RecyclerView.ViewHolder(itemView) {
        fun bind(user: User) {
            itemView.apply {
                item_black_list_name.text = user.name

                item_black_list_remove.setOnClickListener {

                    viewModel.blackList.value?.remove(user)
                    viewModel.blackList.postValue(viewModel.blackList.value)

                    User.get(Profile.uid) {
                        if (!it.isEmpty) {
                            Profile.user.blocked.remove(user.uid)
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