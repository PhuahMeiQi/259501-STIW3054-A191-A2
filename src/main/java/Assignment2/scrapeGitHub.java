package Assignment2;


import org.json.JSONArray;
import org.json.JSONException;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;

class scrapeData extends Thread {

    public void run() {
        System.out.println("info");
    }

}


public class scrapeGitHub {

    private static String full (Reader reader) throws IOException{
        StringBuilder stringBuilder = new StringBuilder();
        int cp;

        while ((cp = reader.read()) != -1){
            stringBuilder.append((char) cp);
        }
        return stringBuilder.toString();
    }

    public static JSONArray readUrl (String url) throws IOException, JSONException {
        InputStream inputStream = new URL(url).openStream();
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
            String text = full(reader);
            JSONArray jsonArray = new JSONArray(text);
            return jsonArray;
        }finally {
            inputStream.close();
        }

    }
    public static void main(String[] args) {
        scrapeData t1 = new scrapeData();
        scrapeData t2 = new scrapeData();
        System.out.println("testing");


        t1.start();
        t2.start();
    }
}