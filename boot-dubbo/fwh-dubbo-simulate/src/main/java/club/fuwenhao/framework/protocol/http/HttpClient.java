package club.fuwenhao.framework.protocol.http;

import com.alibaba.fastjson.JSONObject;
import club.fuwenhao.framework.Invocation;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpClient {

    public String send(String hostname, Integer port, Invocation invocation) {

        try {

            var request = HttpRequest.newBuilder()
                    .uri(new URI("http", null, hostname, port, "/", null, null))
                    .POST(HttpRequest.BodyPublishers.ofString(JSONObject.toJSONString(invocation)))
                    .build();
            var client = java.net.http.HttpClient.newHttpClient();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            String result = response.body();

            // JDK11之前用
//            URL url = new URL("http", hostname, port, "/");
//            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
//
//            httpURLConnection.setRequestMethod("POST");
//            httpURLConnection.setDoOutput(true);
//
//            OutputStream outputStream = httpURLConnection.getOutputStream();
//            ObjectOutputStream oos = new ObjectOutputStream(outputStream);
//
//            oos.writeObject(invocation);
//            oos.flush();
//            oos.close();
//
//            InputStream inputStream = httpURLConnection.getInputStream();
//            String result = IOUtils.toString(inputStream);
            return result;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return null;

    }
}
