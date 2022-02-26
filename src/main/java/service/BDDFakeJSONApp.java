package service;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import pojo.lombok.Data;
import pojo.lombok.LastLogin;
import pojo.lombok.Payload;

public class BDDFakeJSONApp {

    private static String URI = "https://app.fakejson.com/";

    /**
     *
     * @return
     */

    public static Response getResponse() {
        LastLogin lastLogin = LastLogin.builder()
                .dateTime("dateTime|UNIX")
                .ipv4("internetIP4")
                .build();

        Data data = Data.builder()
                .id("nameFirst")
                .email("internetEmail")
                .lastLogin(lastLogin)
                .gender("personGender")
                .repeat(5)
                .build();

        Payload payload = Payload.builder()
                .token("Eatfp3cQTPwim0X3DI9o6w")
                .data(data)
                .build();

        return RestAssured.given().baseUri(URI)
                    .and().header("Content-Type", "application/json")
                    .and().body(payload)
                .when().
                    post("/q");
    }
}
