package pojo.lombok;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

@lombok.Data
@Builder
@Jacksonized
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

    @SerializedName("gender")
    @Expose
    private String gender;

    @SerializedName("last_login")
    @Expose
    private LastLogin lastLogin;
}
