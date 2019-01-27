package marvelcomicsheroes.com.classes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TextObject implements Serializable {

    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("language")
    @Expose
    public String language;
    @SerializedName("text")
    @Expose
    public String text;

    /**
     * No args constructor for use in serialization
     *
     */
    public TextObject() {
    }

    /**
     *
     * @param text
     * @param language
     * @param type
     */
    public TextObject(String type, String language, String text) {
        super();
        this.type = type;
        this.language = language;
        this.text = text;
    }

}
