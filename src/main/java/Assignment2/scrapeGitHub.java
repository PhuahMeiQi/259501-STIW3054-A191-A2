package Assignment2;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.LinkedList;

class scrapeData extends Thread {

    public void run() {
        System.out.println("info");
    }

}



public class scrapeGitHub {
    //URLConnection conn = url.openConnection();
    //conn.setRequestProperty("User-Agent","Mozilla/5.0");
    //HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        //connection.connect();



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
            //HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            //connection.connect();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
            String text = full(reader);
            JSONArray jsonArray = new JSONArray(text);
            return jsonArray;
        }finally {
            inputStream.close();
        }

    }

    public static LinkedList<data> findAll() throws IOException, JSONException{
        LinkedList<data> data = new LinkedList<data>();

        int total = 0;
        int totalRepo = 0;
        String trying = null;

        JSONArray jsonArray = readUrl("https://api.github.com/users/PhuahMeiQi/followers");

        for (int i = 0; i<jsonArray.length();i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String follower = jsonObject.optString("login");
            String link = jsonObject.optString("followers_url");
            String repo = jsonObject.optString("repos_url");
            String following = jsonObject.optString("subscriptions_url");
            String URL = jsonObject.optString("html_url");
            JSONArray jsonArray1 = readUrl(link);
            JSONArray jsonArray2 = readUrl(repo);
            JSONArray jsonArray3 = readUrl(following);

            for (int a = 0; a<jsonArray1.length();a++){
                JSONObject jsonObject1 = jsonArray1.getJSONObject(a);
                jsonObject1.optString("login");
                total = 1 + a++;
            }

            for (int b = 0; b<jsonArray2.length();b++){
                JSONObject jsonObject2 = jsonArray2.getJSONObject(b);
                jsonObject2.optString("id");
                totalRepo = 1 + b++;
            }

            for (int c = 0; c<jsonArray1.length();c++){
                JSONObject jsonObject3 = jsonArray3.getJSONObject(c);
                jsonObject3.optString("name");
            }

            data.add(new data(follower,totalRepo,total,trying,URL));
        }

        return data;
    }
   /* public static void main(String[] args) {
        scrapeData t1 = new scrapeData();
        scrapeData t2 = new scrapeData();
        System.out.println("testing");


        t1.start();
        t2.start();
    }*/
}