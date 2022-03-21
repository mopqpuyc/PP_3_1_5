package spring.rest.template;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import spring.rest.template.configuration.AppConfig;
import spring.rest.template.entity.User;

import java.util.List;

public class App 
{
    public static void main( String[] args )
    {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        RestConnection connection = context.getBean("restConnection", RestConnection.class);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        List<String> cookies = connection.getAllUsers(new HttpEntity<>(httpHeaders));
        System.out.println(cookies);

        httpHeaders.set("Cookie", String.join(";", cookies));

        User newUser = new User(3L, "James", "Brown", (byte)30);

        HttpEntity<User> httpEntity = new HttpEntity<>(newUser, httpHeaders);

        connection.addUser(httpEntity);

        newUser.setName("Thomas");
        newUser.setLastName("Shelby");

        connection.editUser(httpEntity);

        connection.deleteUser(httpEntity, 3);

        System.out.println(connection.getCode());
    }
}
