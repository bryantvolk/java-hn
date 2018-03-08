package api.resources;

import api.resources.representations.Review;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * mock db
 */
public class ReviewDB {

    public static HashMap<Integer, Review> reviews = new HashMap<Integer, Review>();
    static {
        reviews.put(1, new Review(1, "The Shape of Water", "Great"));
        reviews.put(2, new Review(2, "Starship Troopers", "Amazing"));
        reviews.put(3, new Review(3, "Kill Bill vol 1", "Samurai/10"));
    }

    public static List<Review> getReviews() {
        return new ArrayList<Review>(reviews.values());
    }

    public static Review getReview(Integer id) {
        return reviews.get(id);
    }

    public static void updateReview(Integer id, Review review) {
        reviews.put(id, review);
    }

    public static void removeReview(Integer id) {
        reviews.remove(id);
    }
}
