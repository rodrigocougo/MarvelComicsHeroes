package marvelcomicsheroes.com.classes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Item implements Serializable {
    @SerializedName("resourceURI")
    @Expose
    public String resourceURI;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("role")
    @Expose
    public String role;
    @SerializedName("type")
    @Expose
    public String type;

    /**
     * No args constructor for use in serialization
     *
     */
    public Item() {
    }

    /**
     *
     * @param resourceURI
     * @param name
     */
    public Item(String resourceURI, String name) {
        super();
        this.resourceURI = resourceURI;
        this.name = name;
    }

    /**
     *
     * @param resourceURI
     * @param name
     * @param role
     */
    public Item(String resourceURI, String name, String role) {
        super();
        this.resourceURI = resourceURI;
        this.name = name;
        this.role = role;
    }
}
