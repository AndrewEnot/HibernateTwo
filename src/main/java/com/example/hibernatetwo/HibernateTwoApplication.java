package com.example.hibernatetwo;

import com.example.hibernatetwo.controller.OrderConsoleController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class HibernateTwoApplication {

  private final OrderConsoleController orderConsoleController;

  @Autowired
  public HibernateTwoApplication(OrderConsoleController orderConsoleController) {
    this.orderConsoleController = orderConsoleController;
  }

  public static void main(String[] args) {
    SpringApplication.run(HibernateTwoApplication.class, args);
  }

  @EventListener(ApplicationReadyEvent.class)
  public void onInit() {
    orderConsoleController.start();
  }
}
