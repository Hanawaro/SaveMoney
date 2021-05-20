package com.speechpeach.savemoney.database

import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import javax.security.auth.callback.Callback

data class Purchase(
    val uid: String,
    var name: String,
    var category: String,
    var price: Int
) {
    enum class Category(val categoryName: String) {
        FOOD("food"), OTHER("other")
    }

    companion object {

        private const val collection = "purchase"

        fun getAll(uid: String, callback: (QuerySnapshot) -> Unit) {
            val firestore = FirebaseFirestore.getInstance()
            firestore.collection(collection)
                .whereEqualTo("uid", uid)
                .get()
                .addOnSuccessListener {
                    callback(it)
                }
        }

        fun update(purchase: Purchase, ref: DocumentReference) {
            ref.set(purchase)
        }

        fun add(purchase: Purchase) {
            val firestore = FirebaseFirestore.getInstance()
            firestore.collection(collection).add(purchase)
        }

        fun onChange(uid: String, callback: (QuerySnapshot) -> Unit) {
            val firestore = FirebaseFirestore.getInstance()
            firestore.collection(collection)
                .whereEqualTo("uid", uid)
                .addSnapshotListener { value, _ ->
                    if (value != null)
                        callback(value)
                }
        }
    }
}
