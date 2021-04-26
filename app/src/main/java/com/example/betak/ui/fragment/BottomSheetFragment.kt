package com.example.betak.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.example.betak.R
import com.example.betak.model.entity.Employee
import com.example.betak.model.entity.Rating
import com.example.betak.model.viewModel.ProfileViewModel
import com.example.betak.ui.adapter.ChatAdapter
import com.example.betak.ui.adapter.RateAdapter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.util.zip.Inflater


class BottomSheetFragment() : BottomSheetDialogFragment()  {

    private lateinit var viewModel : ProfileViewModel
    private lateinit var recyclerView : RecyclerView
    private lateinit var ratings : List<Rating>
    private lateinit var id: String

    companion object{
       public val newInstance: BottomSheetDialogFragment =  BottomSheetFragment()
       public const val TAG : String = "ActionBottomDialog"
    }

    @Nullable
    override fun onCreateView(
        inflater: LayoutInflater, @Nullable container: ViewGroup?,
        @Nullable savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_bottom_sheet , container , false)

        ratings = ArrayList()

        viewModel = ViewModelProviders.of(this).get(ProfileViewModel::class.java)
        recyclerView = view.findViewById(R.id.recycler_view)
        id = arguments?.getString("id")!!

        GlobalScope.launch {
            viewModel.getRatings(id)
        }

        viewModel.ratings.observe(this , androidx.lifecycle.Observer {
            if(it.size > 0){
                ratings = it
            }else{
                ratings = ArrayList()
            }
            recyclerView.adapter = RateAdapter(ratings)
        })

        return view;
    }

}










