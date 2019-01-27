package marvelcomicsheroes.com.classes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Variant implements Serializable {

    @SerializedName("resourceURI")
    @Expose
    public String resourceURI;
    @SerializedName("name")
    @Expose
    public String name;

    /**
     * No args constructor for use in serialization
     *
     */
    public Variant() {
    }

    /**
     *
     * @param resourceURI
     * @param name
     */
    public Variant(String resourceURI, String name) {
        super();
        this.resourceURI = resourceURI;
        this.name = name;
    }

}