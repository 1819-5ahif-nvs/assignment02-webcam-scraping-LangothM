package at.htl.nvs;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;

public class Main {
    public static void main(String args[]) throws IOException, InterruptedException{
        BufferedWriter writer = new BufferedWriter( new FileWriter("log.txt"));
        for(;;){
            System.out.println(getVideoSource());
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            writer.write(timestamp + "," + getVideoSource());
            writer.flush();
            writer.newLine();
            Thread.sleep(5000);
        }
    }

    public static String getVideoSource() {
        Document doc = null;
        Element video = null;
        String source = null;
        try{
            doc = Jsoup.connect("https://webtv.feratel.com/webtv/?cam=5132&design=v3&c0=0&c2=1&lg=en&s=0").get();
            video = doc.getElementById("fer_video");
            source = video.select("source").first().attr("src");
        }catch(IOException e){}
        return source;
    }
}
