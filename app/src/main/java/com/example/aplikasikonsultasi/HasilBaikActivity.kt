package com.example.aplikasikonsultasi

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HasilBaikActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hasil_baik) // Pindahkan setContentView di awal

        enableEdgeToEdge() // Enable edge to edge setelah setContentView

        val nilaiYa = intent.getIntExtra("NILAI_YA", 0) // Ambil nilai dari intent
        val txtHasil = findViewById<TextView>(R.id.txtHasilBaik)
        val btnKembali = findViewById<Button>(R.id.btnKembaliBerandaBaik)

        // Set text sesuai dengan nilai jawaban "Ya"
        txtHasil.text = "Nilai kamu adalah $nilaiYa, kamu baik-baik saja kok tetap semangat yaaa!!"

        // Action button untuk kembali ke beranda
        btnKembali.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
                startActivity( intent)// Menutup activity ini untuk kembali ke beranda
            }

        // Mengatur insets untuk view utama
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
