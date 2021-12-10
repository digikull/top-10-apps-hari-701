package com.example.top10downloader

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import java.security.AccessControlContext

class FeedAdapter(context: Context, resource:Int, val FeedEntryList:ArrayList<FeedEntry>):ArrayAdapter<FeedEntry>(context, resource){
    override fun getCount(): Int {
        return FeedEntryList.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var inflater=LayoutInflater.from(context)
        var view:View= inflater.inflate(R.layout.item_list,parent,false)

    val tvartist=view.findViewById<TextView>(R.id.tvartist)
    val tvsummary=view.findViewById<TextView>(R.id.tvsummary)
    val tvtitle=view.findViewById<TextView>(R.id.tvtitle)

        tvartist.text =FeedEntryList[position].artist
        tvsummary.text =FeedEntryList[position].summary
        tvtitle.text =FeedEntryList[position].title

        return view
    }
}