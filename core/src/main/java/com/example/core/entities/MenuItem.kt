package com.example.core.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MenuItem (val id:Int?=0,
                     val name:String?="",
                     val rate:Float?=0f,
                     val color:String?="",
                     val description:String?="",
                     val thumbnail:String?="",
                     val price:Int?=0
):Parcelable
