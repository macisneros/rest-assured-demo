package pojo.externalbuilder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Payload {

    @SerializedName("token")
    @Expose
    private String token;

    @SerializedName("data")
    @Expose
    private Object data;

    public Payload(String token, Object data) {
        this.token = token;
        this.data = data;
    }

    public String getToken (){
        return this.token;
    }

    public Object getData() {
        return this.data;
    }
}
