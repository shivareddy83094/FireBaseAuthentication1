package com.example.firebaseauthentication

import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class ForgotPasswordActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var emailEditText: EditText
    private lateinit var resetButton: Button

    override fun onCreate(savedInstanceState: Bundle?) { // Corrected method name
        super.onCreate(savedInstanceState)
        setContentView(R.layout.avtivity_forgot_password) // Corrected layout name

        auth = FirebaseAuth.getInstance()
        emailEditText = findViewById(R.id.edittext) // Ensure R.id.edittext exists in your layout
        resetButton = findViewById(R.id.button)     // Ensure R.id.button exists in your layout

        resetButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()

            if (email.isEmpty()) {
                emailEditText.error = "Please enter your email"
                emailEditText.requestFocus() // Optionally focus the field
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                emailEditText.error = "Please enter a valid email address"
                emailEditText.requestFocus()
                return@setOnClickListener
            }

            // Show progress indicator (e.g., ProgressBar) here if desired

            auth.sendPasswordResetEmail(email)
                .addOnCompleteListener { task ->
                    // Hide progress indicator here
                    if (task.isSuccessful) {
                        Toast.makeText(
                            this,
                            "Password reset email sent. Please check your inbox.",
                            Toast.LENGTH_LONG
                        ).show()
                        // Optionally, navigate back to login screen or finish this activity
                        // finish()
                    } else {
                        Toast.makeText(
                            this,
                            "Failed to send reset email: ${task.exception?.message}",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
        }
    }
}
