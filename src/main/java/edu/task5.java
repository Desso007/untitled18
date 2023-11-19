package edu;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class task5 {

    public static long[] hackerNewsTopStories() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://hacker-news.firebaseio.com/v0/topstories.json"))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String responseBody = response.body();
            // Преобразование JSON в long[]
            String[] idsStringArray = responseBody.substring(1, responseBody.length() - 1).split(",");
            long[] ids = new long[idsStringArray.length];
            for (int i = 0; i < idsStringArray.length; i++) {
                ids[i] = Long.parseLong(idsStringArray[i].trim());
            }
            return ids;
        } catch (Exception e) {
            e.printStackTrace();
            return new long[0];
        }
    }

    public static String news(long id) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://hacker-news.firebaseio.com/v0/item/" + id + ".json"))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String responseBody = response.body();


            Pattern pattern = Pattern.compile("\"title\":\"([^\"]+)\"");
            Matcher matcher = pattern.matcher(responseBody);

            if (matcher.find()) {
                return matcher.group(1);
            } else {
                return "Title not found";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error fetching news";
        }
    }

    public static void main(String[] args) {
        long[] topStories = hackerNewsTopStories();
        System.out.println(Arrays.toString(topStories));

        long newsId = 37570037;
        String newsTitle = news(newsId);
        System.out.println(newsTitle);
    }
}
