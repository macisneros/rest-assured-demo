package pojo.externalbuilder;

public class DataBuilder {

    private String id;
    private String email;
    private int repeat;
    private LastLogin lastLogin;

    public DataBuilder() {
    }

    public static DataBuilder builder() {
        return new DataBuilder();
    }

    public DataBuilder id(String id) {
        this.id = id;
        return this;
    }

    public DataBuilder email(String email) {
        this.email = email;
        return this;
    }

    public DataBuilder repeat(int repeat) {
        this.repeat = repeat;
        return this;
    }

    public DataBuilder lastLogin(LastLogin login) {
        this.lastLogin = login;
        return this;
    }

    public Data build() {
        return new Data(id, email, repeat, lastLogin);
    }

}
