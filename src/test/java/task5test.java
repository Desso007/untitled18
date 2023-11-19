import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class task5test {

    // Метод для получения массива идентификаторов популярных новостей Hacker News
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
}