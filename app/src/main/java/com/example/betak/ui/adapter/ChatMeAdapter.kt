package com.example.betak.ui.adapter

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.betak.databinding.ChatItemMeBinding
import com.example.betak.databinding.ChatItemOtherBinding
import com.example.betak.model.entity.Chat
import com.google.firebase.auth.FirebaseAuth


class ChatAdapter(private var mChats : List<Chat>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var  viewHolder: RecyclerView.ViewHolder

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        when (viewType) {
            VIEW_TYPE_ME -> {
                viewHolder= ChatAdapter.MeViewHolder(ChatItemMeBinding.inflate(LayoutInflater.from(parent.context)))
            }
            VIEW_TYPE_OTHER -> {
                viewHolder= ChatAdapter.OtherViewHolder(ChatItemOtherBinding.inflate(LayoutInflater.from(parent.context)))
            }
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = mChats.get(position)
        if (TextUtils.equals(mChats[position].senderId,
                        FirebaseAuth.getInstance().currentUser!!.uid)) {
            (holder as MeViewHolder).bind(item)
        } else {
            (holder as OtherViewHolder).bind(item)
        }
    }

    class MeViewHolder (private  var binding : ChatItemMeBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(item : Chat){
            binding.chat= item
            binding.executePendingBindings()
        }
    }

    class OtherViewHolder (private  var binding : ChatItemOtherBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(item : Chat){
            binding.chat= item
            binding.executePendingBindings()
        }
    }


    override fun getItemCount(): Int {
        return mChats.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (TextUtils.equals(mChats[position].senderId,
                        FirebaseAuth.getInstance().currentUser!!.uid)) {
            VIEW_TYPE_ME
        } else {
            VIEW_TYPE_OTHER
        }
    }
    companion object {
        private const val VIEW_TYPE_ME = 1
        private const val VIEW_TYPE_OTHER = 2
    }

}