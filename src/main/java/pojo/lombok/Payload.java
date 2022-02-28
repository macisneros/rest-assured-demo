package pojo.lombok;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

@lombok.Data
@Builder
@Jacksonized

public class Payload {

    @SerializedName("token")
    @Expose
    private String token;

    @SerializedName("data")
    @Expose
    private Data data;

}
