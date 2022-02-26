package pojo.externalbuilder;

public class PayloadBuilder {

    private String token;
    private Object data;

    public static PayloadBuilder builder() {
        return new PayloadBuilder();
    }

    public PayloadBuilder token(String token) {
        this.token = token;
        return this;
    }

    public PayloadBuilder data(Object data) {
        this.data = data;
        return this;
    }

    public Payload build() {
        return new Payload(this.token, this.data);
    }
}
