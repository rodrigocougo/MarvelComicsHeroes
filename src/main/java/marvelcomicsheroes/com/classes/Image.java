package marvelcomicsheroes.com.classes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Image implements Serializable {

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
    public Image() {
    }

    /**
     *
     * @param extension
     * @param path
     */
    public Image(String path, String extension) {
        super();
        this.path = path;
        this.extension = extension;
    }

}