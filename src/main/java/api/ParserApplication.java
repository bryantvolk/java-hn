package api;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class ParserApplication extends Application<ParserConfiguration> {

    public static void main(String[] args) throws Exception {
        new ParserApplication().run(args);
    }

    @Override
    public String getName() {
        return "parser";
    }

    @Override
    public void initialize(Bootstrap<ParserConfiguration> bootstrap) {
        // nothing to do yet
    }

    public void run(ParserConfiguration parserConfiguration, Environment environment) throws Exception {
        final HelloWorldResource resource = new HelloWorldResource(
                parserConfiguration.getTemplate(),
                parserConfiguration.getDefaultName()
        );
        environment.jersey().register(resource);
    }
}
