package Assignment2;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.LinkedList;


public class scrapeGitHub {
    private static String full(Reader reader) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        int cp;

        while ((cp = reader.read()) != -1) {
            stringBuilder.append((char) cp);
        }
        return stringBuilder.toString();
    }

    public static JSONArray readUrl(String url) throws IOException, JSONException {
        InputStream inputStream = new URL(url).openStream();
        try {
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("| %-5s| %-20s| %-20s| %-20s| %-20s| %-20s\n","No","Follower Name","Total Repo","Total Follower","Total Following","GitHub Link");
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
            String text = full(reader);
            JSONArray jsonArray = new JSONArray(text);
            return jsonArray;
        } finally {
            inputStream.close();
        }
    }

    public static JSONObject readOneUrl(String url) throws IOException, JSONException {
        InputStream inputStream = new URL(url).openStream();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
            String text = full(reader);
            JSONObject json = new JSONObject(text);
            return json;
        } finally {
            inputStream.close();
        }

    }

    public static LinkedList<data> findAll() throws IOException, JSONException {
        int total = 0;
        int totalRepo = 0;
        String trying = null;
        LinkedList<data> data = new LinkedList<data>();

        JSONArray jsonArray = readUrl("https://api.github.com/users/zhamri/followers");

        int length = 0;

        if(jsonArray.length() > 30){
             length = 30;
        }else {
            length = jsonArray.length();
        }

        for (int i = 0; i < length; i++) {

            JSONObject jsonObject = jsonArray.getJSONObject(i);

            String link = jsonObject.optString("url");

            JSONObject jsonObject1 = readOneUrl(link);


            String login = jsonObject1.optString("login");
            String t_Repo = jsonObject1.optString("public_repos");
            String t_Followers = jsonObject1.optString("followers");
            String t_Following = jsonObject1.optString("following");
            String githubLink = jsonObject1.optString("html_url");

            Thread thread = new Thread(() -> {
                //System.out.println("t-"+Thread.currentThread().getId()+" "+login + " " + t_Repo + " " + t_Followers + " " + t_Following + " " + githubLink);
                System.out.printf("| %-5s",Thread.currentThread().getId());
                System.out.printf("| %-20s",login);
                System.out.printf("| %-20s",t_Repo);
                System.out.printf("| %-20s",t_Followers);
                System.out.printf("| %-20s",t_Following);
                System.out.printf("| %-20s",githubLink+"\n");
                data.add(new data(login, t_Repo, t_Followers, t_Following, githubLink));
            });

            thread.start();

            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }


        return data;
    }
}

