package com.sng.finalproject2.manager

import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage


class FbManager {
    private fun setup() {
        // [START get_firestore_instance]
        val storage = Firebase.storage("https://mpd-final.firebaseio.com/")
        val storageRef: StorageReference = storage.reference
    }
}