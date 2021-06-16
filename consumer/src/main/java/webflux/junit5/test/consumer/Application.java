package webflux.junit5.test.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

        DocumentWebClient gwc = new DocumentWebClient();
        System.out.println(gwc.getDocuments("http://localhost:8081"));
    }
}
