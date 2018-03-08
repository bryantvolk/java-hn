package api;

import api.services.ReviewService;
import com.codahale.metrics.health.HealthCheck;

public class ReviewHealthCheck extends HealthCheck {
    private static final String HEALTHY = "The Review Service is healthy for read and write";
    private static final String UNHEALTHY = "The Review Service is not healthy. ";
    private static final String MESSAGE_PLACEHOLDER = "{}";

    private final ReviewService reviewService;

    public ReviewHealthCheck(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    protected Result check() throws Exception {
        String mySqlHealthStatus = reviewService.performHealthCheck();

        if (mySqlHealthStatus == null) {
            return Result.healthy(HEALTHY);
        } else {
            return Result.unhealthy(UNHEALTHY + MESSAGE_PLACEHOLDER, mySqlHealthStatus);
        }
    }
}
