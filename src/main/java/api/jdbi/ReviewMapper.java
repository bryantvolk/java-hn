package api.jdbi;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReviewMapper implements ResultSetMapper<Review> {
    private static final String ID = "id";
    private static final String TITLE = "title";
    private static final String BODY = "body";
    private static final String TIMESTAMP = "timestamp";
    private static final String IMAGE = "image";
    private static final String LINK = "link";
    private static final String AUTHOR = "author";

    public Review map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {
        return new Review(resultSet.getInt(ID), resultSet.getString(TITLE), resultSet.getString(BODY),
                resultSet.getString(TIMESTAMP), resultSet.getString(IMAGE), resultSet.getString(LINK),
                resultSet.getString(AUTHOR));
    }
}
