import static org.junit.Assert.*;

import com.google.gson.Gson;
import de.ascora.spcjavaclient.SpcConnector;
import de.ascora.spcjavaclient.SpcCremaConnector;
import de.ascora.spcjavaclient.SpcJavaClient;
import de.ascora.spcjavaclient.metadata.MetaData;
import de.ascora.spcjavaclient.metadata.crema.MetaDataCrema;
import de.ascora.spcjavaclient.metadata.crema.PrivateData;
import de.ascora.spcjavaclient.metadata.crema.generic.Preference;
import de.ascora.spcjavaclient.mock.MockMetaData;
import de.ascora.spcjavaclient.mock.MockSpcResponse;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;

/**
 * Created by tobi on 15.09.2015.
 */
public class TestSpcJavaClient {

    private SpcJavaClient spcClient;
    private SpcConnector connector;
    private static final String SOCKET = "127.0.0.1";
    private static final int PORT = 9000;
    private static final String CLIENT_NAME = "JavaTestClient";
    private static final String API_KEY = "59559afbdae9e1075e68fa263057653b";
    private static final String SPC_URL = "http://127.0.0.1:9000/info/api/001";
    private static final String MOCK_ACCESS_TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJfaWQiOiI1NWNkYWI2ZjVhMjNiMWIwMTJkZTU1ZGEiLCJpYXQiOjE0NDExOTcxNTMzMTgsImV4cCI6MTQ0MTIxNTE1MzMxOH0.Gi1eu5OGMyojwMdONMEi7HZrmC90Wq_Q4SIx7MiUz18";
    private static final String TEST_ID = "55cdab6f5a23b1b012de55da";
    private static final String TEST_USER = "Testuser";
    private Preference[] TEST_PREFERENCES = {};
    private String[] optionsValues = {"option1", "option2", "option3"};
    private final String PREF_KEY1 = "email";
    private final String PREF_VAL1 = "testuser@ascora.de";
    private final String PREF_KEY2 = "options";
    private final List PREF_VAL2 = Arrays.asList(optionsValues);
    private final String PREF_KEY3 = "isCustomer";
    private final boolean PREF_VAL3 = true;

    /*
    For local testing.
     */
    private MockSpcResponse mockSpcResponse;
    private MetaDataCrema mockMetaData;
    private Gson gson;


    @Before
    public void setUp() throws Exception {
        connector = new SpcCremaConnector(SPC_URL);
        spcClient = new SpcJavaClient(CLIENT_NAME, API_KEY, SPC_URL, connector);

        mockSpcResponse = new MockSpcResponse();
        mockMetaData = new MockMetaData().getMetaDataCremaInstace();
        gson = new Gson();

        TEST_PREFERENCES = new Preference[]{new Preference<>(PREF_KEY1, PREF_VAL1),
                                            new Preference<>(PREF_KEY2, PREF_VAL2),
                                            new Preference<>(PREF_KEY3, PREF_VAL3)};
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
        SpcJavaClient spcClientMock = new SpcJavaClient(CLIENT_NAME, API_KEY, SPC_URL);
        MetaData result = spcClientMock.requestMetaData(MOCK_ACCESS_TOKEN);
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

    /*
    Testing the SPC response object.
     */
    @Test
    public void testSpcResonseObject() throws IOException {
        MetaData metaData = spcClient.requestMetaData(MOCK_ACCESS_TOKEN);
        assertNotNull(metaData);
    }


    /*
    Testing the MetaData Object
     */
    @Test
    public void testSpcMetaData() throws IOException {
        MetaData metaData = spcClient.requestMetaData(MOCK_ACCESS_TOKEN);
        assertEquals(metaData.getAuthenticatedEntityId(), TEST_ID);
        assertEquals(metaData.getAuthenticatedEntityName(), TEST_USER);
    }

    @Test
    public void testSpcMetaDataCrema() throws IOException {
        MetaDataCrema metaData = (MetaDataCrema) spcClient.requestMetaData(MOCK_ACCESS_TOKEN);
        assertEquals(metaData.getPublicData(), mockMetaData.getPublicData());
        assertEquals(metaData.getPrivateData(), mockMetaData.getPrivateData());
    }

    /*
    Create Private Data for Testuser
     */
    @Test
    public void testCreatePrivateData() throws IOException {
        PrivateData privateData = new PrivateData(TEST_PREFERENCES);
        assertEquals(privateData, mockMetaData.getPrivateData());
        MetaData metaData = new MetaDataCrema(null, null, privateData);
        spcClient.createMetaData(MOCK_ACCESS_TOKEN, metaData);
    }

//    @Test
//    public void testRestRequestMetaData() throws IOException {
//        de.ascora.spcjavaclient.SpcConnector connector = new de.ascora.spcjavaclient.SpcRestConnector(SPC_URL);
//        String result = spcClient.requestMetaData(MOCK_ACCESS_TOKEN, connector);
//        assertEquals(result, gson.toJson(mockSpcResponse));
//    }

}
