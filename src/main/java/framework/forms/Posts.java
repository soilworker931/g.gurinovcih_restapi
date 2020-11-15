package framework.forms;

import com.google.gson.Gson;

import framework.base.BaseForm;
import framework.models.Post;
import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class Posts {

    private BaseForm posts = new BaseForm();
    private final String url;
    private final int id = 99;
    private final int userId = 10;
    private Post post = new Post("title", "body", 1);

    public Posts(String url) {
        this.url = url;
    }

    public int getResponseCode() {
        return posts.getResponseCode(url);
    }

    public boolean checkOrder() {
        JSONArray jsonArray = new JSONArray(posts.getJsonBody(url));
        boolean check = false;
        for (int i = 1; i < jsonArray.length(); i++) {
            if (jsonArray.getJSONObject(i).optInt("id") > jsonArray.getJSONObject(i - 1).optInt("id")) {
                return check = true;
            } else {
                return check = false;
            }
        }
        return check;
    }

    public boolean checkText() {
        JSONObject jsonObject = new JSONObject(posts.getJsonBody(url));
        boolean check = false;
        if (jsonObject.getInt("id") == id && jsonObject.getInt("userId") == userId){
            return check = true;
        } else {
            return check = false;
        }
    }

    public boolean isBodyEmpty() {
        boolean check = true;
        if (posts.getJsonBody(url).isEmpty()) {
            return check = true;
        } else {
            return check = false;
        }
    }

    public HttpResponse createNewPost() {
        HttpResponse response = null;
        boolean check = false;
        try {
            Gson gson = new Gson();
            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpPost httpPost = new HttpPost(url);
            StringEntity postingString = new StringEntity(gson.toJson(post));
            httpPost.setEntity(postingString);
            httpPost.setHeader("Content-type", "application/json");
            response = httpClient.execute(httpPost);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
//        if (response.getLastHeader("Location").getValue() == "http://jsonplaceholder.typicode.com/posts/101" && response.getStatusLine().getStatusCode() == 201) {
//            check = true;
//        } else {
//            check = false;
//        }
//        return check;
    }
}