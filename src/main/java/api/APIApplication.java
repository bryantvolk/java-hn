package api;

import api.resources.ReviewResource;
import api.services.ReviewService;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;

public class APIApplication extends Application<ReviewConfiguration> {
    private static final String SQL = "sql";
    private static final String DROPWIZARD_REVIEW_SERVICE = "Dropwizard review service";
    private static final String BEARER = "Bearer";
    private static final Logger LOGGER = LoggerFactory.getLogger(APIApplication.class);

    public static void main(String[] args) throws Exception {
        new APIApplication().run(args);
    }

    @Override
    public String getName() {
        return "parser";
    }

    public void run(ReviewConfiguration c, Environment environment) throws Exception {
        // datasource config
        final DataSource dataSource = c.getDataSourceFactory().build(environment.metrics(), SQL);
        DBI dbi = new DBI(dataSource);

        // register health check
        ReviewHealthCheck healthCheck = new ReviewHealthCheck(dbi.onDemand(ReviewService.class));
        environment.healthChecks().register(DROPWIZARD_REVIEW_SERVICE, healthCheck);

        LOGGER.info("Registering REST resources");
        environment.jersey().register(new ReviewResource(dbi.onDemand(ReviewService.class)));
    }
}
