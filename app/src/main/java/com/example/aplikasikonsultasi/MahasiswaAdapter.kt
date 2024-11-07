package com.example.aplikasikonsultasi

import androidx.recyclerview.widget.RecyclerView
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore



class MahasiswaAdapter(
    private val mahasiswaList: ArrayList<Mahasiswa>,
    private val context: Context
) : RecyclerView.Adapter<MahasiswaAdapter.MahasiswaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MahasiswaViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_mahasiswa, parent, false)
        return MahasiswaViewHolder(view)
    }

    override fun onBindViewHolder(holder: MahasiswaViewHolder, position: Int) {
        val mahasiswa = mahasiswaList[position]
        holder.bind(mahasiswa)



        holder.btnDelete.setOnClickListener {
            FirebaseFirestore.getInstance().collection("users").document(mahasiswa.Nim)
                .delete()
                .addOnSuccessListener {
                    mahasiswaList.removeAt(position)
                    notifyItemRemoved(position)
                    notifyItemRangeChanged(position, mahasiswaList.size)
                }
                .addOnFailureListener { e ->
                    Toast.makeText(context, "Gagal menghapus data: ${e.message}", Toast.LENGTH_SHORT).show()
                }
        }

        // Handle chat button
        holder.btnChat.setOnClickListener {
            val intent = Intent(context, RoomChatActivity::class.java)
            intent.putExtra("user_nim", mahasiswa.Nim)
            intent.putExtra("user_nama", mahasiswa.Nama)
            intent.putExtra("sender_id", "Admin")
            // Kirim NIM ke RoomChatActivity
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return mahasiswaList.size
    }

    inner class MahasiswaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nama: TextView = itemView.findViewById(R.id.txtNama)
        val nim: TextView = itemView.findViewById(R.id.txtNim)
        val fakultas: TextView = itemView.findViewById(R.id.txtFakultas)
        val prodi: TextView = itemView.findViewById(R.id.txtProdi)
        val email: TextView = itemView.findViewById(R.id.txtEmail)
        val noHp: TextView = itemView.findViewById(R.id.txtNoHp)
        val btnDelete: Button = itemView.findViewById(R.id.btnDelete)
        val btnChat: Button = itemView.findViewById(R.id.btnChat)

        fun bind(mahasiswa: Mahasiswa) {
            nama.text = mahasiswa.Nama
            nim.text = mahasiswa.Nim
            fakultas.text = mahasiswa.Fakultas
            prodi.text = mahasiswa.Prodi
            email.text = mahasiswa.Email
            noHp.text = mahasiswa.No_Hp
        }
    }
}