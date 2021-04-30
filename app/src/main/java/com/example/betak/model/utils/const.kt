package com.example.betak.model.utils

import android.content.Context
import android.widget.ArrayAdapter
import com.example.betak.R


var listJobs = arrayListOf("ممرضة", "سباك" , "حداد" , "مدرس" , "كهربائي" , "عامل سويتش" , "أدوات منزلية" ,
    "طباخ" , "حلاق" ,"عمال نظافة" , "كوافير" , "مربية أطفال"
        ,"عامل سيراميك" , "عامل دهان")


var listGovernators = arrayListOf("الاسكندرية" , "أسوان" , "أسيوط" , "البحيرة"
    , "بني سويف" , "القاهرة" , "الدقهلية" ,
    "دمياط" , "الفيوم" , "الغربية" , "الجيزة" ,
    "الاسماعيليه", "كفر الشيخ"  , "المنيا" , "المنوفية" ,
    "الوادي الجديد" ,  "القليوبية" , "قنا", "الشرقية", "سوهاج")



var listAlex = arrayListOf("المنتزه" , "الشرق" , "الوسط" , "الجمرك"
    , "العجمي" , "العامرية" , "برج العرب" )

var listAswan = arrayListOf("أسوان" , "ادفو" , "كوم امبو" , "دراو"
    , "نصر النوبة" )

var listAssuit = arrayListOf("أسيوط" , "ديروط" , "قوصية" , "أبنوب"
    , "منفلوط" , "أبو تيب" , "الغنايم" ,
    "ساحل سليم" , "البداري" , "صدفا" , "الفتح")

var listBehairah = arrayListOf("رشيد" , "شبراخيت" , "ايتاي البارود" , "أبو حمص"
    , "كفر الدوار" , "الدلنجات" , "كوم حمادة" ,
    "حوش عيسي" , "دمنهور" , "المحموية" , "ادكو" ,
    "أبو المطامير", "الرحمانية" , "النوبارية" , "الجديدة" , "وادي النطرون")

var listBani_swaif = arrayListOf("اهناسيا" , "الفشن" , "الواسطي" , "ببا"
    , "سمسطا" , "بني سويف" )

var listCairo= arrayListOf("مصر الجديدة" , "النزهة" , "غرب مدينة نصر" , "شرق مدينة نصر" , "عين شمس" , "حي السلام أول"
    , "المرج", "منشأة ناصر" , "المطرية" , "حي وسط" , "" , "" , ""
        , "" , "" , "" , "" ,"")

var listDaqahlia = arrayListOf("أجا" , "السنبلاوين" , "المنزلة" , "بلقاس"
    , "بني عبيد" , "دكرنس" )


var listDammitta = arrayListOf("دمياط" , "كفر سعد" , "فارسكور" , "الزرقا")


var listFauiom = arrayListOf("أبشواي" , "اطسا" , "الفيوم" , "سنورس"
    , "طامية" )

var listGharbia = arrayListOf("المحلة الكبري" , "طنطا" , "سمنود" , "قطور"
    , "السنطه" , "زفتي" , "بسيون" ,
    "كفر الزيات" )

var listGiza = arrayListOf("أبو النمرس" , "أطفيح" , "أوسيم" , "البدرشين"
    , "الواحات" , "الحوامدية" , "الصف" ,
    "كرداسة" , "كفر غطاطي" , "منشأة القنطرة")


var listEsmailia = arrayListOf("الاسماعيلية" , "التل الكبير" , "فايد" , "القنطرة شرق"
    , "القنطرة غرب" , "أبو صوير" , "القصاصين" )


var listKafr_shiekh = arrayListOf("كفر الشيخ" , "الحامول" , "بيلا" , "قلين"
    , "مطوبس" , "دسوق" , "بلطيم" ,
    "سيدي سالم" , "الرياض" , "فوة" )


var listMinia = arrayListOf("أبو قرقاص" , "بني مزار" , "دير مواس" , "سمالوط"
    , "العدوة" , "مطاي" , "مغاغة" ,
    "ملوي" , "المنيا" )

var listMonoufia = arrayListOf("أشمون" , "الباجور" , "بركة السبع" , "منوف"
    , "مدينة السادات" , "سرس الليان" , "تلا" ,
    "الشهداء" , "شبين الكوم" , "قويسنا" )


var listWadi_gadid = arrayListOf("الداخلة" , "الخارجة" , "الفرافرة"
        , "مركز باريس")



var listQualuibia = arrayListOf("الخانكة" , "بنها" , "شبرا الخيمة" , "شبين القناطر"
    , "طوخ" , "قليوب" , "القناطر الخيرية" )

var listQina = arrayListOf("أبو تشت" , "فرشوط" , "نجع حمادي" , "الوقف"
    , "دشنا" , "قنا" , "قفط" ,
    "قوص" , "نقادة" , "أرمنت" , "الأقصر")

var listSharqia = arrayListOf("أبو حماد" , "أبو كبير" , "أولاد صقر" , "بلبيس"
    , "الحسينية" , "ديرب نجم" , "الزقازيق" ,
    "الصالحيه" , "العاشر من رمضان" , "فاقوس" , "كفر صقر" ,
    "منيا القمح", "هيهيا" , "مشتول" , "السوق" , "الابراهيمية"
        , "القرين" , "القنايات" )

var listSohag = arrayListOf("أخميم" , "البلينا" , "جرجا" , "دار السلام"
    , "جهينة" , "ساقلتا" , "سوهاج" ,
    "طما" , "طهطا" , "المراغة" , "المنشأة")


fun getSuccessArea(context: Context , position : Int) : ArrayAdapter<String>{

    var areaAdapter : ArrayAdapter<String> = ArrayAdapter<String>(context, R.layout.spinner_list, listAlex);

    if (position==0){
        areaAdapter  = ArrayAdapter<String>(context, R.layout.spinner_list, listAlex);
    }
    else if (position==1){
        areaAdapter  = ArrayAdapter<String>(context, R.layout.spinner_list, listAswan);
    }
    else if (position==2){
        areaAdapter  = ArrayAdapter<String>(context, R.layout.spinner_list, listAssuit);
    }
    else if (position==3){
        areaAdapter  = ArrayAdapter<String>(context, R.layout.spinner_list, listBehairah);
    }
    else if (position==4){
        areaAdapter  = ArrayAdapter<String>(context, R.layout.spinner_list, listBani_swaif);
    }
    else if (position==5){
        areaAdapter  = ArrayAdapter<String>(context, R.layout.spinner_list, listCairo);
    }
    else if (position==6){
        areaAdapter  = ArrayAdapter<String>(context, R.layout.spinner_list, listDaqahlia);
    }

    else if (position==7){
        areaAdapter  = ArrayAdapter<String>(context, R.layout.spinner_list, listDammitta);
    }
    else if (position==8){
        areaAdapter  = ArrayAdapter<String>(context, R.layout.spinner_list, listFauiom);
    }
    else if (position==9){
        areaAdapter  = ArrayAdapter<String>(context, R.layout.spinner_list, listGharbia);
    }
    else if (position==10){
        areaAdapter  = ArrayAdapter<String>(context, R.layout.spinner_list, listGiza);
    }
    else if (position==11){
        areaAdapter  = ArrayAdapter<String>(context, R.layout.spinner_list, listEsmailia);
    }
    else if (position==12){
        areaAdapter  = ArrayAdapter<String>(context, R.layout.spinner_list, listKafr_shiekh);
    }

    else if (position==13){
        areaAdapter  = ArrayAdapter<String>(context, R.layout.spinner_list, listMinia);
    }
    else if (position==14){
        areaAdapter  = ArrayAdapter<String>(context, R.layout.spinner_list, listMonoufia);
    }
    else if (position==15){
        areaAdapter  = ArrayAdapter<String>(context, R.layout.spinner_list, listWadi_gadid);
    }
    else if (position==16){
        areaAdapter  = ArrayAdapter<String>(context, R.layout.spinner_list, listQualuibia);
    }
    else if (position==17){
        areaAdapter  = ArrayAdapter<String>(context, R.layout.spinner_list, listQina);
    }
    else if (position==18){
        areaAdapter  = ArrayAdapter<String>(context, R.layout.spinner_list, listSharqia);
    }
    else if (position==19){
        areaAdapter  = ArrayAdapter<String>(context, R.layout.spinner_list, listSohag);
    }
    return areaAdapter
}


fun getListArea(position : Int) :  ArrayList<String>  {

    var listArea  = listAlex

    if (position==0){
        listArea  =  listAlex
    }
    else if (position==1){
        listArea  =  listAswan
    }
    else if (position==2){
        listArea  = listAssuit
    }
    else if (position==3){
        listArea  =  listBehairah
    }
    else if (position==4){
        listArea  =listBani_swaif
    }
    else if (position==5){
        listArea  =  listCairo
    }
    else if (position==6){
        listArea  = listDaqahlia
    }

    else if (position==7){
        listArea  = listDammitta
    }
    else if (position==8){
        listArea  = listFauiom
    }
    else if (position==9){
        listArea  =  listGharbia
    }
    else if (position==10){
        listArea  = listGiza
    }
    else if (position==11){
        listArea  =  listEsmailia
    }
    else if (position==12){
        listArea  =  listKafr_shiekh
    }

    else if (position==13){
        listArea  =  listMinia
    }
    else if (position==14){
        listArea  = listMonoufia
    }
    else if (position==15){
        listArea  =  listWadi_gadid
    }
    else if (position==16){
        listArea  = listQualuibia
    }
    else if (position==17){
        listArea  = listQina
    }
    else if (position==18){
        listArea  = listSharqia
    }
    else if (position==19){
        listArea  =  listSohag
    }
    return listArea
}