package com.example.aplikasikonsultasi

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ChatAdapter (private var messageList: ArrayList<Message>) : RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {

    fun updateMessages(newMessages: List<Message>) {
        messageList.clear()
        messageList.addAll(newMessages)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_message, parent, false)
        return ChatViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        val message = messageList[position]
        holder.bind(message)
    }

    override fun getItemCount(): Int {
        return messageList.size
    }

    inner class ChatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val messageTextView: TextView = itemView.findViewById(R.id.txtMessage)

        fun bind(message: Message) {
            messageTextView.text = message.text
            setChatBackground(message.senderId)
        }

        private fun setChatBackground(senderId: String?) {
            Log.d("ChatAdapter", "Setting background for senderId: $senderId")
            val backgroundColor = if (senderId == "Admin") {
                android.graphics.Color.parseColor("#F7C59F")
            } else {
                android.graphics.Color.parseColor("#5DF96D")
            }
            messageTextView.setBackgroundColor(backgroundColor)
            Log.d("ChatAdapter", "Sender: $senderId, Background Color: $backgroundColor")
        }
    }
}

