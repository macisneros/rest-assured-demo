package pojo.externalbuilder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("_repeat")
    @Expose
    private int repeat;

    @SerializedName("last_login")
    @Expose
    private LastLogin lastLogin;

    public Data(String id, String email, int repeat) {

    }

    public Data(String id, String email, int repeat, LastLogin lastLogin) {
        this.id = id;
        this.email = email;
        this.repeat = repeat;
        this.lastLogin = lastLogin;
    }

    public String getId() {
        return this.id;
    }

    public String getEmail() {
        return this.email;
    }

    public int getRepeat () {return this.repeat; }

}
