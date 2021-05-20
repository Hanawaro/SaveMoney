package com.speechpeach.savemoney.database

import android.os.Parcelable
import android.util.Log
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.speechpeach.savemoney.profile.Profile
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val uid: String = "",
    val email: String = "",
    val name: String = "",

    var referralCode: String = "",
    val blocked: ArrayList<String> = ArrayList()
) : Parcelable {

    companion object {
        const val collection = "users"

        fun get(uid: String, callback: (QuerySnapshot) -> Unit) {
            val firestore = FirebaseFirestore.getInstance()
            firestore.collection(collection)
                .whereEqualTo("uid", uid)
                .get()
                .addOnSuccessListener {
                    callback(it)
                }
        }

        fun update(user: User, ref: DocumentReference) {
            ref.set(user)
        }

        fun getAll(uid: String, callback: (QuerySnapshot) -> Unit) {
            val firestore = FirebaseFirestore.getInstance()
            firestore.collection(collection)
                .whereEqualTo("referralCode", uid)
                .get()
                .addOnSuccessListener {
                    callback(it)
                }
        }

        fun onChange(uid: String, callback: (QuerySnapshot) -> Unit) {
            val firestore = FirebaseFirestore.getInstance()
            firestore.collection(collection)
                .whereEqualTo("referralCode", uid)
                .addSnapshotListener { value, _ ->
                    if (value != null)
                        callback(value)
                }
        }
    }

}