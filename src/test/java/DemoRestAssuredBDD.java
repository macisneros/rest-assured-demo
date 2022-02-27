import org.testng.Assert;
import org.testng.annotations.Test;
import service.BDDFakeJSONApp;

import static org.hamcrest.Matchers.*;

public class DemoRestAssuredBDD {

    @Test
    public void ApiIsExposed() {
        BDDFakeJSONApp.getResponse().
                then().assertThat().statusCode(200);
    }

    @Test
    public void VerifyUserResponseStructure() {
        BDDFakeJSONApp.getResponse()
                .then().assertThat().body("[0]", hasKey("id"))
                .and().body("[0]", hasKey("email"))
                .and().body("[0]", hasKey("gender"))
                .and().body("[0]", hasKey("last_login"));
    }

    @Test
    public void VerifyLastLoginResponseStructure() {
        BDDFakeJSONApp.getResponse()
                .then().assertThat().body("[0]['last_login']", hasKey("date_time"))
                .and().body("[0]['last_login']", hasKey("ip4"));
    }

    @Test
    public void NoneNullValuesForOneUser() {
        BDDFakeJSONApp.getResponse().
                then().assertThat().body("[0]['id']", notNullValue())
                .and().body("[0]['email']", notNullValue())
                .and().body("[0]['gender']", notNullValue())
                .and().body("[0]['last_login']", notNullValue());
    }

    @Test
    public void NoneNullValuesForOneUserLastLogin() {
        BDDFakeJSONApp.getResponse().
                then().assertThat().body("[0]['last_login']['ip4']", notNullValue())
                .and().body("[0]['last_login']['date_time']", notNullValue());
    }

    @Test
    public void ipv4ProperlyWritten() {
        String ipv4 = BDDFakeJSONApp.getResponse()
                .then().extract().body().path("[0]['last_login']['ip4']");

        String[] segments = ipv4.split("\\.");

        Assert.assertTrue(segments.length == 4, "ipv4 should contain only 4 numbers");

        for(String segment: segments) {
            int numSegment = Integer.parseInt(segment);
            Assert.assertTrue( numSegment >= 0 && numSegment <=255, "ipv4 segment is not a valid number");
        }

    }

    @Test
    public void genderIsAnExpectedValue() {
        BDDFakeJSONApp.getResponse().
                then().assertThat().body("[0]['gender']", anyOf(equalTo("male"), equalTo("female")));
    }

    @Test
    public void nameIsOnlyChars() {
        BDDFakeJSONApp.getResponse().
                then().assertThat().body("[0]['id']", matchesPattern("[a-zA-Z]+"));
    }
}
