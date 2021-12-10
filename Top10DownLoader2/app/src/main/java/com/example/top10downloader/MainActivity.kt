package com.example.top10downloader

import android.content.Context
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ListView
import java.net.URL
var TAG:String=""
class MainActivity : AppCompatActivity() {
    private var url: String ="https://www.google.com"
    var modifiedurl = "" + url

    var xmlListView: ListView? = null
    var downloadData :DownloadData?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        var count: Int = 0
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        xmlListView = findViewById<ListView>(R.id.xmlListView)
        downloadURL("http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/topsongs/limit=10/xml")

        Log.d(TAG,"finished onCreate")

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_url,menu)
        return true

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var feedUrl: String=""
        when (item.itemId) {
            R.id.menu_top_songs -> {
                feedUrl =
                    "http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/topsongs/limit=10/xml"
            }
            R.id.menu_paid_apps -> {
                feedUrl =
                    "http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/toppaidapplications/limit=25/xml"
            }
            R.id.menu_free_apps -> {
                feedUrl =
                    "http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/topfreeapplications/limit=10/xml"
            }

        }
        downloadURL(feedUrl)
        return true
    }
        fun downloadURL (url:String){
            downloadData = DownloadData(this,xmlListView!!)
            downloadData!!.execute(url)
        }


     class DownloadData(val context: Context,val xmlListView: ListView) : AsyncTask<String, Void, String>() {
        override fun doInBackground(vararg p0: String?): String {
            Log.d(TAG, "we call the url ${p0.toString()}")
            var xmlString: String = URL(p0[0]).readText()
            return xmlString
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            val parseapplication: Parseapp = Parseapp()
            var Feedlist = parseapplication.parse(result)

            val feedAdapter = FeedAdapter(context, R.layout.item_list, Feedlist)
            xmlListView.adapter=feedAdapter
            Log.d(TAG,"finished downloading url"+result)
        }

    }
}