package net.ctalkobt.issue.camel.seda.queuefull;

import net.ctalkobt.issue.camel.seda.queuefull.model.User;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CamelConfig {

    @Bean(name = "userBlockingQueue")
    public BlockingQueue<User> myBlockingQueue() {
        return new LinkedBlockingDeque<>(200);
    }
}
