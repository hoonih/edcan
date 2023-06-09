package com.example.edcan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.edcan.databinding.ActivityMainBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var db: FirebaseFirestore



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        db = FirebaseFirestore.getInstance()

        binding.btn.setOnClickListener {
            val id = binding.edittext1.text.toString()
            val phoneNum = binding.edittext2.text.toString()
            val schoolName = binding.edittext3.text.toString().trim()

            register(id, phoneNum, schoolName)
        }
    }

    fun register(id: String, phoneNum: String, schoolName: String) {
        val user = hashMapOf<String, String>(
            "id" to id,
            "phoneNum" to phoneNum
        )

        val docId =
            if (schoolName=="sunrin") "sunin"
            else "other"

        db.collection("user").document(docId)
            .set(user)
            .addOnSuccessListener {
                Log.d("fb", "데이터 넣기 성공!")
            }
            .addOnFailureListener { error->
                Log.d("fb", "데이터 넣기 실패! ${error.message}")
            }


    }
}
