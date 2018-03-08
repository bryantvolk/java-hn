package api.resources;

import api.resources.representations.Review;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.validation.Validator;

@Path("/reviews")
@Produces(MediaType.APPLICATION_JSON)
public class ReviewResource {
    private final Validator validator;

    public ReviewResource(Validator validator) {
        this.validator = validator;
    }

    @GET
    public Response getReviews() {
        return Response.ok(ReviewDB.getReviews()).build();
    }

    @GET
    @Path("/{id}")
    public Response getReviewById(@PathParam("id") Integer id) {
        Review review = ReviewDB.getReview(id);
        if (review != null)
            return Response.ok(review).build();
        else
            return Response.status(Response.Status.NOT_FOUND).build();
    }

}
