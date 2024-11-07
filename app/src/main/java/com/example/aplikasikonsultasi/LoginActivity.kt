package com.example.aplikasikonsultasi

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.firestore.FirebaseFirestore

class LoginActivity : AppCompatActivity() {

    private lateinit var nimInput: EditText
    private lateinit var namaInput: EditText
    private lateinit var loginButton: Button
    private val firestore = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        nimInput = findViewById(R.id.editTextNIM)
        namaInput = findViewById(R.id.editTextNama)
        loginButton = findViewById(R.id.buttonLogin)

        loginButton.setOnClickListener {
            val nim = nimInput.text.toString().trim()
            val nama = namaInput.text.toString().trim()

            if (nim.isNotEmpty() && nama.isNotEmpty()) {
                loginMahasiswa(nim, nama)
            } else {
                Toast.makeText(this, "Silakan masukkan NIM dan Nama", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loginMahasiswa(nim: String, nama: String) {
        firestore.collection("users").document(nim).get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    // Login berhasil
                    Toast.makeText(this, "Login berhasil!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, RoomChatActivity::class.java)
                    intent.putExtra("user_nim", nim)
                    intent.putExtra("user_nama", nama)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "Data tidak ditemukan!", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Terjadi kesalahan: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }
}
