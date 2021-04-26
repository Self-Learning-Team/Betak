package com.example.betak.ui.adapter

import android.provider.Telephony
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.betak.R
import com.example.betak.databinding.EmployeeItemBinding
import com.example.betak.model.entity.Employee
import com.example.betak.model.entity.Rating
import com.google.firebase.firestore.FirebaseFirestore
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class RateAdapter(private var listRate: List<Rating>) :
    ListAdapter<Rating, RateAdapter.viewHolder>(DiffCallback) {

    class viewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var employeeRef = FirebaseFirestore.getInstance().collection("Employees")

        val client_img = itemView.findViewById<CircleImageView>(R.id.client_img)
        val client_op = itemView.findViewById<TextView>(R.id.client_op)
        val client_name = itemView.findViewById<TextView>(R.id.client_name)


        suspend fun bind(item: Rating) {
            client_op.setText(item.opinion!!)
            val snap = employeeRef.document(item.clientId!!).get()
            val client = snap.await().toObject(Employee::class.java)

          //  client_name.text = client!!.getName()+ "name"
        }

    }


    companion object DiffCallback : DiffUtil.ItemCallback<Rating>() {
        override fun areItemsTheSame(oldItem: Rating, newItem: Rating): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Rating, newItem: Rating): Boolean {
            return oldItem.clientId == newItem.clientId
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.rate_list_item, parent, false)
        return viewHolder(view)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val view = listRate.get(position)
        GlobalScope.launch {
            holder.bind(view)
        }
    }

    override fun getItemCount(): Int {
        return listRate.size
    }


}