import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import service.BDDFakeJSONApp;
import utils.Hook;

import static org.hamcrest.Matchers.*;

@Listeners(Hook.class)
@Epic("Job application")
@Feature("Server fetches data on demand - BDD")
public class DemoRestAssuredBDD {

    @Test(priority = 0, groups = "reggresion")
    @Story("Endpoint is available to query")
    @Description(
            "The objective is to verify endpoint is available to fetch the response"
    )
    @Severity(SeverityLevel.CRITICAL)
    public void ApiIsExposed() {
        BDDFakeJSONApp.queryUsersData().
                then().assertThat().statusCode(200);
    }

    @Test(priority = 0, groups = "reggresion")
    @Story("Verify structure of user data")
    @Description(
            "The objective is to verify fields within payload are being " +
                    "fetched in response. Example, if sent payload contain " +
                    "id, email and gender then response contains those fields " +
                    "and its respective values"
    )
    @Severity(SeverityLevel.BLOCKER)
    public void VerifyUserResponseStructure() {
        BDDFakeJSONApp.queryUsersData()
                .then().assertThat().body("[0]", hasKey("id"))
                .and().body("[0]", hasKey("email"))
                .and().body("[0]", hasKey("gender"))
                .and().body("[0]", hasKey("last_login"));
    }

    @Test(priority = 0, groups = "reggresion")
    @Story("Verify structure of user data -> last login")
    @Description(
            "The objective is to verify fields within payload are being fetched " +
                    "in response. Example, if sent payload contain id, email and gender then " +
                    "response contains those fields and its respective values"
    )
    @Severity(SeverityLevel.BLOCKER)
    public void VerifyLastLoginResponseStructure() {
        BDDFakeJSONApp.queryUsersData()
                .then().assertThat().body("[0]['last_login']", hasKey("date_time"))
                .and().body("[0]['last_login']", hasKey("ip4"));
    }

    @Test(priority = 0, groups = "reggresion")
    @Story("None user data is null")
    @Description(
            "The objective is to verify fields within payload are being " +
                    "fetched in response. Example, if sent payload contain " +
                    "id, email and gender then response contains those fields " +
                    "and its respective values"
    )
    @Severity(SeverityLevel.BLOCKER)
    public void NoneNullValuesForOneUser() {
        BDDFakeJSONApp.queryUsersData().
                then().assertThat().body("[0]['id']", notNullValue())
                .and().body("[0]['email']", notNullValue())
                .and().body("[0]['gender']", notNullValue())
                .and().body("[0]['last_login']", notNullValue());
    }

    @Test(priority = 0, groups = "reggresion")
    @Story("None last login data is null")
    @Description(
            "The objective is to verify fields within payload are being " +
                    "fetched in response. Example, if sent payload contain " +
                    "id, email and gender then response contains those fields " +
                    "and its respective values"
    )
    @Severity(SeverityLevel.BLOCKER)
    public void NoneNullValuesForOneUserLastLogin() {
        BDDFakeJSONApp.queryUsersData().
                then().assertThat().body("[0]['last_login']['ip4']", notNullValue())
                .and().body("[0]['last_login']['date_time']", notNullValue());
    }

    @Test(priority = 1, groups = "reggresion")
    @Story("Ipv4 address is written in the proper format")
    @Description(
            "The objective is to verify ipv4 in response is valid. In other words, it has " +
                    "to follow the format XXX.XXX.XXX.XXX where XXX is a number between 0 and 255"
    )
    @Severity(SeverityLevel.NORMAL)
    public void ipv4ProperlyWritten() {
        String ipv4 = BDDFakeJSONApp.queryUsersData()
                .then().extract().body().path("[0]['last_login']['ip4']");

        String[] segments = ipv4.split("\\.");

        Assert.assertTrue(segments.length == 4, "ipv4 should contain only 4 numbers");

        for(String segment: segments) {
            int numSegment = Integer.parseInt(segment);
            Assert.assertTrue( numSegment >= 0 && numSegment <=255, "ipv4 segment is not a valid number");
        }

    }

    @Test(priority = 1, groups = "reggresion")
    @Story("Gender value is only male or female")
    @Description(
            "The objective is to verify gender in response can contains " +
                    "just one of these values either male or female."
    )
    @Severity(SeverityLevel.NORMAL)
    public void genderIsAnExpectedValue() {
        BDDFakeJSONApp.queryUsersData().
                then().assertThat().body("[0]['gender']", anyOf(equalTo("male"), equalTo("female")));
    }

    @Test(priority = 1, groups = {"reggresion"})
    @Story("User name is composed only by letters")
    @Description(
            "The objective is to verify name in response is written " +
                    "only with literal strings."
    )
    @Severity(SeverityLevel.NORMAL)
    public void nameIsOnlyChars() {
        BDDFakeJSONApp.queryUsersData().
                then().assertThat().body("[0]['id']", matchesPattern("[a-zA-Z]+"));
    }
}
