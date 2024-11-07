package com.example.aplikasikonsultasi

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val btnTesPsikologi: Button = findViewById(R.id.btnTesPsikologi)
        val btnArtikel: Button = findViewById(R.id.btnArtikel)
        val btnLogin: Button = findViewById(R.id.btnLogin)
        val btnAdminLogin: Button = findViewById(R.id.btnAdminLogin)

        btnTesPsikologi.setOnClickListener {
            startActivity(Intent(this, TesPsikologiActivity::class.java))
        }

        btnArtikel.setOnClickListener {
            startActivity(Intent(this, ArtikelActivity::class.java))
        }

        btnLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        btnAdminLogin.setOnClickListener {
            startActivity(Intent(this, AdminLoginActivity::class.java))
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}