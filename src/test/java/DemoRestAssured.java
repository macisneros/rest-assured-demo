
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.EmailValidator;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pojo.lombok.Data;
import pojo.lombok.LastLogin;
import service.FakeJSONApp;
import utils.Hook;

@Listeners(Hook.class)
@Epic("Job application")
@Feature("Server fetches data on demand")
public class DemoRestAssured {

    @Test(priority = 0, groups = "reggresion")
    @Story("Endpoint is available to query")
    @Description(
        "The objective is to verify endpoint is available to fetch the response"
    )
    @Severity(SeverityLevel.CRITICAL)
    public void ApiIsExposed() {
        Response response = FakeJSONApp.getServerData();

        Assert.assertEquals(response.statusCode(), 200, "Api not available");
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
        Response response = FakeJSONApp.getServerData();
        String jsonUser = response.jsonPath().get("[0]").toString();

        Assert.assertTrue(jsonUser.contains("id="), "User does not contain field id");
        Assert.assertTrue(jsonUser.contains("email="), "User does not contain field email");
        Assert.assertTrue(jsonUser.contains("gender="), "User does not contain field gender");
        Assert.assertTrue(jsonUser.contains("last_login="), "User does not contain field last_login");
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
        Response response = FakeJSONApp.getServerData();
        String jsonLastLogin = response.jsonPath().get("[0]['last_login']").toString();

        Assert.assertTrue(jsonLastLogin.contains("date_time="), "Last login does not contain field date_time");
        Assert.assertTrue(jsonLastLogin.contains("ip4="), "Last login does not contain field ip4");
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
        Data user = FakeJSONApp.getUser(0);

        Assert.assertNotNull(user.getId(), "User field id is null");
        Assert.assertNotNull(user.getEmail(), "User field email is null");
        Assert.assertNotNull(user.getGender(), "User field gender is null");
        Assert.assertNotNull(user.getLastLogin(), "User field last login is null");
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
        Data user = FakeJSONApp.getUser(0);
        LastLogin lastLogin = user.getLastLogin();

        Assert.assertNotNull(lastLogin.getDateTime(), "User field date time is null");
        Assert.assertNotNull(lastLogin.getIpv4(), "User field ipv4 is null");
    }

    @Test(priority = 1, groups = "reggresion")
    @Story("Ipv4 address is written in the proper format")
    @Description(
        "The objective is to verify ipv4 in response is valid. In other words, it has " +
        "to follow the format XXX.XXX.XXX.XXX where XXX is a number between 0 and 255"
    )
    @Severity(SeverityLevel.NORMAL)
    public void ipv4ProperlyWritten() {
        LastLogin lastLogin = FakeJSONApp.getUser(0).getLastLogin();

        String[] segments = lastLogin.getIpv4().split("\\.");

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
        Data user = FakeJSONApp.getUser(0);

        //Assert.assertTrue(user.getGender().matches("male|female"), "Gender is not an expected value");
        Assert.assertTrue(user.getGender().equals("male") || user.getGender().equals("female"), "gender is not an expected value");
    }

    @Test(priority = 1, groups = {"reggresion"})
    @Story("User name is composed only by letters")
    @Description(
        "The objective is to verify name in response is written " +
        "only with literal strings."
    )
    @Severity(SeverityLevel.NORMAL)
    public void nameIsOnlyChars() {
        Data user = FakeJSONApp.getUser(0);

        //Assert.assertTrue(user.getId().matches("[a-zA-Z]+"), "Name of user does not contains only chars");
        Assert.assertTrue(StringUtils.isAlpha(user.getId()), "Name of user does not contains only chars");
    }

    @Test(priority = 1, groups = "reggresion")
    @Story("Email is written in the proper format")
    @Description(
        "The objective is to verify email in response is written " +
        "in a proper format (xxxxx@xxx.com)"
    )
    @Severity(SeverityLevel.NORMAL)
    public void emailProperlyWritten() {
        Data user = FakeJSONApp.getUser(0);

        //Assert.assertTrue(user.getEmail().matches("^(.+)@(\\\\S+)$"), "wrong email format");
        Assert.assertTrue(EmailValidator.getInstance().isValid(user.getEmail()), "wrong email format");
    }
}
