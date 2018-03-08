package api.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

public class Representation<T> {

    @Length(max = 4)
    private T data;

    public Representation() {}

    public Representation(long code, T data) {
        this.data = data;
    }

    @JsonProperty
    public T getData() {
        return data;
    }
}
