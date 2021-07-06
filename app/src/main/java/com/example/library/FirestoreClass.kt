package com.example.library

import android.app.Activity
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

class FirestoreClass {
    private  val mfirestore = FirebaseFirestore.getInstance()
    val currentuserid = FirebaseAuth.getInstance().currentUser!!.uid
    fun registeruser(activity: RegisterActivity,userInfo: User){
        mfirestore.collection("users").document(currentuserid).set(userInfo, SetOptions.merge())
    }

    fun updateUserProfile(activity: Activity, userHashMap: HashMap<String, Any>){
        mfirestore.collection("users").document(currentuserid).update(userHashMap).addOnSuccessListener {

        }.addOnFailureListener { e ->
            Log.e(activity.javaClass.simpleName,"error",e)
        }

    }
}