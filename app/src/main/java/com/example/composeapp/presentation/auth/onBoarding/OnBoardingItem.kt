package com.example.composeapp.presentation.auth.onBoarding

import com.example.composeapp.R

sealed class OnBoardingItem{
    abstract val  icon : Int
    abstract val  title : String
    abstract val  content : String
    object FirstPage:OnBoardingItem(){
        override val icon = R.drawable.first_vector
        override  val title:String ="أطلب وتدلل وخليك مرتاح نوصلك \n" + "كل شيء وأي شيء من وين ما تحب"
        override  val content:String ="لوريم ايبسوم دولار سيت أميت ,كون\n" + "سيكتيتور أدايبا يسكينج أليايت لوريم\n" + "ايبسوم دولار سيت أميت"
    }

    object SecondPage:OnBoardingItem(){
        override val icon = R.drawable.second_vector
        override  val title:String ="أوردر برو يخدمك بـ احتراف وسرعه وهمه"
        override  val content:String ="لوريم ايبسوم دولار سيت أميت ,كون\n" + "سيكتيتور أدايبا يسكينج أليايت لوريم\n" + "ايبسوم دولار سيت أميت"
    }

    object ThirdPage:OnBoardingItem(){
        override val icon = R.drawable.third_vector
        override  val title:String = "أوردر برو يخدمك بـ احتراف وسرعه وهمه"
        override  val content:String ="لوريم ايبسوم دولار سيت أميت ,كون\n" + "سيكتيتور أدايبا يسكينج أليايت لوريم\n" + "ايبسوم دولار سيت أميت"
    }
}
