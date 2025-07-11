package org.example.springbootpodcast;

import jakarta.persistence.EntityManagerFactory;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.annotation.RequestScope;

@SpringBootApplication
public class SpringbootPodcastApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootPodcastApplication.class, args);
    }

    @Bean @RequestScope
    public static StatelessSession statelessSession(EntityManagerFactory entityManagerFactory) {
        return entityManagerFactory.unwrap(SessionFactory.class).openStatelessSession();
    }
}
