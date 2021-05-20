package com.speechpeach.savemoney.ui.content.fragments.info.blackList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.speechpeach.savemoney.database.User

class BlackListViewModel : ViewModel() {

    val blackList = MutableLiveData<ArrayList<User>>()

}