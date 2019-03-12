package mvvmuserlist.com.mvvmuserlist.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import mvvmuserlist.com.mvvmuserlist.model.Info;
import mvvmuserlist.com.mvvmuserlist.model.User;

public class UserResponseHandler {

    @SerializedName("results")
    private List<User> results;

    @SerializedName("info")
    private Info info;

    public List<User> getResults() {
        return results;
    }

    public void setResults(List<User> results) {
        this.results = results;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }
}
