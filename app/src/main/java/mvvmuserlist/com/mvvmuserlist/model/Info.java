package mvvmuserlist.com.mvvmuserlist.model;

import com.google.gson.annotations.SerializedName;

public class Info {

    @SerializedName("seed")
    String seed;
    @SerializedName("version")
    String version;
    @SerializedName("results") int results;
    @SerializedName("page") int page;
}
