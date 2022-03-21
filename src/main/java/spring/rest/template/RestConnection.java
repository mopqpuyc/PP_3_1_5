package spring.rest.template;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import spring.rest.template.entity.User;

import java.util.List;

@Component
public class RestConnection {

    private RestTemplate restTemplate;
    private final String apiUrl = "http://94.198.50.185:7081/api/users";
    private StringBuilder code = new StringBuilder();

    @Autowired
    public RestConnection(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public StringBuilder getCode() {
        return code;
    }

    public List<String> getAllUsers(HttpEntity<User> requestEntity) {
        ResponseEntity<String> responseEntity = restTemplate.exchange(apiUrl,
                HttpMethod.GET,
                requestEntity,
                String.class);
        System.out.println(responseEntity.getStatusCode());
        System.out.println(responseEntity.getBody());
        return responseEntity.getHeaders().get("Set-Cookie");
    }

    public void addUser(HttpEntity<User> requestEntity) {
        ResponseEntity<String> responseEntity = restTemplate.exchange(apiUrl,
                HttpMethod.POST,
                requestEntity,
                String.class);
        System.out.println(responseEntity.getStatusCode());
        System.out.println(responseEntity.getBody());
        code.append(responseEntity.getBody());
    }

    public void editUser(HttpEntity<User> requestEntity) {
        ResponseEntity<String> responseEntity = restTemplate.exchange(apiUrl,
                HttpMethod.PUT,
                requestEntity,
                String.class);
        System.out.println(responseEntity.getStatusCode());
        System.out.println(responseEntity.getBody());
        code.append(responseEntity.getBody());
    }

    public void deleteUser(HttpEntity<User> requestEntity,int id) {
        ResponseEntity<String> responseEntity = restTemplate.exchange(apiUrl + "/" + id,
                HttpMethod.DELETE,
                requestEntity,
                String.class);
        System.out.println(responseEntity.getStatusCode());
        System.out.println(responseEntity.getBody());
        code.append(responseEntity.getBody());
    }
}
