import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class RedirectProcessor {
    public void checkMultipleRedirects(List<String> urls) {
        for (String url : urls) {
            checkRedirect(url);
        }
    }

    public void checkRedirect(String url) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setInstanceFollowRedirects(true);
            connection.setRequestMethod("HEAD");
            connection.connect();

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("Link: " + url + " Not Redirected");
            } else if (responseCode == HttpURLConnection.HTTP_MOVED_PERM
                    || responseCode == HttpURLConnection.HTTP_MOVED_TEMP
                    || responseCode == HttpURLConnection.HTTP_SEE_OTHER) {
                String redirectURL = connection.getHeaderField("Location");
                System.out.println("Link " + url + " redirects to: " + redirectURL);
            }

            connection.disconnect();

        } catch (IOException e) {
            System.out.println("Error occurred after checking link " + url + ": " + e.getMessage());
        }
    }
}
