package marvelcomicsheroes.com.classes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Price {

    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("price")
    @Expose
    public float price;

    /**
     * No args constructor for use in serialization
     *
     */
    public Price() {
    }

    /**
     *
     * @param price
     * @param type
     */
    public Price(String type, float price) {
        super();
        this.type = type;
        this.price = price;
    }

}