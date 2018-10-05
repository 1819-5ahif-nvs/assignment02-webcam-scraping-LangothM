package at.htl.nvs;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


public class Main{
    public static void main(String args[]) throws Exception{
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/video",new VideoHandler());
        server.createContext("/videosrc",new SourceHandler());
        server.setExecutor(null);
        server.start();
    }
    public static String VideoSource() {
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

    static class VideoHandler implements HttpHandler {
        public void handle(HttpExchange t) throws IOException {
            String response = "<video controls autoplay>\n" +
                    "  <source src=\"" + VideoSource() +"\" type=\"video/mp4\">\n" +
                    "  Your browser does not support the video tag.\n" +
                    "</video>";
            Headers h = t.getResponseHeaders();
            h.set("Content-Type", "text/html");
            t.sendResponseHeaders(200,response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

    static class SourceHandler implements  HttpHandler {
        public void handle(HttpExchange t) throws IOException {
            String response = VideoSource();
            t.sendResponseHeaders(200,response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

}
