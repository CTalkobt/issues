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
    @Autowired
    private ProducerTemplate producerTemplate;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {

        for (int i = 1; i < 20000; i++) {
            User u = new User("id"+i, "first"+i, "last"+i);
LogManager.getLogger().info("Writing : " + u);
            producerTemplate.sendBody("seda://incoming&defaultBlockWhenFull=true&defaultDiscardWhenFull=false&defaultOfferTimeout=-1",  u);
        }
    }



}
