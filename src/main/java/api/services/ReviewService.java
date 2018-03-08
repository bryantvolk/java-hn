package api.services;

import api.jdbi.Review;
import api.jdbi.ReviewDao;
import org.skife.jdbi.v2.exceptions.UnableToExecuteStatementException;
import org.skife.jdbi.v2.exceptions.UnableToObtainConnectionException;
import org.skife.jdbi.v2.sqlobject.CreateSqlObject;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Objects;

public abstract class ReviewService {
    public static final String REVIEW_NOT_FOUND = "Review id %s not found";
    public static final String DATABASE_REACH_ERROR = "Could not reach DB\n details: ";
    public static final String DATABASE_CONNECTION_ERROR = "DB connection error\n details: ";
    public static final String DATABASE_UNEXPECTED_ERROR = "Unexpected error\n details: ";
    public static final String SUCCESS = "Success...";
    public static final String UNEXPECTED_ERROR = "Unexpected error occured";

    @CreateSqlObject
    abstract ReviewDao reviewDao();

    public List<Review> getReviews() {
        return reviewDao().getReviews();
    }

    public Review getReview(int id) {
        Review review = reviewDao().getReview(id);
        if (Objects.isNull(review)) {
            throw new WebApplicationException(String.format(REVIEW_NOT_FOUND, id), Response.Status.NOT_FOUND);
        }
        return review;
    }

    public Review createReview(Review review) {
        reviewDao().createReview(review);
        return reviewDao().getReview(reviewDao().lastInsertId());
    }

    public String deleteReview(final int id) {
        int result = reviewDao().deleteReview(id);
        switch (result) {
            case 1:
                return SUCCESS;
            case 0:
                throw new WebApplicationException(String.format(REVIEW_NOT_FOUND, id), Response.Status.NOT_FOUND);
            default:
                throw new WebApplicationException(UNEXPECTED_ERROR, Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    public String performHealthCheck() {
        try {
            reviewDao().getReviews();
        } catch (UnableToObtainConnectionException ex) {
            return checkUnableToObtainConnectionException(ex);
        } catch (UnableToExecuteStatementException ex) {
            return checkUnableToExecuteStatementException(ex);
        } catch (Exception ex) {
            return DATABASE_UNEXPECTED_ERROR + ex.getCause().getLocalizedMessage();
        }
        return null;
    }

    private String checkUnableToObtainConnectionException(UnableToObtainConnectionException ex) {
        if (ex.getCause() instanceof java.sql.SQLNonTransientConnectionException) {
            return DATABASE_REACH_ERROR + ex.getCause().getLocalizedMessage();
        } else if (ex.getCause() instanceof java.sql.SQLException) {
            return DATABASE_CONNECTION_ERROR + ex.getCause().getLocalizedMessage();
        } else {
            return DATABASE_UNEXPECTED_ERROR + ex.getCause().getLocalizedMessage();
        }
    }

    private String checkUnableToExecuteStatementException(UnableToExecuteStatementException ex) {
        if (ex.getCause() instanceof java.sql.SQLSyntaxErrorException) {
            return DATABASE_CONNECTION_ERROR + ex.getCause().getLocalizedMessage();
        } else {
            return DATABASE_UNEXPECTED_ERROR + ex.getCause().getLocalizedMessage();
        }
    }
}
