package com.example.chatapps.Activities

import com.example.chatapps.Models.User
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.chatapps.R
import com.google.firebase.FirebaseApp
import com.google.firebase.database.FirebaseDatabase

class SignUpActivity : AppCompatActivity() {

    private lateinit var toSignInTextView: TextView
    private lateinit var registerNameEditText: EditText
    private lateinit var registerEmailEditText: EditText
    private lateinit var registerPasswordEditText: EditText
    private lateinit var registerConfirmPasswordEditText: EditText
    private lateinit var registerButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        // Inisialisasi komponen-komponen
        toSignInTextView = findViewById(R.id.toSignIn)
        registerNameEditText = findViewById(R.id.RegisterName)
        registerEmailEditText = findViewById(R.id.RegisterEmail)
        registerPasswordEditText = findViewById(R.id.RegisterPassword)
        registerConfirmPasswordEditText = findViewById(R.id.RegisterConfirmPassword)
        registerButton = findViewById(R.id.registerButton)
        // Inisialisasi Firebase
        FirebaseApp.initializeApp(this)


        // Mengatur aksi klik pada TextView "have an account? Sign In"
        toSignInTextView.setOnClickListener {
            // Tambahkan kode untuk pindah ke activity Sign In di sini
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }


        registerButton.setOnClickListener {
            val name = registerNameEditText.text.toString()
            val email = registerEmailEditText.text.toString()
            val password = registerPasswordEditText.text.toString()
            val confirm_password = registerConfirmPasswordEditText.text.toString()

//            Log.d("SignInActivity", "Name: $name")
//            Log.d("SignInActivity", "Email: $email")
//            Log.d("SignInActivity", "Password: $password")
//            Log.d("SignInActivity", "Confirm Password: $confirm_password")

            // Membuat objek data pengguna
            val user = User(name, email, password)

//            Log.d("UserDebug", "Name: ${user.name}")
//            Log.d("UserDebug", "Email: ${user.email}")
//            Log.d("UserDebug", "Password: ${user.password}")

            // Mendapatkan referensi ke Realtime Database
            val databaseRef = FirebaseDatabase.getInstance().reference
//            if (databaseRef != null) {
//                Log.d("DatabaseDebug", "Connection to Realtime Database established")
//            } else {
//                Log.d("DatabaseDebug", "Connection to Realtime Database failed")
//            }

            databaseRef.child("users").push().setValue(user)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Data berhasil terkirim
                        Log.e("SignUpActivity", "Data berhasil terkirim")
                        Toast.makeText(this, "Data berhasil terkirim", Toast.LENGTH_SHORT).show()

                        // Pindah ke activity SignInActivity
                        val intent = Intent(this, UsersActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                    else {
                        // Terjadi kesalahan, data gagal terkirim
                        Log.e("SignUpActivity", "Gagal mengirim data: ${task.exception}")
                        Toast.makeText(this, "Terjadi kesalahan, data gagal terkirim", Toast.LENGTH_SHORT).show()
                    }
                }
        }

    }

}