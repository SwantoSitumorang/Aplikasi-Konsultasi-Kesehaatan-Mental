package com.example.aplikasikonsultasi

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class RoomChatActivity : AppCompatActivity() {
    private lateinit var chatAdapter: ChatAdapter
    private lateinit var firestore: FirebaseFirestore
    private var userNim: String? = null
    private var userNama: String? = null
    private var senderId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_room_chat)

        val btnSend: Button = findViewById(R.id.btnSend)
        val editMessage: EditText = findViewById(R.id.editMessage)
        val recyclerViewChat: RecyclerView = findViewById(R.id.recyclerViewChat)

        // Dapatkan NIM dan Nama dari Intent
        userNim = intent.getStringExtra("user_nim")
        userNama = intent.getStringExtra("user_nama")
        senderId = intent.getStringExtra("sender_id")
        Log.d("RoomChatActivity", "SenderId received: $senderId")
        Log.d("RoomChatActivity", "UserNim: $userNim, UserNama: $userNama SenderId: $senderId")

        firestore = FirebaseFirestore.getInstance()

        val txtNamaMahasiswa: TextView = findViewById(R.id.txtNamaMahasiswa)
        val txtNimMahasiswa: TextView = findViewById(R.id.txtNimMahasiswa)
        txtNamaMahasiswa.text = userNama
        txtNimMahasiswa.text = userNim

        setupRecyclerView(recyclerViewChat)

        // Muat pesan chat dari Firestore
        loadMessages()

        // Tombol kirim pesan
        btnSend.setOnClickListener {
            val messageText = editMessage.text.toString()
            if (messageText.isNotEmpty()) {
                sendMessage(messageText, editMessage)
            }
        }
    }

    private fun setupRecyclerView(recyclerViewChat: RecyclerView) {
        chatAdapter = ChatAdapter(ArrayList())
        recyclerViewChat.layoutManager = LinearLayoutManager(this)
        recyclerViewChat.adapter = chatAdapter

    }

    private fun loadMessages() {
        val chatDocument = firestore.collection("chats").document("chat$userNim")

        chatDocument.collection("messages")
            .orderBy("timestamp", Query.Direction.ASCENDING)
            .addSnapshotListener { snapshots, e ->
                if (e != null) {
                    Log.w("RoomChatActivity", "Listen failed.", e)
                    return@addSnapshotListener
                }

                val messages = snapshots?.toObjects(Message::class.java)
                if (messages != null) {
                    Log.d("RoomChatActivity", "Messages: $messages")
                    chatAdapter.updateMessages(messages)
                }
            }
    }

    private fun sendMessage(text: String, editMessage: EditText) {
        val chatDocument = firestore.collection("chats").document("chat$userNim")

        chatDocument.get().addOnSuccessListener { documentSnapshot ->
            if (!documentSnapshot.exists()) {
                chatDocument.set(
                    mapOf(
                        "lastMessage" to text,
                        "participants" to listOf(userNim, "Admin"),
                        "timestamp" to System.currentTimeMillis()
                    )
                )
            } else {
                chatDocument.update("lastMessage", text, "timestamp", System.currentTimeMillis())
            }

            // Tentukan senderId

            val message = hashMapOf(
                "senderId" to (senderId ?: userNim), // Pastikan senderId benar
                "text" to text,
                "timestamp" to System.currentTimeMillis()
            )

            // Tambahkan pesan ke sub-koleksi "messages"
            chatDocument.collection("messages").add(message)

                .addOnSuccessListener {
                    Log.d("RoomChatActivity", "Message sent successfully.")
                }
                .addOnFailureListener { e ->
                    Log.w("RoomChatActivity", "Failed to send message.", e)
                }


            editMessage.text.clear()
        }
    }
}