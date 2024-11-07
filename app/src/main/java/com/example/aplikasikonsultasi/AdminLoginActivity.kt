package com.example.aplikasikonsultasi

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth

class AdminLoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_admin_login)

        auth = FirebaseAuth.getInstance()

        val btnLogin: Button = findViewById(R.id.btnLoginAdmin)
        btnLogin.setOnClickListener {
            val admin = findViewById<EditText>(R.id.etAdminId).text.toString()
            val password = findViewById<EditText>(R.id.etAdminPassword).text.toString()

            // Validasi email dan password
            auth.signInWithEmailAndPassword("$admin@gmail.com", password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Login sukses
                        val userNim = "Admin"
                        startActivity(Intent(this, BerandaAdmin::class.java))
                        Toast.makeText(this, "Welcome, Admin!", Toast.LENGTH_SHORT).show()
                        intent.putExtra("user_nim", userNim)
                        intent.putExtra("user_nama", "Nama Admin")
                    } else {
                        // Tampilkan error
                        Toast.makeText(this, "Login gagal", Toast.LENGTH_SHORT).show()
                    }
                }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
