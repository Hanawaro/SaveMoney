package com.speechpeach.savemoney.ui.content.fragments.info.family

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.speechpeach.savemoney.database.User

class FamilyViewModel : ViewModel() {

    val family = MutableLiveData<ArrayList<User>>()

}