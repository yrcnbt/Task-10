package com.example.task10;

import com.example.task10.model.User;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class Consumer {
    static String url = "http://94.198.50.185:7081/api/users";
    static RestTemplate restTemplate = new RestTemplate();

    public static void main(String[] args) {

        ResponseEntity<List<User>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {
        });

        String setCookie = responseEntity.getHeaders().getFirst("Set-Cookie");
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", setCookie);

        User user = new User();
        user.setId(3L);
        user.setName("James");
        user.setLastName("Brown");
        user.setAge((byte) 25);

        HttpEntity<User> entity = new HttpEntity<>(user, headers);

        ResponseEntity<String> responsePost = restTemplate.exchange(
                "http://94.198.50.185:7081/api/users",
                HttpMethod.POST,
                entity,
                String.class);

        user.setName("Thomas");
        user.setLastName("Shelby");
        HttpEntity<User> entityForUpdate = new HttpEntity<>(user, headers);
        ResponseEntity<String> responseForUpdate = restTemplate.exchange(
                "http://94.198.50.185:7081/api/users",
                HttpMethod.PUT,
                entityForUpdate,
                String.class);

        ResponseEntity<String> responseForDelete = restTemplate.exchange(
                "http://94.198.50.185:7081/api/users/3",
                HttpMethod.DELETE,
                new HttpEntity<Object>(headers),
                String.class);

        System.out.println(responseEntity.getBody());
        System.out.println(responsePost.getBody() + responseForUpdate.getBody() + responseForDelete.getBody());


    }

}
