package com.example.nutriscan.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Objek(
    val image: Int,
    val kalori: String?,
    val nama: String?
): Parcelable
