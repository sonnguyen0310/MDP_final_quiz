package com.sng.finalproject2.ui.home.dummy

class AssignmentContent : LectureContent() {
    init {

    }

    override fun initData() {
        addItem(createLecture("L1 L2 HomeWork", "W1D1_L1_L2_Homework_1.pdf"))
        addItem(createLecture("W1D2 L3 HomeWork", "W1D2_L3_HOMEWORK_2.pdf"))
        addItem(createLecture("W1D4 L5 HomeWork", "W1D4_L5_HOMEWORK_4.pdf"))
        addItem(createLecture("W1D5D6 L6 HOMEWORK 5", "W1D5D6_L6_HOMEWORK_5.pdf"))

        addItem(
            createLecture(
                "W2 L6L7 HOMEWORK 6 Group Assignment",
                "W2_L6L7_HOMEWORK_6_Group_Assignment.pdf"
            )
        )
    }
}