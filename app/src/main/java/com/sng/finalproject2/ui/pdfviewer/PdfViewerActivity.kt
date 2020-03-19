package com.sng.finalproject2.ui.pdfviewer

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener
import com.github.barteksc.pdfviewer.listener.OnPageErrorListener
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle
import com.shockwave.pdfium.PdfDocument.Bookmark
import com.sng.finalproject2.R
import kotlinx.android.synthetic.main.activity_pdf_viewer.*


class PdfViewerActivity : AppCompatActivity(), OnPageChangeListener, OnLoadCompleteListener,
    OnPageErrorListener {
    private val REQUEST_CODE = 42
    val PERMISSION_CODE = 42042

    val READ_EXTERNAL_STORAGE = "android.permission.READ_EXTERNAL_STORAGE"
    var pageNumber = 0
    val TAG = "PDF_VIEWER"
    var fileName = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pdf_viewer)
        val data = intent.getStringExtra("DATA")
        if (data.isEmpty()) {
            finish()
            return
        }
        fileName = data
//        val permissionCheck = ContextCompat.checkSelfPermission(
//            this,
//            READ_EXTERNAL_STORAGE
//        )
//        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(
//                this,
//                arrayOf(READ_EXTERNAL_STORAGE),
//                PERMISSION_CODE
//            )
//            finish()
//        }
        openPdf()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String?>,
        grantResults: IntArray
    ) {
        if (requestCode == PERMISSION_CODE) {
            if (grantResults.size > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED
            ) {
                openPdf()
            }
        }
    }

    private fun openPdf() {
        try {

            pdfView.fromAsset(fileName)
                .defaultPage(pageNumber)
                .onPageChange(this)
                .enableAnnotationRendering(true)
                .onLoad(this)
                .scrollHandle(DefaultScrollHandle(this))
                .spacing(10) // in dp
                .onPageError(this)
                .load();
        } catch (e: Exception) {
            Log.e("ERROR", "" + e)
        }
    }

    override fun onPageChanged(page: Int, pageCount: Int) {
        pageNumber = page;
        title = String.format("%s %s / %s", fileName, page + 1, pageCount);
    }

    override fun loadComplete(nbPages: Int) {
        val meta = pdfView.documentMeta
        Log.d(TAG, "title = " + meta.title)
        Log.d(TAG, "author = " + meta.author)
        Log.d(TAG, "subject = " + meta.subject)
        Log.d(TAG, "keywords = " + meta.keywords)
        Log.d(TAG, "creator = " + meta.creator)
        Log.d(TAG, "producer = " + meta.producer)
        Log.d(TAG, "creationDate = " + meta.creationDate)
        Log.d(TAG, "modDate = " + meta.modDate)
        printBookmarksTree(pdfView.tableOfContents, "-");

    }

    override fun onPageError(page: Int, t: Throwable?) {
        Log.e(TAG, "Cannot load page " + page);
    }

    private fun printBookmarksTree(
        tree: List<Bookmark>,
        sep: String
    ) {
        for (b in tree) {
            Log.e(
                TAG,
                java.lang.String.format("%s %s, p %d", sep, b.title, b.pageIdx)
            )
            if (b.hasChildren()) {
                printBookmarksTree(b.children, "$sep-")
            }
        }
    }
}
