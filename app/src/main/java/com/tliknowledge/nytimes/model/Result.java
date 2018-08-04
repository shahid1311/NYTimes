
package com.tliknowledge.nytimes.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Result implements Parcelable {
    private String url;

    @SerializedName("adx_keywords")
    private String adxKeywords;
    private String section;
    private String byline;
    private String type;
    private String title;
    @SerializedName("abstract")
    private String _abstract;
    @SerializedName("published_date")
    private String publishedDate;
    private String source;
    private long id;
    @SerializedName("asset_id")
    private long assetId;
    private int views;
    private List<String> desFacet = null;
    private List<String> orgFacet;
    private List<String> perFacet = null;
    private String geoFacet;
    private List<Medium> media = null;
    public final static Creator<Result> CREATOR = new Creator<Result>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Result createFromParcel(Parcel in) {
            return new Result(in);
        }

        public Result[] newArray(int size) {
            return (new Result[size]);
        }

    };

    protected Result(Parcel in) {
        this.url = ((String) in.readValue((String.class.getClassLoader())));
        this.adxKeywords = ((String) in.readValue((String.class.getClassLoader())));
        this.section = ((String) in.readValue((String.class.getClassLoader())));
        this.byline = ((String) in.readValue((String.class.getClassLoader())));
        this.type = ((String) in.readValue((String.class.getClassLoader())));
        this.title = ((String) in.readValue((String.class.getClassLoader())));
        this._abstract = ((String) in.readValue((String.class.getClassLoader())));
        this.publishedDate = ((String) in.readValue((String.class.getClassLoader())));
        this.source = ((String) in.readValue((String.class.getClassLoader())));
        this.id = ((long) in.readValue((long.class.getClassLoader())));
        this.assetId = ((long) in.readValue((long.class.getClassLoader())));
        this.views = ((int) in.readValue((int.class.getClassLoader())));
        in.readList(this.desFacet, (String.class.getClassLoader()));
        in.readList(this.orgFacet, (String.class.getClassLoader()));
        in.readList(this.perFacet, (String.class.getClassLoader()));
        this.geoFacet = ((String) in.readValue((String.class.getClassLoader())));
        if(this.media!=null) {
            in.readList(this.media, (Medium.class.getClassLoader()));
        }
    }

    public Result() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAssetId() {
        return assetId;
    }

    public void setAssetId(long assetId) {
        this.assetId = assetId;
    }

    public String getAdxKeywords() {
        return adxKeywords;
    }

    public void setAdxKeywords(String adxKeywords) {
        this.adxKeywords = adxKeywords;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getByline() {
        return byline;
    }

    public void setByline(String byline) {
        this.byline = byline;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAbstract() {
        return _abstract;
    }

    public void setAbstract(String _abstract) {
        this._abstract = _abstract;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public List<String> getDesFacet() {
        return desFacet;
    }

    public void setDesFacet(List<String> desFacet) {
        this.desFacet = desFacet;
    }

    public List<String> getOrgFacet() {
        return orgFacet;
    }

    public void setOrgFacet(List<String> orgFacet) {
        this.orgFacet = orgFacet;
    }

    public List<String> getPerFacet() {
        return perFacet;
    }

    public void setPerFacet(List<String> perFacet) {
        this.perFacet = perFacet;
    }

    public String getGeoFacet() {
        return geoFacet;
    }

    public void setGeoFacet(String geoFacet) {
        this.geoFacet = geoFacet;
    }

    public List<Medium> getMedia() {
        return media;
    }

    public void setMedia(List<Medium> media) {
        this.media = media;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(url);
        dest.writeValue(adxKeywords);
        dest.writeValue(section);
        dest.writeValue(byline);
        dest.writeValue(type);
        dest.writeValue(title);
        dest.writeValue(_abstract);
        dest.writeValue(publishedDate);
        dest.writeValue(source);
        dest.writeValue(id);
        dest.writeValue(assetId);
        dest.writeValue(views);
        dest.writeList(desFacet);
        dest.writeValue(orgFacet);
        dest.writeList(perFacet);
        dest.writeValue(geoFacet);
        dest.writeList(media);
    }

    public int describeContents() {
        return 0;
    }

}
