package com.example.betak

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.example.betak.databinding.ActivityMainBinding
import com.example.betak.model.entity.IntroSlide
import com.example.betak.ui.activity.DashboardActivity
import com.example.betak.ui.activity.SignUpActivity
import com.example.betak.ui.adapter.IntroSliderAdapter
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val introSliderAdapter = IntroSliderAdapter(

        listOf(
              IntroSlide("ابحث عن عامل", "اسهل طريقة للبحث عن شخص لانجاز اعمال منزلك السريعه", R.drawable.img3)
            , IntroSlide("ابحث عن عمل ", "سجل بياناتك واحصل علي عمل في منطقتك", R.drawable.img1)
            , IntroSlide("ريح دماغك ", " خليك في بيتك وصلح كل اللي عندك .....", R.drawable.img2)
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       binding = DataBindingUtil.setContentView(this , R.layout.activity_main)

        binding.introSliderViewPager.adapter = introSliderAdapter

        setUpIndicators()
        setCurrentIndicator(0)

        binding.introSliderViewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicator(position)
            }
        })
        binding.btnNext.setOnClickListener {

          /*  if (binding.introSliderViewPager.currentItem + 1 < introSliderAdapter.itemCount){
              binding.introSliderViewPager.currentItem += 1 }*/

            startActivity(Intent(this ,SignUpActivity::class.java))

            }


        if (FirebaseAuth.getInstance().currentUser!=null){
           var intent = Intent(this , DashboardActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
        }
    }

    private fun setUpIndicators(){

      val indicators = arrayOfNulls<ImageView>(introSliderAdapter.itemCount)

        val layoutParams : LinearLayout.LayoutParams = LinearLayout.LayoutParams(WRAP_CONTENT , WRAP_CONTENT)
        layoutParams.setMargins(8 , 0 , 8  , 0)

        for (i in indicators.indices){

            indicators[i] = ImageView(applicationContext)
            indicators[i].apply {
                this?.setImageDrawable(ContextCompat.getDrawable(
             applicationContext , R.drawable.indicators_inactive
                ))
                this?.layoutParams= layoutParams
            }
            binding.indicatorsContainer.addView(indicators[i])
        }

    }

    private fun setCurrentIndicator(index : Int){
        val childCount = binding.indicatorsContainer.childCount

        for (i in 0 until childCount){
            val imageView = binding.indicatorsContainer.get(i) as ImageView
            if(i==index){
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(applicationContext , R.drawable.indicator_active)
                )
            }else{

                imageView.setImageDrawable(
                    ContextCompat.getDrawable(applicationContext , R.drawable.indicators_inactive)
                )
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()

    }
}