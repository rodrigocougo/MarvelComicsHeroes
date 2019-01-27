package marvelcomicsheroes.com.classes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MarvelHeroes implements Serializable {
    @SerializedName("code")
    @Expose
    public long code;
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("copyright")
    @Expose
    public String copyright;
    @SerializedName("attributionText")
    @Expose
    public String attributionText;
    @SerializedName("attributionHTML")
    @Expose
    public String attributionHTML;
    @SerializedName("etag")
    @Expose
    public String etag;
    @SerializedName("data")
    @Expose
    public Data data;

    /**
     * No args constructor for use in serialization
     *
     */
    public MarvelHeroes() {
    }

    /**
     *
     * @param attributionText
     * @param etag
     * @param status
     * @param data
     * @param copyright
     * @param code
     * @param attributionHTML
     */
    public MarvelHeroes(long code, String status, String copyright, String attributionText, String attributionHTML, String etag, Data data) {
        super();
        this.code = code;
        this.status = status;
        this.copyright = copyright;
        this.attributionText = attributionText;
        this.attributionHTML = attributionHTML;
        this.etag = etag;
        this.data = data;
    }
}
