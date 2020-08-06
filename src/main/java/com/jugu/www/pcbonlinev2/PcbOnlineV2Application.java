package com.jugu.www.pcbonlinev2;

import com.jugu.www.pcbonlinev2.state.core.Initialization;
import com.jugu.www.pcbonlinev2.state.core.OrderStateManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@ServletComponentScan
public class PcbOnlineV2Application {

    public static void main(String[] args) {
        SpringApplication.run(PcbOnlineV2Application.class, args);
    }

    @Bean
    public Initialization initialization() {
        return new Initialization();
    }

    @Bean
    public OrderStateManager orderStateManager() {
        return new OrderStateManager();
    }

}
