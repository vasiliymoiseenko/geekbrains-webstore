package ru.geekbrains.webstore.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("/secret.properties")
public class CoreApp {

  public static void main(String[] args) {
    SpringApplication.run(CoreApp.class, args);
  }

}
