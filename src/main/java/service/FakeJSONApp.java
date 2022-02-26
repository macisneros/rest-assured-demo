package service;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.lombok.Data;
import pojo.lombok.LastLogin;
import pojo.lombok.Payload;

public class FakeJSONApp {

    private static String URI = "https://app.fakejson.com/";

    /**
     *
     * @return
     */

    private static Response getServerData() {
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

        RestAssured.baseURI = URI;
        RequestSpecification request = RestAssured.given();

        request.header("Content-Type", "application/json");
        request.body(payload);

        return request.post("/q");
    }

    /**
     *
     * @return
     */

    public static Data[] getUsers() {

        Response response = getServerData();
        Data[] users = response.getBody().as(Data[].class);

        return users;
    }

    /**
     *
     * @return
     */

    public static Data getUser(int id) {

        Data[] users = getUsers();

        return users[id];
    }

    public static Response getResponse() {

        return getServerData();
    }
}
