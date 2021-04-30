package com.example.betak.ui.adapter

import android.annotation.SuppressLint
import android.text.TextUtils
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.betak.R
import com.example.betak.databinding.ChatItemMeBinding
import com.example.betak.databinding.ChatItemOtherBinding
import com.example.betak.model.entity.Chat
import com.google.firebase.auth.FirebaseAuth
import java.text.SimpleDateFormat
import java.util.*


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

    @SuppressLint("SimpleDateFormat")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = mChats.get(position)
        val currentTime = System.currentTimeMillis()

        if (item.getOk()==true){
            holder.itemView.findViewById<TextView>(R.id.date).visibility = View.VISIBLE
            val date = DateUtils.getRelativeTimeSpanString(item.getCurrentTime(),
                    currentTime , DateUtils.DAY_IN_MILLIS).toString()
            holder.itemView.findViewById<TextView>(R.id.date).text = date
        }else{
            holder.itemView.findViewById<TextView>(R.id.date).visibility = View.GONE

        }

        if (TextUtils.equals(
                mChats[position].getSenderId(),
                FirebaseAuth.getInstance().currentUser!!.uid
            )
        ) {
            (holder as MeViewHolder).bind(item)

            if (item.getSeen() == true) {

                holder.itemView.findViewById<ImageView>(R.id.seen).visibility = View.VISIBLE

            } else {

                holder.itemView.findViewById<ImageView>(R.id.seen).visibility = View.GONE
            }

            val timeFormatter = SimpleDateFormat("h:mm a")
            val displayValue: String = timeFormatter.format(item.getTime())
            holder.itemView.findViewById<TextView>(R.id.time).text = displayValue

        } else {

            (holder as OtherViewHolder).bind(item)

            val timeFormatter = SimpleDateFormat("h:mm a")
            val displayValue: String = timeFormatter.format(item.getTime())
            holder.itemView.findViewById<TextView>(R.id.time).text = displayValue

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
        return if (TextUtils.equals(mChats[position].getSenderId(), FirebaseAuth.getInstance().currentUser!!.uid)) {
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