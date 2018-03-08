package api.jdbi;

import org.hibernate.validator.constraints.NotEmpty;

public class Review {
    private int id;
    @NotEmpty
    private String title;
    @NotEmpty
    private String body;


    public Review() {
        super();
    }

    public Review(int  id, String title, String body) {
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public String getTitle() {return title;}
    public void setTitle() {this.title = title;}
    public String getBody() {return body;}
    public void setBody(String body) {this.body = body;}

}
