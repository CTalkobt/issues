package net.ctalkobt.issue.camel.seda.queuefull;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.Logger;
import org.apache.camel.builder.AggregationStrategies;
import org.apache.camel.builder.RouteBuilder;
import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Component;

@Component
public class Routes extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        final Logger logger = LogManager.getLogger();

        from("seda://incoming")
                .routeId("incoming")
                .aggregate(simple("${body.id}"), AggregationStrategies.flexible().accumulateInCollection(ArrayList.class) )
                .completionSize(100)
                .completionTimeout(500)
                .process(exch -> logger.info("incoming 1:" + exch.getIn().getBody()))
                .to("seda://part2");

        from("seda://part2")
                .aggregate(constant(1), AggregationStrategies.flexible().accumulateInCollection(ArrayList.class))                
                    .completionTimeout(500)
                    .completionSize(500)
                .process(exch -> logger.info("Got : " + exch.getIn().getBody(List.class).size()) );
    }

}
