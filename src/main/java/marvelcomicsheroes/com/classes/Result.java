package marvelcomicsheroes.com.classes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Result implements Serializable {
    @SerializedName("id")
    @Expose
    public long id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("modified")
    @Expose
    public String modified;
    @SerializedName("thumbnail")
    @Expose
    public Thumbnail thumbnail;
    @SerializedName("resourceURI")
    @Expose
    public String resourceURI;
    @SerializedName("comics")
    @Expose
    public Comics comics;
    @SerializedName("series")
    @Expose
    public Series series;
    @SerializedName("stories")
    @Expose
    public Stories stories;
    @SerializedName("events")
    @Expose
    public Events events;
    @SerializedName("urls")
    @Expose
    public List<Url> urls = null;

    /* #2 */
    @SerializedName("digitalId")
    @Expose
    public long digitalId;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("issueNumber")
    @Expose
    public long issueNumber;
    @SerializedName("variantDescription")
    @Expose
    public String variantDescription;
    @SerializedName("isbn")
    @Expose
    public String isbn;
    @SerializedName("upc")
    @Expose
    public String upc;
    @SerializedName("diamondCode")
    @Expose
    public String diamondCode;
    @SerializedName("ean")
    @Expose
    public String ean;
    @SerializedName("issn")
    @Expose
    public String issn;
    @SerializedName("format")
    @Expose
    public String format;
    @SerializedName("pageCount")
    @Expose
    public long pageCount;
    @SerializedName("textObjects")
    @Expose
    public List<TextObject> textObjects = null;
    @SerializedName("variants")
    @Expose
    public List<Variant> variants = null;
    @SerializedName("collections")
    @Expose
    public List<Object> collections = null;
    @SerializedName("collectedIssues")
    @Expose
    public List<Object> collectedIssues = null;
    @SerializedName("dates")
    @Expose
    public List<Date> dates = null;
    @SerializedName("prices")
    @Expose
    public List<Price> prices = null;
    @SerializedName("images")
    @Expose
    public List<Image> images = null;
    @SerializedName("creators")
    @Expose
    public Creators creators;
    @SerializedName("characters")
    @Expose
    public Characters characters;

    /**
     * No args constructor for use in serialization
     *
     */
    public Result() {
    }

    /**
     *
     * @param id
     * @param series
     * @param stories
     * @param thumbnail
     * @param resourceURI
     * @param urls
     * @param events
     * @param description
     * @param name
     * @param comics
     * @param modified
     */
    public Result(long id, String name, String description, String modified, Thumbnail thumbnail, String resourceURI, Comics comics, Series series, Stories stories, Events events, List<Url> urls) {
        super();
        this.id = id;
        this.name = name;
        this.description = description;
        this.modified = modified;
        this.thumbnail = thumbnail;
        this.resourceURI = resourceURI;
        this.comics = comics;
        this.series = series;
        this.stories = stories;
        this.events = events;
        this.urls = urls;
    }

    /**
     *
     * @param series
     * @param issn
     * @param events
     * @param id
     * @param title
     * @param dates
     * @param description
     * @param isbn
     * @param variants
     * @param digitalId
     * @param collections
     * @param pageCount
     * @param textObjects
     * @param urls
     * @param format
     * @param upc
     * @param modified
     * @param variantDescription
     * @param creators
     * @param ean
     * @param issueNumber
     * @param stories
     * @param thumbnail
     * @param resourceURI
     * @param images
     * @param collectedIssues
     * @param prices
     * @param characters
     * @param diamondCode
     */
    public Result(long id, long digitalId, String title, long issueNumber, String variantDescription, String description, String modified, String isbn, String upc, String diamondCode, String ean, String issn, String format, long pageCount, List<TextObject> textObjects, String resourceURI, List<Url> urls, Series series, List<Variant> variants, List<Object> collections, List<Object> collectedIssues, List<Date> dates, List<Price> prices, Thumbnail thumbnail, List<Image> images, Creators creators, Characters characters, Stories stories, Events events) {
        super();
        this.id = id;
        this.digitalId = digitalId;
        this.title = title;
        this.issueNumber = issueNumber;
        this.variantDescription = variantDescription;
        this.description = description;
        this.modified = modified;
        this.isbn = isbn;
        this.upc = upc;
        this.diamondCode = diamondCode;
        this.ean = ean;
        this.issn = issn;
        this.format = format;
        this.pageCount = pageCount;
        this.textObjects = textObjects;
        this.resourceURI = resourceURI;
        this.urls = urls;
        this.series = series;
        this.variants = variants;
        this.collections = collections;
        this.collectedIssues = collectedIssues;
        this.dates = dates;
        this.prices = prices;
        this.thumbnail = thumbnail;
        this.images = images;
        this.creators = creators;
        this.characters = characters;
        this.stories = stories;
        this.events = events;
    }
}
