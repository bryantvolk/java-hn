package api.resources;

import api.jdbi.Review;
import api.services.ReviewService;
import com.codahale.metrics.annotation.Timed;
import org.eclipse.jetty.http.HttpStatus;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/reviews")
@Produces(MediaType.APPLICATION_JSON)
public class ReviewResource {
    private final ReviewService reviewService;

    public ReviewResource(ReviewService reviewService) {
        this.reviewService = reviewService;
    }


    @GET
    @Timed
    public Representation<List<Review>> getReviews() {
        return new Representation<List<Review>>(HttpStatus.OK_200, reviewService.getReviews());
    }

    @GET
    @Timed
    @Path("/{id}")
    public Representation<Review> getReview(@PathParam("id") final int id) {
        return new Representation<Review>(HttpStatus.OK_200, reviewService.getReview(id));
    }

    @POST
    @Timed
    public Representation<Review> createReview(@NotNull @Valid final Review review) {
        return new Representation<Review>(HttpStatus.OK_200, reviewService.createReview(review));
    }

    @DELETE
    @Timed
    @Path("/{id}")
    @RolesAllowed("ADMIN")
    public Representation<String> deleteReview(@PathParam("id") final int id) {
        return new Representation<String>(HttpStatus.OK_200, reviewService.deleteReview(id));
    }

}
