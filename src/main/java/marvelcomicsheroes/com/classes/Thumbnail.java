package marvelcomicsheroes.com.classes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Thumbnail implements Serializable {
    @SerializedName("path")
    @Expose
    public String path;
    @SerializedName("extension")
    @Expose
    public String extension;

    /**
     * No args constructor for use in serialization
     *
     */
    public Thumbnail() {
    }

    /**
     *
     * @param extension
     * @param path
     */
    public Thumbnail(String path, String extension) {
        super();
        this.path = path;
        this.extension = extension;
    }
}
