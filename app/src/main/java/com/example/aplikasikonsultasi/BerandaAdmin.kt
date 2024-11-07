    package com.example.aplikasikonsultasi

    import android.content.Intent
    import android.os.Bundle
    import android.util.Log
    import android.widget.Button
    import androidx.activity.enableEdgeToEdge
    import androidx.appcompat.app.AppCompatActivity
    import androidx.core.view.ViewCompat
    import androidx.core.view.WindowInsetsCompat

    class BerandaAdmin : AppCompatActivity() {

        private lateinit var btnDataMahasiswa: Button
        private lateinit var btnListKonsultasi: Button



        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            enableEdgeToEdge()
            setContentView(R.layout.activity_beranda_admin)
            btnDataMahasiswa = findViewById(R.id.btnDataMahasiswa)


            val userNim = intent.getStringExtra("user_nim")
            val userNama = intent.getStringExtra("user_nama")


            Log.d("BerandaAdmin", "UserNim: $userNim, UserNama: $userNama")


            btnDataMahasiswa.setOnClickListener {
                val intent = Intent(this, DataMahasiswa::class.java)
                startActivity(intent)
                intent.putExtra("sender_id", "Admin")
            }


            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
        }
    }