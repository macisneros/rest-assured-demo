package pojo.externalbuilder;

public class LastLoginBuilder {

    private String date;
    private String ipv4;

    public static LastLoginBuilder builder() {
        return new LastLoginBuilder();
    }

    public LastLoginBuilder dateTime(String date) {
        this.date = date;
        return this;
    }

    public LastLoginBuilder ipv4(String ipv4) {
        this.ipv4 = ipv4;
        return this;
    }

    public LastLogin build() {
        return new LastLogin(date, ipv4);
    }
}
