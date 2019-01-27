package marvelcomicsheroes.com.classes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Url implements Serializable {
    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("url")
    @Expose
    public String url;

    /**
     * No args constructor for use in serialization
     *
     */
    public Url() {
    }

    /**
     *
     * @param type
     * @param url
     */
    public Url(String type, String url) {
        super();
        this.type = type;
        this.url = url;
    }
}
