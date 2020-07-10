package com.jugu.www.pcbonlinev2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class PcbOnlineV2Application {

    public static void main(String[] args) {
        SpringApplication.run(PcbOnlineV2Application.class, args);
    }

}
