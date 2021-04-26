package com.example.betak.model.utils
import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import com.example.betak.R
import com.example.betak.model.entity.IDialogeClickListner


object CustomDialoge {

    public lateinit var iDialogeClickListner: IDialogeClickListner
    public lateinit var rating : RatingBar
    public lateinit var opinion: EditText
    public lateinit var btn_cancel: Button
    public lateinit var btn_ok: Button



    fun showLoginDialoge(
        context: Context,
        iDialogeClickListner: IDialogeClickListner
    ) {

        this.iDialogeClickListner = iDialogeClickListner

        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        val dialogeView: View = LayoutInflater.from(context).inflate(R.layout.dialoge, null)
        dialog.setContentView(dialogeView)


        rating = dialogeView.findViewById<RatingBar>(R.id.rating_bar)
        opinion = dialogeView.findViewById<EditText>(R.id.txt_opinion)
        btn_ok =  dialogeView.findViewById<Button>(R.id.btn_ok)
        btn_cancel = dialogeView.findViewById<Button>(R.id.btn_cancel)

        dialog.setCancelable(false)
        dialog.show()
        val window: Window? = dialog.getWindow()
        window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)


      btn_cancel.setOnClickListener {
       iDialogeClickListner.onClickNegativeButton(dialog);
      }

      btn_ok.setOnClickListener {
        if(opinion.text.isEmpty()){
           opinion.error="متسبش المكان فاضي"
        }else {
          iDialogeClickListner.onClickPositiveButton(dialog , opinion = opinion.text.toString() , rating = rating.rating)
        }
        }
    }
}