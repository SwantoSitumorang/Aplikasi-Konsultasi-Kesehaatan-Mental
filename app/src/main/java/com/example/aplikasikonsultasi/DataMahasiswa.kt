package com.example.aplikasikonsultasi

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject

class DataMahasiswa : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var btnTambahMahasiswa: Button
    private lateinit var mahasiswaAdapter: MahasiswaAdapter
    private lateinit var mahasiswaList: ArrayList<Mahasiswa>
    private val db = FirebaseFirestore.getInstance()
    private var senderId: String? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_data_mahasiswa)

        senderId = intent.getStringExtra("sender_id")
        recyclerView = findViewById(R.id.recyclerViewDataMahasiswa)
        btnTambahMahasiswa = findViewById(R.id.btnTambahMahasiswa)

        mahasiswaList = ArrayList()
        mahasiswaAdapter = MahasiswaAdapter(mahasiswaList, this)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = mahasiswaAdapter

        // Mengambil data mahasiswa dari Firestore
        fetchMahasiswaData()

        btnTambahMahasiswa.setOnClickListener {
            val intent = Intent(this, register_mahasiswa::class.java)
            startActivity(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun fetchMahasiswaData() {
        db.collection("users")
            .get()
            .addOnSuccessListener { documents ->
                mahasiswaList.clear()
                for (document in documents) {
                    val mahasiswa = document.toObject(Mahasiswa::class.java)
                    mahasiswaList.add(mahasiswa)
                }
                mahasiswaAdapter.notifyDataSetChanged()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Gagal memuat data: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }
}
