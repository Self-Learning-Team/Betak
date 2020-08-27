package com.example.betak.model.utils

import android.R
import android.content.Context
import android.widget.ArrayAdapter


var listJobs = listOf("ممرضة", "سباك" , "حداد" , "مدرس" , "كهربائي" , "عامل سويتش" , "أدوات منزلية" ,
    "طباخ" , "حلاق" ,"عمال نظافة" , "كوافير" , "مربية أطفال" ,"عامل سيراميك" , "عامل دهان")


var listGovernators = listOf("الاسكندرية" , "أسوان" , "أسيوط" , "البحيرة"
    , "بني سويف" , "القاهرة" , "الدقهلية" ,
    "دمياط" , "الفيوم" , "الغربية" , "الجيزة" ,
    "الاسماعيليه", "كفر الشيخ"  , "المنيا" , "المنوفية" ,
    "الوادي الجديد" ,  "القليوبية" , "قنا", "الشرقية", "سوهاج")



var listAlex = listOf("المنتزه" , "الشرق" , "الوسط" , "الجمرك"
    , "العجمي" , "العامرية" , "برج العرب" )

var listAswan = listOf("أسوان" , "ادفو" , "كوم امبو" , "دراو"
    , "نصر النوبة" )

var listAssuit = listOf("أسيوط" , "ديروط" , "قوصية" , "أبنوب"
    , "منفلوط" , "أبو تيب" , "الغنايم" ,
    "ساحل سليم" , "البداري" , "صدفا" , "الفتح")

var listBehairah = listOf("رشيد" , "شبراخيت" , "ايتاي البارود" , "أبو حمص"
    , "كفر الدوار" , "الدلنجات" , "كوم حمادة" ,
    "حوش عيسي" , "دمنهور" , "المحموية" , "ادكو" ,
    "أبو المطامير", "الرحمانية" , "النوبارية" , "الجديدة" , "وادي النطرون")

var listBani_swaif = listOf("اهناسيا" , "الفشن" , "الواسطي" , "ببا"
    , "سمسطا" , "بني سويف" )

var listCairo= listOf("" , "")

var listDaqahlia = listOf("أجا" , "السنبلاوين" , "المنزلة" , "بلقاس"
    , "بني عبيد" , "دكرنس" )


var listDammitta = listOf("دمياط" , "كفر سعد" , "فارسكور" , "الزرقا")


var listFauiom = listOf("أبشواي" , "اطسا" , "الفيوم" , "سنورس"
    , "طامية" )

var listGharbia = listOf("المحلة الكبري" , "طنطا" , "سمنود" , "قطور"
    , "السنطه" , "زفتي" , "بسيون" ,
    "كفر الزيات" )

var listGiza = listOf("أبو النمرس" , "أطفيح" , "أوسيم" , "البدرشين"
    , "الواحات" , "الحوامدية" , "الصف" ,
    "كرداسة" , "كفر غطاطي" , "منشأة القنطرة"
)


var listEsmailia = listOf("الاسماعيلية" , "التل الكبير" , "فايد" , "القنطرة شرق"
    , "القنطرة غرب" , "أبو صوير" , "القصاصين" )


var listKafr_shiekh = listOf("كفر الشيخ" , "الحامول" , "بيلا" , "قلين"
    , "مطوبس" , "دسوق" , "بلطيم" ,
    "سيدي سالم" , "الرياض" , "فوة" )


var listMinia = listOf("أبو قرقاص" , "بني مزار" , "دير مواس" , "سمالوط"
    , "العدوة" , "مطاي" , "مغاغة" ,
    "ملوي" , "المنيا" )

var listMonoufia = listOf("أشمون" , "الباجور" , "بركة السبع" , "منوف"
    , "مدينة السادات" , "سرس الليان" , "تلا" ,
    "الشهداء" , "شبين الكوم" , "قويسنا" )


var listWadi_gadid = listOf("الداخلة" , "الخارجة" , "الفرافرة" , "مركز باريس")



var listQualuibia = listOf("الخانكة" , "بنها" , "شبرا الخيمة" , "شبين القناطر"
    , "طوخ" , "قليوب" , "القناطر الخيرية" )

var listQina = listOf("أبو تشت" , "فرشوط" , "نجع حمادي" , "الوقف"
    , "دشنا" , "قنا" , "قفط" ,
    "قوص" , "نقادة" , "أرمنت" , "الأقصر")


var listSharqia = listOf("أبو حماد" , "أبو كبير" , "أولاد صقر" , "بلبيس"
    , "الحسينية" , "ديرب نجم" , "الزقازيق" ,
    "الصالحيه" , "العاشر من رمضان" , "فاقوس" , "كفر صقر" ,
    "منيا القمح", "هيهيا" , "مشتول" , "السوق" , "الابراهيمية" , "القرين" , "القنايات" )

var listSohag = listOf("أخميم" , "البلينا" , "جرجا" , "دار السلام"
    , "جهينة" , "ساقلتا" , "سوهاج" ,
    "طما" , "طهطا" , "المراغة" , "المنشأة")

fun getSuccessArea(context: Context , position : Int) : ArrayAdapter<String>{

    var areaAdapter : ArrayAdapter<String> = ArrayAdapter<String>(context, R.layout.simple_spinner_item, listAlex);

    if (position==0){
        areaAdapter  = ArrayAdapter<String>(context, R.layout.simple_spinner_item, listAlex);
    }
    else if (position==1){
        areaAdapter  = ArrayAdapter<String>(context, R.layout.simple_spinner_item, listAswan);
    }
    else if (position==2){
        areaAdapter  = ArrayAdapter<String>(context, R.layout.simple_spinner_item, listAssuit);
    }
    else if (position==3){
        areaAdapter  = ArrayAdapter<String>(context, R.layout.simple_spinner_item, listBehairah);
    }
    else if (position==4){
        areaAdapter  = ArrayAdapter<String>(context, R.layout.simple_spinner_item, listBani_swaif);
    }
    else if (position==5){
        areaAdapter  = ArrayAdapter<String>(context, R.layout.simple_spinner_item, listCairo);
    }
    else if (position==6){
        areaAdapter  = ArrayAdapter<String>(context, R.layout.simple_spinner_item, listDaqahlia);
    }

    else if (position==7){
        areaAdapter  = ArrayAdapter<String>(context, R.layout.simple_spinner_item, listDammitta);
    }
    else if (position==8){
        areaAdapter  = ArrayAdapter<String>(context, R.layout.simple_spinner_item, listFauiom);
    }
    else if (position==9){
        areaAdapter  = ArrayAdapter<String>(context, R.layout.simple_spinner_item, listGharbia);
    }
    else if (position==10){
        areaAdapter  = ArrayAdapter<String>(context, R.layout.simple_spinner_item, listGiza);
    }
    else if (position==11){
        areaAdapter  = ArrayAdapter<String>(context, R.layout.simple_spinner_item, listEsmailia);
    }
    else if (position==12){
        areaAdapter  = ArrayAdapter<String>(context, R.layout.simple_spinner_item, listKafr_shiekh);
    }

    else if (position==13){
        areaAdapter  = ArrayAdapter<String>(context, R.layout.simple_spinner_item, listMinia);
    }
    else if (position==14){
        areaAdapter  = ArrayAdapter<String>(context, R.layout.simple_spinner_item, listMonoufia);
    }
    else if (position==15){
        areaAdapter  = ArrayAdapter<String>(context, R.layout.simple_spinner_item, listWadi_gadid);
    }
    else if (position==16){
        areaAdapter  = ArrayAdapter<String>(context, R.layout.simple_spinner_item, listQualuibia);
    }
    else if (position==17){
        areaAdapter  = ArrayAdapter<String>(context, R.layout.simple_spinner_item, listQina);
    }
    else if (position==18){
        areaAdapter  = ArrayAdapter<String>(context, R.layout.simple_spinner_item, listSharqia);
    }
    else if (position==19){
        areaAdapter  = ArrayAdapter<String>(context, R.layout.simple_spinner_item, listSohag);
    }
    return areaAdapter
}