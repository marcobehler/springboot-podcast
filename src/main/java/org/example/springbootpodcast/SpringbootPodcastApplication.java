package org.example.springbootpodcast;

import jakarta.persistence.EntityManagerFactory;
//import org.example.springbootpodcast.repository.PlusGuideRepository;
//import org.example.springbootpodcast.repository.PlusGuideRepository_;
import org.hibernate.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;

@SpringBootApplication
public class SpringbootPodcastApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootPodcastApplication.class, args);
    }

//    @Bean
//    public PlusGuideRepository plusGuideRepository(StatelessSession statelessSession) {
//        return new PlusGuideRepository_(statelessSession);
//    }

    @Bean
    public static StatelessSession statelessSession(EntityManagerFactory entityManagerFactory) {
        return (StatelessSession)
                Proxy.newProxyInstance(StatelessSession.class.getClassLoader(),
                        new Class[]{StatelessSession.class},
                        // Since Spring just doesn't seem to have a reasonably clean
                        // way to inject a transaction-scoped bean into a service,
                        // we'll just open a new SS for every operation.
                        // (That's really not perfect, but it works.)
                        (proxy, method, args) -> {
                            if ( method.getName().equals("close") ) {
                                return null;
                            }
                            else {
                                return entityManagerFactory.unwrap(SessionFactory.class)
                                        .fromStatelessSession(session -> {
                                            try {
                                                return method.invoke(session, args);
                                            }
                                            catch (IllegalAccessException | InvocationTargetException e) {
                                                throw new RuntimeException(e);
                                            }
                                        });
                            }
                        });
    }
}
