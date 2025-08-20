package com.example.firebaseauthentication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
// import androidx.compose.ui.semantics.text // Removed this unused import
import com.google.firebase.auth.FirebaseAuth

class ActivityLogin : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()
        emailEditText = findViewById(R.id.email2)
        passwordEditText = findViewById(R.id.pswd2)
        loginButton = findViewById(R.id.login)

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString().trim() // Added trim()
            val password = passwordEditText.text.toString() // Passwords can have spaces

            if (email.isNotEmpty() && password.isNotEmpty()) {
                loginUser(email, password) // Call your function here
            } else {
                Toast.makeText(baseContext, "Please enter email and password.",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loginUser(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task -> // 'this' refers to ActivityLogin
                if (task.isSuccessful) {
                    Toast.makeText(this, "Authentication successful.", // Changed Toast message for consistency
                        Toast.LENGTH_SHORT).show()
                    // TODO: Navigate to the next screen or update UI
                } else {
                    Toast.makeText(this, "Authentication failed: ${task.exception?.message}",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }
}
