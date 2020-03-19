package com.sng.finalproject2.ui.home.dummy

import java.util.ArrayList
import java.util.HashMap

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 *
 * TODO: Replace all uses of this class before publishing your app.
 */
open class LectureContent {

    /**
     * An array of sample (dummy) items.
     */
    public val ITEMS: MutableList<LectureItem> = ArrayList()

    /**
     * A map of sample (dummy) items, by ID.
     */
    val ITEM_MAP: MutableMap<String, LectureItem> = HashMap()

    var count = 1;
    private val COUNT = 25

    init {
        initData()
    }

    open fun initData() {
        addItem(createLecture("Introduction", "l1_introduction.pdf"))
        addItem(createLecture("Kotlin Fundamendals", "Lesson_2_Kotlin_Fundamendals.pdf"))
        addItem(createLecture("Lesson 3 CreatingFirstApp", "Lesson_3_CreatingFirstApp.pdf"))
        addItem(createLecture("Lesson 3 LogCat Filter Steps", "Lesson_3_LogCat Filter Steps.pdf"))

        addItem(
            createLecture(
                "Lesson 4 Activity and Lifecycles Notes",
                "Lesson_4_Activity_and_Lifecycles_Notes.pdf"
            )
        )
        addItem(
            createLecture(
                "Lesson 4 Views Layouts Resources and Lifecycle.pdf",
                "Lesson_4_Views_Layouts_Resources_and Lifecycle.pdf"
            )
        )
        addItem(createLecture("Lesson 5 Activities Intents.pdf", "Lesson_5_Activities_Intents.pdf"))
        addItem(
            createLecture(
                "Lesson 6 User Input Controls Day1",
                "Lesson_6_User_Input_Controls_Day1.pdf"
            )
        )
        addItem(
            createLecture(
                "Lesson 6 User Input Controls Day2.pdf",
                "Lesson_6_User_Input_Controls_Day2.pdf"
            )
        )
        addItem(
            createLecture(
                "Lesson 7 Menus Tabs Fragments Day1.pdf",
                "Lesson_7_Menus_Tabs_Fragments_Day1.pdf"
            )
        )
        addItem(
            createLecture(
                "Lesson 7 Menus Tabs Fragments Day2.pdf",
                "Lesson_7_Menus_Tabs_Fragments_Day2.pdf"
            )
        )
        addItem(
            createLecture(
                "Lesson 8 SharedPreferences Webview.pdf",
                "Lesson_8_SharedPreferences_Webview.pdf"
            )
        )
        addItem(
            createLecture(
                "Lesson 8 SPF Step by Step View Persisted data_1.pdf",
                "Lesson_8_SPF_Step_by_Step_View_Persisted data_1.pdf"
            )
        )
        addItem(createLecture("Lesson 9 Multimedia_Updated.pdf", "Lesson_9_Multimedia_Updated.pdf"))
        addItem(createLecture("Lesson 10 SQLite.pdf", "Lesson_10_SQLite.pdf"))
        addItem(
            createLecture(
                "Lesson 10 SQLite Sample Queries.pdf",
                "Lesson_10_SQLite_Sample_Queries.pdf"
            )
        )
        addItem(createLecture("Lesson 11 Sensors.pdf", "Lesson_11_Sensors.pdf"))
        addItem(createLecture("Lesson 12 Localization.pdf", "Lesson_12_Localization.pdf"))
        addItem(createLecture("Lesson 13 Publish APK 1.pdf", "Lesson_13_Publish_APK_1.pdf"))
    }

    protected fun addItem(item: LectureItem) {
        ITEMS.add(item)
        ITEM_MAP[item.id] = item
    }

    protected fun createLecture(title: String, name: String): LectureItem {
        return LectureItem(count++.toString(), title, "", name)
    }

    private fun makeDetails(position: Int): String {
        val builder = StringBuilder()
        builder.append("Details about Item: ").append(position)
        for (i in 0..position - 1) {
            builder.append("\nMore details information here.")
        }
        return builder.toString()
    }

    data class LectureItem(
        val id: String,
        val content: String,
        val details: String,
        val fileName: String
    ) {
        override fun toString(): String = content
    }
}
