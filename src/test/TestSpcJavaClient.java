import static org.junit.Assert.*;

import com.google.gson.Gson;
import de.ascora.spcjavaclient.SpcJavaClient;
import de.ascora.spcjavaclient.metadata.MetaData;
import de.ascora.spcjavaclient.mock.MockMetaData;
import de.ascora.spcjavaclient.mock.MockSpcResponse;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by tobi on 15.09.2015.
 */
public class TestSpcJavaClient {

    private SpcJavaClient spcClient;
    private static final String SOCKET = "127.0.0.1";
    private static final int PORT = 9000;
    private static final String CLIENT_NAME = "JavaTestClient";
    private static final String API_KEY = "59559afbdae9e1075e68fa263057653b";
    private static final String SPC_URL = "http://127.0.0.1:9000/info/api/001";
    private static final String MOCK_ACCESS_TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJfaWQiOiI1NWNkYWI2ZjVhMjNiMWIwMTJkZTU1ZGEiLCJpYXQiOjE0NDExOTcxNTMzMTgsImV4cCI6MTQ0MTIxNTE1MzMxOH0.Gi1eu5OGMyojwMdONMEi7HZrmC90Wq_Q4SIx7MiUz18";

    /*
    For local testing.
     */
    private MockSpcResponse mockSpcResponse;
    private MetaData mockMetaData;
    private Gson gson;


    @Before
    public void setUp() throws Exception {
        spcClient = new SpcJavaClient(CLIENT_NAME, API_KEY, SPC_URL);

        mockSpcResponse = new MockSpcResponse();
        mockMetaData = new MockMetaData().getMetaDataCremaInstace();
        gson = new Gson();
    }

    /*
    Basic test
     */
    @Test
    public void testSpcJavaClientPreferences() {
        assertEquals(spcClient.getSpcClientId(), CLIENT_NAME);
        assertEquals(spcClient.getSpcClientSecret(), API_KEY);
        assertEquals(spcClient.getSpcUrl(), SPC_URL);
    }


    /*
    Client calls internal mock entity information
     */
    @Test
    public void testMockRequestMetaData() throws IOException {
        MetaData result = spcClient.requestMetaData(MOCK_ACCESS_TOKEN);
        assertEquals(result, mockMetaData);
    }

    /*
    Testing the connection to the SPC. Note: run mongodb and grunt serve
     */
    @Test
    public void testSpcConnection() throws IOException {
        Socket socket = new Socket(SOCKET, PORT);
        assertTrue(socket.isConnected());
    }

//    @Test
//    public void testRestRequestMetaData() throws IOException {
//        de.ascora.spcjavaclient.SpcConnector connector = new de.ascora.spcjavaclient.SpcRestConnector(SPC_URL);
//        String result = spcClient.requestMetaData(MOCK_ACCESS_TOKEN, connector);
//        assertEquals(result, gson.toJson(mockSpcResponse));
//    }

}
