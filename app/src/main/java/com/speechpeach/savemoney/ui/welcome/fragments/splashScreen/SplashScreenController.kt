package com.speechpeach.savemoney.ui.welcome.fragments.splashScreen

import com.google.firebase.firestore.FirebaseFirestore
import com.speechpeach.savemoney.database.FirebaseModule
import com.speechpeach.savemoney.database.User

class SplashScreenController {

    private lateinit var firebaseFirestore: FirebaseFirestore

    var successCallback: ((forTheFirstTime: Boolean, referralCode: String, blocked: ArrayList<String>) -> Unit) = { _, _, _-> }
    var failedCallback: (() -> Unit) = {}

    fun init() {
        firebaseFirestore = FirebaseFirestore.getInstance()

        if (FirebaseModule.firebaseAuth.currentUser != null) {
            firebaseFirestore.collection(User.collection)
                    .whereEqualTo("email", FirebaseModule.firebaseAuth.currentUser!!.email)
                    .get()
                    .addOnSuccessListener {
                        if (!it.isEmpty) {
                            successCallback(false,
                                it.toObjects(User::class.java).first().referralCode,
                                it.toObjects(User::class.java).first().blocked
                            )
                        } else {
                            successCallback(false,
                                "",
                                ArrayList()
                            )
                        }

                    }
        } else {
            failedCallback()
        }
    }

}