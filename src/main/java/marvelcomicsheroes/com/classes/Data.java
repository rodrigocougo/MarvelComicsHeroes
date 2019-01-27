package marvelcomicsheroes.com.classes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Data implements Serializable {
    @SerializedName("offset")
    @Expose
    public long offset;
    @SerializedName("limit")
    @Expose
    public long limit;
    @SerializedName("total")
    @Expose
    public long total;
    @SerializedName("count")
    @Expose
    public long count;
    @SerializedName("results")
    @Expose
    public List<Result> results = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public Data() {
    }

    /**
     *
     * @param total
     * @param limit
     * @param results
     * @param count
     * @param offset
     */
    public Data(long offset, long limit, long total, long count, List<Result> results) {
        super();
        this.offset = offset;
        this.limit = limit;
        this.total = total;
        this.count = count;
        this.results = results;
    }
}
