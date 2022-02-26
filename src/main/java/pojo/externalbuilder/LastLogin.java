package pojo.externalbuilder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LastLogin {

    @SerializedName("date_time")
    @Expose
    private String date;

    @SerializedName("ip4")
    @Expose
    private String ipv4;

    public LastLogin(String date, String ipv4) {
        this.date = date;
        this.ipv4 = ipv4;
    }

    public String getDate() {
        return this.date;
    }

    public String getIpv4() {
        return this.ipv4;
    }
}
