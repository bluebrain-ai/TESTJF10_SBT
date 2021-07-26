package com.bluescript.demo;

import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class TESTJF10Main {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(TESTJF10Main.class, args);

        // Scanner scan = new Scanner(System.in);
        // scan.nextLine();

        Testjf10 testj10 = context.getBean(Testjf10.class);
        testj10.m0000MainModule();

    }

}
