package net.ctalkobt.issue.camel.seda.queuefull;

import net.ctalkobt.issue.camel.seda.queuefull.model.User;
import org.apache.camel.ProducerTemplate;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class TestStartup implements ApplicationListener<ApplicationReadyEvent> {
    private final boolean useSolution = true;
    
    @Autowired
    private ProducerTemplate producerTemplate;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {

        for (int i = 1; i <= 20000; i++) {
            User u = new User("id"+i, "first"+i, "last"+i);
            if (useSolution) {
                producerTemplate.sendBody("seda://incoming?blockWhenFull=true&offerTimeout=500000",  u);                
            } else {
                producerTemplate.sendBody("seda://incoming",  u); /* Problem statement */
            }
        }
    }



}
