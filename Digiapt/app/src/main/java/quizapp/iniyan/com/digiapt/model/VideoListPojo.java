package quizapp.iniyan.com.digiapt.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VideoListPojo implements Parcelable {

    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("thumb")
    @Expose
    private String thumb;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("url")
    @Expose
    private String url;

    private VideoListPojo(Parcel in) {
        description = in.readString();
        id = in.readString();
        thumb = in.readString();
        title = in.readString();
        url = in.readString();
    }

    public static final Creator<VideoListPojo> CREATOR = new Creator<VideoListPojo>() {
        @Override
        public VideoListPojo createFromParcel(Parcel in) {
            return new VideoListPojo(in);
        }

        @Override
        public VideoListPojo[] newArray(int size) {
            return new VideoListPojo[size];
        }
    };

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(description);
        dest.writeString(id);
        dest.writeString(thumb);
        dest.writeString(title);
        dest.writeString(url);
    }
}