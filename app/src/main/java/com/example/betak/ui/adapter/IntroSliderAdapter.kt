package com.example.betak.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.betak.model.entity.IntroSlide
import com.example.betak.R

class IntroSliderAdapter(private val introSliders : List<IntroSlide> ) : RecyclerView.Adapter<IntroSliderAdapter.IntroSliderViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IntroSliderViewHolder {
        return IntroSliderViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.slide_item_container, parent , false))

    }

    override fun getItemCount(): Int {
        return introSliders.size
    }

    override fun onBindViewHolder(holder: IntroSliderViewHolder, position: Int) {

        var slide = introSliders.get(position)

        holder.bind(slide)

    }

    inner class IntroSliderViewHolder(view : View) : RecyclerView.ViewHolder(view){

        private val textTitle = view.findViewById<TextView>(R.id.textTitle)
        private val textDescription = view.findViewById<TextView>(R.id.textDescription)
        private val imageIcon = view.findViewById<ImageView>(R.id.imageSlideIcon)

        fun bind(introSlide: IntroSlide){

            textTitle.text = introSlide.title
            textDescription.text = introSlide.description
            imageIcon.setImageResource(introSlide.icon)
        }


    }
}