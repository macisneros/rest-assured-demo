package service;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.lombok.Data;
import pojo.lombok.LastLogin;
import pojo.lombok.Payload;

public class FakeJSONApp {

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
    public static Response getServerData() {
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

    /** Fetch data from many users
     *
     * @return  Array of deserialized users data
     */

    @Step("Get data from many users deserialized")
    public static Data[] getUsers() {

        Response response = getServerData();
        Data[] users = response.getBody().as(Data[].class);

        return users;
    }

    /** Fetch data just from one user
     *
     * @param id  Index of the selected user you want data
     * @return    Deserialized data of the selected user
     */

    @Step("Get data from user {0} deserialized")
    public static Data getUser(int id) {

        Data[] users = getUsers();

        return users[id];
    }

}
