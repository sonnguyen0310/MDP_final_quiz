package com.sng.finalproject2.model

import com.sng.finalproject2.ui.home.dummy.LectureContent

interface ILectureCallback {
    fun onLectureClick(lecture: LectureContent.LectureItem)
}