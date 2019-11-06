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
            System.out.printf("| %-5s| %-15s| %-15s| %-15s| %-15s| %-15s\n","No.","Login ID","Number of Repositories","Number of Followers","Number of Following","GitHub Link");
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

        LinkedList<data> data = new LinkedList<data>();

        JSONArray jsonArray = readUrl("https://api.github.com/users/zhamri/followers?access_token=3d1ca6930b12cb3704d6d6712a759300b0d0eb06");

        for (int i = 0; i < jsonArray.length(); i++) {

            JSONObject jsonObject = jsonArray.getJSONObject(i);

            String link = jsonObject.optString("url");

            JSONObject jsonObject1 = readOneUrl(link+"?access_token=3d1ca6930b12cb3704d6d6712a759300b0d0eb06");


            String login = jsonObject1.optString("login");
            String t_Repo = jsonObject1.optString("public_repos");
            String t_Followers = jsonObject1.optString("followers");
            String t_Following = jsonObject1.optString("following");
            String githubLink = jsonObject1.optString("html_url");

            Thread thread = new Thread(() -> {
                System.out.printf("| %-5s",Thread.currentThread().getName());
                System.out.printf("| %-15s",login);
                System.out.printf("| %-22s",t_Repo);
                System.out.printf("| %-19s",t_Followers);
                System.out.printf("| %-19s",t_Following);
                System.out.printf("| %-20s",githubLink+"\n");
                data.add(new data(login, t_Repo, t_Followers, t_Following, githubLink));
            });

            thread.setName(String.valueOf(i+1));
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

