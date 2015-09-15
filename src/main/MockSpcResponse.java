/**
 * Created by tobi on 15.09.2015.
 */
public class MockSpcResponse {
    private String _id = "55cdab6f5a23b1b012de55da";
    private String name = "Testuser";
    private String company = "BMW";
    private String factory = "München Werk 1";
    private String key = "email";
    private String value = "testuser@ascora.de";

    MockSpcResponse() {}

    @Override
    public String toString() {
        return "{" +
                "_id:'" + _id + '\'' +
                ", name:'" + name + '\'' +
                ", company:'" + company + '\'' +
                ", factory:'" + factory + '\'' +
                ", key:'" + key + '\'' +
                ", value:'" + value + '\'' +
                '}';
    }
}
