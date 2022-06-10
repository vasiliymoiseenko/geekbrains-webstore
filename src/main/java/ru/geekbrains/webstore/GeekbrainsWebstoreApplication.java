package ru.geekbrains.webstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class GeekbrainsWebstoreApplication {

  public static void main(String[] args) {
    SpringApplication.run(GeekbrainsWebstoreApplication.class, args);
  }

}
