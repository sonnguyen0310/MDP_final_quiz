package com.sng.finalproject2.model

import com.google.firebase.database.IgnoreExtraProperties
import java.io.Serializable

@IgnoreExtraProperties
data class Question (val question: String = "", val answer: List<String> = arrayListOf(), val correct: Int = -1, var selected: Int = -1) : Serializable
