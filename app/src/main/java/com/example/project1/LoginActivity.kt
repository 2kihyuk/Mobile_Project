package com.example.project1

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.project1.databinding.ActivityLoginBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    private lateinit var binding: ActivityLoginBinding






    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth
//        val idtext =  binding.IdText.text.toString().trim()
//        val pwdtext = binding.Passwordtext.text.toString().trim()
        val loginbtn = binding.LoginBtn
        val signupbtn = binding.SIgnupBtn
        val db = Firebase.firestore


        signupbtn.setOnClickListener {  //회원가입 버튼 클릭시.
            val idtext =  binding.IdText.text.toString().trim()
            val pwdtext = binding.Passwordtext.text.toString().trim()

            auth.createUserWithEmailAndPassword(idtext, pwdtext) //새로
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail:success")
                        val user = hashMapOf(
                            "email_id" to idtext,
                            "password" to pwdtext
                            // 추가적인 사용자 정보 필드들도 저장 가능
                        )
                        db.collection("users")
                            .add(user)
                            .addOnSuccessListener {  documentReference ->
                                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                            }
                            .addOnFailureListener { e ->
                                Log.w(TAG, "Error adding document", e)
                            }
                        Log.d(TAG,"회원 가입 성공")
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        Toast.makeText(
                            baseContext,
                            "Authentication failed.",
                            Toast.LENGTH_SHORT,
                        ).show()
//                        updateUI(null)
                    }
                }
        }


        loginbtn.setOnClickListener {
            val idtext =  binding.IdText.text.toString().trim()
            val pwdtext = binding.Passwordtext.text.toString().trim()
            auth.signInWithEmailAndPassword(idtext, pwdtext)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithEmail:success")
                        val user = auth.currentUser
//                        updateUI(user)
                        val intent = Intent(this,MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithEmail:failure", task.exception)
                        Toast.makeText(
                            baseContext,
                            "Authentication failed.",
                            Toast.LENGTH_SHORT,
                        ).show()
//                        updateUI(null)
                    }
                }
        }


    }


}