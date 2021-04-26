package com.example.betak.ui.adapter

import android.content.Context
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.betak.R
import com.example.betak.databinding.ActivityChatBinding.inflate
import com.example.betak.databinding.EmployeeItemBinding
import com.example.betak.databinding.NotiListItemBinding
import com.example.betak.model.entity.Employee
import com.example.betak.model.entity.Noti
import de.hdodenhof.circleimageview.CircleImageView

class NotiAdapter (var listNotes : List<Noti> , private var context : Context): ListAdapter<Noti, NotiAdapter.viewHolder>(DiffCallback) {

    class viewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

      var currentTime = System.currentTimeMillis()

      fun bind(item : Noti){
       itemView.findViewById<TextView>(R.id.noti_txt).text = item.senderName
       itemView.findViewById<TextView>(R.id.noti_time).text =
           DateUtils.getRelativeTimeSpanString(item.time, currentTime,
               DateUtils.SECOND_IN_MILLIS)
      }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.noti_list_item , parent , false)
        return NotiAdapter.viewHolder(view)

    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
     var item = listNotes.get(position)
        Glide
            .with(context)
            .load(item.senderImage)
            .centerCrop()
            .placeholder(R.drawable.profile)
            .into(holder.itemView.findViewById<CircleImageView>(R.id.noti_img));
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return listNotes.size
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Noti>() {
        override fun areItemsTheSame(oldItem: Noti, newItem: Noti): Boolean {
            return oldItem.time == newItem.time
        }

        override fun areContentsTheSame(oldItem: Noti, newItem: Noti): Boolean {
            return oldItem.time == newItem.time

        }
    }


}
