package com.example.chatapps.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.example.chatapps.R
import com.google.firebase.database.FirebaseDatabase

class SignInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        // Mendapatkan referensi ke ImageView
        val logoImageView = findViewById<ImageView>(R.id.Logo)
        // Mendapatkan referensi ke EditText
        val loginEmailEditText = findViewById<EditText>(R.id.LoginEmail)
        val loginPasswordEditText = findViewById<EditText>(R.id.LoginPassword)
        // Mendapatkan referensi ke TextView
        val toSignUpTextView = findViewById<TextView>(R.id.toSignUp)
        // Mendapatkan referensi ke Button
        val loginButton = findViewById<Button>(R.id.loginButton)
        // mendapatkan referensi ke Realtime Database
        val database = FirebaseDatabase.getInstance()
        val databaseRef = database.reference



        // Mengatur aksi klik pada TextView
        toSignUpTextView.setOnClickListener {
            // Aksi yang ingin Anda lakukan saat TextView diklik
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        loginButton.setOnClickListener {
            val email = loginEmailEditText.text.toString()
            val password = loginPasswordEditText.text.toString()

//            Log.d("SignInActivity", "Email: $email")
//            Log.d("SignInActivity", "Password: $password")
            val intent = Intent(this, UsersActivity::class.java)
            startActivity(intent)
        }
    }
}