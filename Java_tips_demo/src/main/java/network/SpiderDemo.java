package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class SpiderDemo {

    public static void main(String[] args) {
        System.out.println(getContent("https://www.zhihu.com"));
    }

    private static String getContent(String url) {
        StringBuilder result = new StringBuilder();
        BufferedReader in = null;
        try {
            // Convert String to URL
            URL realURL = new URL(url);
            // Init a connection
            URLConnection connection = realURL.openConnection();
            // connect
            connection.connect();
            // Init a BufferedReader to read to content from connection
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String line;
            while ((line = in.readLine()) != null) {
                result.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return result.toString();
    }
}
