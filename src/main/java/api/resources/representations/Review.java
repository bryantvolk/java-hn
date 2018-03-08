package api.resources.representations;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

public class Review {
    @NotNull
    private Integer id;

    @NotEmpty @Length(min = 5, max = 255)
    private String title;

    @NotEmpty
    private String body;

    public Review() {}

    public Review(Integer id, String title, String body) {
        this.id = id;
        this.title = title;
        this.body = body;
    }

    @JsonProperty
    public Integer getId() {return id;}

    @JsonProperty
    public String getTitle() {
        return title;
    }

    @JsonProperty
    public String getBody() {
        return body;
    }

    @Override
    public String toString() {
        return title;
    }
}
