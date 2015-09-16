import static org.junit.Assert.*;

import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by tobi on 15.09.2015.
 */
public class TestSpcJavaClient {

    private SpcJavaClient spcClient;
    private static final String CLIENT_NAME = "JavaTestClient";
    private static final String API_KEY = "59559afbdae9e1075e68fa263057653b";
    private static final String SPC_URL = "http://127.0.0.1:9000/info/api/001";
    private static final String MOCK_ACCESS_TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJfaWQiOiI1NWNkYWI2ZjVhMjNiMWIwMTJkZTU1ZGEiLCJpYXQiOjE0NDExOTcxNTMzMTgsImV4cCI6MTQ0MTIxNTE1MzMxOH0.Gi1eu5OGMyojwMdONMEi7HZrmC90Wq_Q4SIx7MiUz18";

    private MockSpcResponse mockSpcResponse;
    private Gson gson;


    @Before
    public void setUp() throws Exception {
        spcClient = new SpcJavaClient();
        spcClient.setSpcClientId(CLIENT_NAME);
        spcClient.setSpcClientSecret(API_KEY);
        spcClient.setSpcUrl(SPC_URL);

        mockSpcResponse = new MockSpcResponse();
        gson = new Gson();
    }

    @Test
    public void testSpcJavaClientPreferences() {

        assertEquals(spcClient.getSpcClientId(), CLIENT_NAME);
        assertEquals(spcClient.getSpcClientSecret(), API_KEY);
        assertEquals(spcClient.getSpcUrl(), SPC_URL);
    }


    @Test
    public void testMockRequestMetaData() throws IOException {
        SpcConnector connector = new MockSpcConnectorImpl(SPC_URL);
        String result = spcClient.requestMetaData(MOCK_ACCESS_TOKEN, connector);
        assertEquals(result, gson.toJson(mockSpcResponse));
    }

    @Test
    public void testRestRequestMetaData() throws IOException {
        SpcConnector connector = new SpcRestConnector(SPC_URL);
        String result = spcClient.requestMetaData(MOCK_ACCESS_TOKEN, connector);
        assertEquals(result, gson.toJson(mockSpcResponse));
    }

}
