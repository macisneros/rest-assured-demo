
import io.restassured.response.Response;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.EmailValidator;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.lombok.Data;
import pojo.lombok.LastLogin;
import service.FakeJSONApp;

public class DemoRestAssured {

    @Test
    public void ApiIsExposed() {
        Response response = FakeJSONApp.getResponse();

        Assert.assertEquals(response.statusCode(), 200, "Api not available");
    }

    @Test
    public void VerifyUserResponseStructure() {
        Response response = FakeJSONApp.getResponse();
        String jsonUser = response.jsonPath().get("[0]").toString();

        Assert.assertTrue(jsonUser.contains("id="), "User does not contain field id");
        Assert.assertTrue(jsonUser.contains("email="), "User does not contain field email");
        Assert.assertTrue(jsonUser.contains("gender="), "User does not contain field gender");
        Assert.assertTrue(jsonUser.contains("last_login="), "User does not contain field last_login");
    }

    @Test
    public void VerifyLastLoginResponseStructure() {
        Response response = FakeJSONApp.getResponse();
        String jsonLastLogin = response.jsonPath().get("[0]['last_login']").toString();

        Assert.assertTrue(jsonLastLogin.contains("date_time="), "Last login does not contain field date_time");
        Assert.assertTrue(jsonLastLogin.contains("ip4="), "Last login does not contain field ip4");
    }

    @Test
    public void NoneNullValuesForOneUser() {
        Data user = FakeJSONApp.getUser(0);

        Assert.assertNotNull(user.getId(), "User field id is null");
        Assert.assertNotNull(user.getEmail(), "User field email is null");
        Assert.assertNotNull(user.getGender(), "User field gender is null");
        Assert.assertNotNull(user.getLastLogin(), "User field last login is null");
    }

    @Test
    public void NoneNullValuesForOneUserLastLogin() {
        Data user = FakeJSONApp.getUser(0);
        LastLogin lastLogin = user.getLastLogin();

        Assert.assertNotNull(lastLogin.getDateTime(), "User field date time is null");
        Assert.assertNotNull(lastLogin.getIpv4(), "User field ipv4 is null");
    }

    @Test
    public void ipv4ProperlyWritten() {
        LastLogin lastLogin = FakeJSONApp.getUser(0).getLastLogin();

        String[] segments = lastLogin.getIpv4().split("\\.");

        Assert.assertTrue(segments.length == 4, "ipv4 should contain only 4 numbers");

        for(String segment: segments) {
            int numSegment = Integer.parseInt(segment);
            Assert.assertTrue( numSegment >= 0 && numSegment <=255, "ipv4 segment is not a valid number");
        }

    }

    @Test
    public void genderIsAnExpectedValue() {
        Data user = FakeJSONApp.getUser(0);

        //Assert.assertTrue(user.getGender().matches("male|female"), "Gender is not an expected value");
        Assert.assertTrue(user.getGender().equals("male") || user.getGender().equals("female"), "gender is not an expected value");
    }

    @Test
    public void nameIsOnlyChars() {
        Data user = FakeJSONApp.getUser(0);

        //Assert.assertTrue(user.getId().matches("[a-zA-Z]+"), "Name of user does not contains only chars");
        Assert.assertTrue(StringUtils.isAlpha(user.getId()), "Name of user does not contains only chars");
    }

    @Test
    public void emailProperlyWritten() {
        Data user = FakeJSONApp.getUser(0);

        //Assert.assertTrue(user.getEmail().matches("^(.+)@(\\\\S+)$"), "wrong email format");
        Assert.assertTrue(EmailValidator.getInstance().isValid(user.getEmail()), "wrong email format");
    }
}
