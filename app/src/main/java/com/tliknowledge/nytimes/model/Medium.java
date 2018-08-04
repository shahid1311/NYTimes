
package com.tliknowledge.nytimes.model;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.tliknowledge.nytimes.constants.AppConstants;

public class Medium implements Parcelable
{

    private String type;
    private String subtype;
    private String caption;
    private String copyright;
    @SerializedName("approved_for_syndication")
    private int approvedForSyndication;
    @SerializedName("media-metadata")
    private List<MediaMetadata> mediaMetadata;
    public final static Creator<Medium> CREATOR = new Creator<Medium>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Medium createFromParcel(Parcel in) {
            return new Medium(in);
        }

        public Medium[] newArray(int size) {
            return (new Medium[size]);
        }

    }
    ;

    protected Medium(Parcel in) {
        this.type = ((String) in.readValue((String.class.getClassLoader())));
        this.subtype = ((String) in.readValue((String.class.getClassLoader())));
        this.caption = ((String) in.readValue((String.class.getClassLoader())));
        this.copyright = ((String) in.readValue((String.class.getClassLoader())));
        this.approvedForSyndication = ((int) in.readValue((int.class.getClassLoader())));
        if(this.mediaMetadata!=null) {
            in.readList(this.mediaMetadata, (MediaMetadata.class.getClassLoader()));
        }
    }

    public Medium() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public int getApprovedForSyndication() {
        return approvedForSyndication;
    }

    public void setApprovedForSyndication(int approvedForSyndication) {
        this.approvedForSyndication = approvedForSyndication;
    }

    public List<MediaMetadata> getMediaMetadata() {
        return mediaMetadata;
    }

    public void setMediaMetadata(List<MediaMetadata> mediaMetadata) {
        this.mediaMetadata = mediaMetadata;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(type);
        dest.writeValue(subtype);
        dest.writeValue(caption);
        dest.writeValue(copyright);
        dest.writeValue(approvedForSyndication);
        dest.writeList(mediaMetadata);
    }

    public int describeContents() {
        return  0;
    }

    public MediaMetadata getThumbnailMetaData(List<MediaMetadata> mediaMetadata){
        if(mediaMetadata!=null){
            for (MediaMetadata mmd : mediaMetadata){
                if(mmd.getFormat().equalsIgnoreCase(AppConstants.ImageFormat.STANDARD_THUMBNAIL)){
                    return mmd;
                }
            }
        }
        return null;
    }

}
