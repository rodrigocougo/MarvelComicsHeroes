package marvelcomicsheroes.com.classes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Characters implements Serializable {

    @SerializedName("available")
    @Expose
    public long available;
    @SerializedName("collectionURI")
    @Expose
    public String collectionURI;
    @SerializedName("items")
    @Expose
    public List<Item> items = null;
    @SerializedName("returned")
    @Expose
    public long returned;

    /**
     * No args constructor for use in serialization
     *
     */
    public Characters() {
    }

    /**
     *
     * @param items
     * @param collectionURI
     * @param available
     * @param returned
     */
    public Characters(long available, String collectionURI, List<Item> items, long returned) {
        super();
        this.available = available;
        this.collectionURI = collectionURI;
        this.items = items;
        this.returned = returned;
    }

}
