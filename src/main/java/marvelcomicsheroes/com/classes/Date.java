package marvelcomicsheroes.com.classes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Date implements Serializable {

    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("date")
    @Expose
    public String date;

    /**
     * No args constructor for use in serialization
     *
     */
    public Date() {
    }

    /**
     *
     * @param date
     * @param type
     */
    public Date(String type, String date) {
        super();
        this.type = type;
        this.date = date;
    }

}
