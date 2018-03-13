package api.jdbi;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

@RegisterMapper(ReviewMapper.class)
public interface ReviewDao {

    @SqlQuery("select * from reviews;")
    public List<Review> getReviews();

    @SqlQuery("select * from reviews where id = :id")
    public Review getReview(@Bind("id") final int id);

    @SqlUpdate("insert into reviews(title, body, image, link, author) values(:title, :body, :image, :link, :author)")
    void createReview(@BindBean final Review review);

    @SqlUpdate("delete from review where id = :id")
    int deleteReview(@Bind("id") final int id);

    @SqlQuery("select last_insert_id();")
    public int lastInsertId();
}
