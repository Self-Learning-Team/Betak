package com.example.betak.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.text.format.DateUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.betak.R
import com.example.betak.model.entity.Employee
import com.example.betak.model.entity.Noti
import com.google.firebase.firestore.FirebaseFirestore
import de.hdodenhof.circleimageview.CircleImageView

class NotiAdapter (var listNotes : List<Noti> , private var context : Context , private val onClickListener: NotiAdapter.OnClickListner): ListAdapter<Noti, NotiAdapter.viewHolder>(DiffCallback) {

    var notiRef = FirebaseFirestore.getInstance().collection("Notifications")

    class viewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

      var currentTime = System.currentTimeMillis()

      fun bind(item : Noti){
       itemView.findViewById<TextView>(R.id.noti_txt).text ="تم ارسال رسالة بواسطة " + item.senderName
       itemView.findViewById<TextView>(R.id.noti_time).text =
           DateUtils.getRelativeTimeSpanString(item.time, currentTime,
               DateUtils.SECOND_IN_MILLIS)
      }


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.noti_list_item , parent , false)
        return NotiAdapter.viewHolder(view)

    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: viewHolder, position: Int) {
     val item = listNotes.get(position)

     val background = holder.itemView.findViewById<RelativeLayout>(R.id.back_item)

        Log.e("adapter not click" , item.getOpen().toString())

        if(item.getOpen() == true){
          background.setBackgroundColor(context.resources.getColor(R.color.white))
        }else{
            background.setBackgroundColor(context.resources.getColor(R.color.colorClick))

        }

        Glide
            .with(context)
            .load(item.senderImage)
            .centerCrop()
            .placeholder(R.drawable.profile)
            .into(holder.itemView.findViewById<CircleImageView>(R.id.noti_img));

        holder.bind(item)

        holder.itemView.setOnClickListener {
            onClickListener.onClick(item)

            val update = mapOf("open" to true)
            notiRef.document(item.senderId!!).collection("noti")
                    .document(item.time.toString()).update(update).addOnSuccessListener {
                        Log.e("success update noti", "success")
                    }.addOnFailureListener {
                        Log.e("fail update noti", it.message.toString())
                    }

            Log.e("adapter click" , "true ")
        }
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

    class OnClickListner(val clickListner: (noti : Noti ) -> Unit) {
        fun onClick(noti : Noti ) = clickListner(noti)
    }

}
