package api;

import api.resources.ReviewResource;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class APIApplication extends Application<Configuration> {

    private static final Logger LOGGER = LoggerFactory.getLogger(APIApplication.class);

    public static void main(String[] args) throws Exception {
        new APIApplication().run(args);
    }

    @Override
    public String getName() {
        return "parser";
    }

    @Override
    public void initialize(Bootstrap<Configuration> bootstrap) {
        // nothing to do yet
    }

    public void run(Configuration c, Environment environment) throws Exception {

        final ReviewResource reviewResource = new ReviewResource(environment.getValidator());
        LOGGER.info("Registering REST resources");

        environment.jersey().register(reviewResource);
    }
}
