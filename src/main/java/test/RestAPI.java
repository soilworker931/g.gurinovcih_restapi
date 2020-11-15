package test;

import framework.forms.Posts;

public class RestAPI {

    public static void main(String[] args) {
        Posts post = new Posts("https://jsonplaceholder.typicode.com/posts");
        System.out.println(post.createNewPost().getHeaders("Location").toString() == "https://jsonplaceholder.typicode.com/posts/101");
    }
}