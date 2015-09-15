/**
 * Created by tobi on 15.09.2015.
 */
public class MockSpcConnectorImpl implements SpcConnector {

    private String query;

    private static final String SPC_REQUEST_URL = "http://127.0.0.1:9000/info/api/001";
    private static final String SPC_TEST_AUTH = "?api_key=59559afbdae9e1075e68fa263057653b&access_token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJfaWQiOiI1NWNkYWI2ZjVhMjNiMWIwMTJkZTU1ZGEiLCJpYXQiOjE0NDExOTcxNTMzMTgsImV4cCI6MTQ0MTIxNTE1MzMxOH0.Gi1eu5OGMyojwMdONMEi7HZrmC90Wq_Q4SIx7MiUz18";
    private static final String[] SPC_RESPONSE = {
            "_id", "55cdab6f5a23b1b012de55da", "name", "Testuser",
            "address", "Musterstr. 16", "company", "BMW", "factory", "München Werk 1",
            "key", "color", "value", "green", "key", "accesslevel", "value", "1",
            "key", "email", "value", "testuser@ascora.de"
    };

    @Override
    public void setAuthParameter(String apiKey, String accessToken) {
        this.query = "?api_key=" + apiKey + "&access_token=" + accessToken;
    }

    @Override
    public String[] requestEndpoint(String endpoint) {

        String requestUrl = endpoint + query;

        if (requestUrl.equals(SPC_REQUEST_URL + SPC_TEST_AUTH)) {
            return SPC_RESPONSE;
        }
        return new String[] {"401"};
    }
}
