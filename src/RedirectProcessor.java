import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * The RedirectProcessor class is responsible for checking multiple URLs for redirections.
 */
public class RedirectProcessor {
    /**
     * The checkMultipleRedirects method is used to checking URL from list.
     * @param urls The list of URLs
     */
    public void checkMultipleRedirects(List<String> urls) {
        for (String url : urls) {
            checkRedirect(url);
        }
    }

    /**
     * Checking single url for redirection
     * @param url THe URL to be checked
     */
    public void checkRedirect(String url) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection(); //Creating new object and connection to URL. Casting HTTP type.
            connection.setInstanceFollowRedirects(true); // setting flag for true indicating that connection should fallow redirects and handle them autmatically.
            connection.setRequestMethod("HEAD"); //HTTP response header, not content.
            connection.connect(); //establish connection

            int responseCode = connection.getResponseCode(); //new object for response code

            if (responseCode == HttpURLConnection.HTTP_OK) { //if response (200)
                System.out.println("Link: " + url + " Not Redirected");
            } else if (responseCode == HttpURLConnection.HTTP_MOVED_PERM
                    || responseCode == HttpURLConnection.HTTP_MOVED_TEMP //Check other possibilities
                    || responseCode == HttpURLConnection.HTTP_SEE_OTHER) {
                String redirectURL = connection.getHeaderField("Location"); //redirect URL
                System.out.println("Link " + url + " redirects to: " + redirectURL);
            }

            connection.disconnect(); //closing connection

        } catch (IOException e) {
            System.out.println("Error occurred after checking link " + url + ": " + e.getMessage());
        }
    }
}
