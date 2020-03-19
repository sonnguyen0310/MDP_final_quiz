package com.sng.finalproject2

import android.content.Intent
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.sng.finalproject2.ui.home.LectureFragment
import com.sng.finalproject2.ui.home.dummy.LectureContent
import com.sng.finalproject2.ui.pdfviewer.PdfViewerActivity

class MainActivity : AppCompatActivity(), LectureFragment.OnListFragmentInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
                R.id.navigation_home, R.id.navigation_assignment, R.id.navigation_quiz))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onListFragmentInteraction(item: LectureContent.LectureItem?) {
        val i = Intent(this, PdfViewerActivity::class.java)
        i.putExtra("DATA", item?.fileName)
        startActivity(i)

    }
}
