package com.example.top10downloader

class FeedEntry {
    var artist:String=""
    var summary:String=""
    var title:String=""

    override fun toString(): String {
        return """
             artist:$artist
             summary:$summary
             title:$title
         """.trimIndent()
    }

}