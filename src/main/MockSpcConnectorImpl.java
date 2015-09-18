import com.google.gson.Gson;

/**
 * Created by tobi on 15.09.2015.
 */
public class MockSpcConnectorImpl implements SpcConnector {

    private String uri;
    private MockSpcResponse mockSpcResponse;

    private static final String SPC_REQUEST_URL = "http://127.0.0.1:9000/info/api/001";
    private static final String SPC_TEST_AUTH = "?api_key=59559afbdae9e1075e68fa263057653b&access_token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJfaWQiOiI1NWNkYWI2ZjVhMjNiMWIwMTJkZTU1ZGEiLCJpYXQiOjE0NDExOTcxNTMzMTgsImV4cCI6MTQ0MTIxNTE1MzMxOH0.Gi1eu5OGMyojwMdONMEi7HZrmC90Wq_Q4SIx7MiUz18";


    public MockSpcConnectorImpl(String uri) {
        this.uri = uri;
        mockSpcResponse = new MockSpcResponse();
    }


    @Override
    public String requestEndpointDocument(String apiKey, String accessToken) {
        this.uri += "?api_key=" + apiKey + "&access_token=" + accessToken;
        Gson gson = new Gson();

        if (executeRequest(uri)) {
            return gson.toJson(mockSpcResponse);
        }

        return "401";
    }



    private boolean executeRequest(String url) {
        return url.equals(SPC_REQUEST_URL + SPC_TEST_AUTH);
    }
}
