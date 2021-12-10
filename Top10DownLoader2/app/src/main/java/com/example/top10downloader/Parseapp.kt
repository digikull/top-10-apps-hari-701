package com.example.top10downloader

import android.util.Log
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory

class Parseapp {
    val xmlString: String = ""
    var TAG: String = "Parseapp"
    var FeedEntry: ArrayList<FeedEntry> = ArrayList()

    fun parse(xmlString: String?):ArrayList<FeedEntry> {
        val factory = XmlPullParserFactory.newInstance()
        factory.isNamespaceAware = true
        val xmlParser = factory.newPullParser()
        xmlParser.setInput(xmlString!!.reader())

        var textValue: String = ""
        var FeedEntry: FeedEntry = FeedEntry()
        var FeedEntryList: ArrayList<FeedEntry> = ArrayList()

        var eventType = xmlParser.eventType
        while (eventType != XmlPullParser.END_DOCUMENT) {
           Log.d(TAG, "I encountered" + eventType.toString())
            when (eventType) {
                XmlPullParser.START_TAG -> {
                    val name = xmlParser.name.toLowerCase()
                    if (name == "entry") {
                        FeedEntry = FeedEntry()
                    }
                }
                XmlPullParser.TEXT -> {
                    textValue = xmlParser.text
                }
                XmlPullParser.END_TAG -> {
                    val tagName = xmlParser.name.toLowerCase()
                    when (tagName) {
                        "artist" -> FeedEntry.artist = textValue
                        "summary" -> FeedEntry.summary = textValue
                        "title" -> FeedEntry.title = textValue
                        "entry" -> {
                            FeedEntryList.add(FeedEntry)

                        }


                    }
                }
            }
            eventType = xmlParser.next()
        }
        return FeedEntryList
    }
}