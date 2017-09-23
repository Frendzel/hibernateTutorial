package pl.lodz.sda.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import pl.lodz.sda.environment.DB;
import pl.lodz.sda.tools.HibernateSessionFactory;

import javax.annotation.PreDestroy;
import java.util.Arrays;

@SpringBootApplication
public class SpringBootApp {

    public static void main(String[] args) {
        System.getProperties().put("server.port", 8181);
        HibernateSessionFactory hibernateSessionFactory =
                new HibernateSessionFactory(DB.H2);
        ConfigurableApplicationContext run = SpringApplication.run(
                SpringBootApp.class, args);
    }

    @PreDestroy
    public void log(){
        System.out.println("We need to close hibernate sessionf factory");
        HibernateSessionFactory.closeSessionFactory();
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            System.out.println("Let's inspect the beans provided by Spring Boot:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }

        };
    }

}
