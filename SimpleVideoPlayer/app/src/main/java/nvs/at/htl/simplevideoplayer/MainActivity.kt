package nvs.at.htl.simplevideoplayer

import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.VideoView
import kotlinx.android.synthetic.main.activity_main.*
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val video = findViewById(R.id.videoView) as VideoView
        video.setVideoURI(Uri.parse("https://streamsrv54.feratel.co.at/streams/1/05132_5bb880f8-c727Vid.mp4?dcsdesign=feratel4"))
        video.start()
    }

     /*fun getVideo() : String{
            var doc: Document
            var video : Element
            var source : String
            doc = Jsoup.connect("https://webtv.feratel.com/webtv/?cam=5132&design=v3&c0=0&c2=1&lg=en&s=0").get()
            video = doc.getElementById("fer_video")
            source = video.select("source").first().attr("src")
            return source
    }*/
}
