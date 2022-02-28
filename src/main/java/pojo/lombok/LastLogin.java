package pojo.lombok;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder

public class LastLogin {

    @SerializedName("date_time")
    @Expose
    private String dateTime;

    @SerializedName("ip4")
    @Expose
    private String ipv4;

}
