package service;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import pojo.lombok.Data;
import pojo.lombok.LastLogin;
import pojo.lombok.Payload;

public class BDDFakeJSONApp {

    private static String URI = "https://app.fakejson.com/";

    /**
     * Send a payload to query the server to fetch an user's data such as:
     * <ul>
     *     <li>name</li>
     *     <li>email</li>
     *     <li>gender</li>
     *     <li>ipv4 addres</li>
     *     <li>time last connection</li>
     * </ul>
     *
     * @return  Reply of the server query
     */

    @Step("Query server for users' information")
    public static Response queryUsersData() {
        LastLogin lastLogin = LastLogin.builder()
                .dateTime("dateTime|UNIX")
                .ipv4("internetIP4")
                .build();

        Data data = Data.builder()
                .id("nameFirst")
                .email("internetEmail")
                .lastLogin(lastLogin)
                .gender("personGender")
                .repeat(1)
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
