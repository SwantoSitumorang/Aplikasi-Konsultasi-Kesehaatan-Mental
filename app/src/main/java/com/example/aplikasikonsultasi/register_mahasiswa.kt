package com.example.aplikasikonsultasi

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore

class register_mahasiswa : AppCompatActivity() {
    private lateinit var nameInput: EditText
    private lateinit var nimInput: EditText
    private lateinit var emailInput: EditText
    private lateinit var fakultasInput: EditText
    private lateinit var prodiInput: EditText
    private lateinit var noHpInput: EditText
    private lateinit var registerButton: Button
    private val firestore = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register_mahasiswa)

        // Inisialisasi EditText dan Button
        nameInput = findViewById(R.id.editTextName)
        nimInput = findViewById(R.id.editTextNIM)
        emailInput = findViewById(R.id.editTextEmail)
        fakultasInput = findViewById(R.id.editTextFakultas)
        prodiInput = findViewById(R.id.editTextProdi)
        noHpInput = findViewById(R.id.editTextNoHp)
        registerButton = findViewById(R.id.buttonRegisterStudent)

        // Set onClickListener untuk button register
        registerButton.setOnClickListener {
            val Nama = nameInput.text.toString()
            val Nim = nimInput.text.toString()
            val Email = emailInput.text.toString()
            val Fakultas = fakultasInput.text.toString()
            val Prodi = prodiInput.text.toString()
            val No_Hp = noHpInput.text.toString()

            // Validasi input yang tidak boleh kosong
            if (Nama.isNotEmpty() && Nim.isNotEmpty() && Email.isNotEmpty()) {
                registerMahasiswa(Nama, Nim, Email, Fakultas, Prodi, No_Hp)
            } else {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Fungsi untuk menambahkan data mahasiswa ke Firestore
    private fun registerMahasiswa(Nama: String, Nim: String, Email: String, Fakultas: String, Prodi: String, No_Hp: String) {
        // Data mahasiswa yang akan disimpan
        val DataMahasiswa = mapOf(
            "role" to "mhs",
            "Nim" to Nim,
            "Nama" to Nama,
            "Email" to Email,
            "Fakultas" to Fakultas,
            "Prodi" to Prodi,
            "No_Hp" to No_Hp
        )

        // Menyimpan data ke Firestore dengan NIM sebagai nama dokumen
        firestore.collection("users").document(Nim).set(DataMahasiswa)
            .addOnSuccessListener {
                Toast.makeText(this, "Mahasiswa berhasil ditambahkan", Toast.LENGTH_SHORT).show()
                // Membersihkan input setelah data berhasil disimpan
                clearInputFields()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Gagal menambahkan mahasiswa: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    // Fungsi untuk membersihkan input fields setelah berhasil disimpan
    private fun clearInputFields() {
        nameInput.text.clear()
        nimInput.text.clear()
        emailInput.text.clear()
        fakultasInput.text.clear()
        prodiInput.text.clear()
        noHpInput.text.clear()
    }
}
