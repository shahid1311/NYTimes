
package com.tliknowledge.nytimes.model;

import android.os.Parcel;
import android.os.Parcelable;

public class MediaMetadata implements Parcelable
{

    private String url;
    private String format;
    private int height;
    private int width;
    public final static Creator<MediaMetadata> CREATOR = new Creator<MediaMetadata>() {


        @SuppressWarnings({
            "unchecked"
        })
        public MediaMetadata createFromParcel(Parcel in) {
            return new MediaMetadata(in);
        }

        public MediaMetadata[] newArray(int size) {
            return (new MediaMetadata[size]);
        }

    }
    ;

    protected MediaMetadata(Parcel in) {
        this.url = ((String) in.readValue((String.class.getClassLoader())));
        this.format = ((String) in.readValue((String.class.getClassLoader())));
        this.height = ((int) in.readValue((int.class.getClassLoader())));
        this.width = ((int) in.readValue((int.class.getClassLoader())));
    }

    public MediaMetadata() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(url);
        dest.writeValue(format);
        dest.writeValue(height);
        dest.writeValue(width);
    }

    public int describeContents() {
        return  0;
    }

}
