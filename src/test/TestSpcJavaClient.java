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
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by tobi on 15.09.2015.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
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
    private final String UPDATE_KEY1 = "color";
    private final String UPDATE_VAL1 = "red";
    private final String UPDATE_KEY2 = "email";
    private final String UPDATE_VAL2 = "testuser@ascora.de";

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
    Basic test for SpcJavaClient
     */
    @Test
    public void test1_SpcJavaClientPreferences() {
        assertEquals(spcClient.getSpcClientId(), CLIENT_NAME);
        assertEquals(spcClient.getSpcClientSecret(), API_KEY);
        assertEquals(spcClient.getSpcUrl(), SPC_URL);
    }


    /*
    Client calls internal mock entity information
     */
    @Test
    public void test2_MockRequestMetaData() throws IOException {
        SpcJavaClient spcClientMock = new SpcJavaClient(CLIENT_NAME, API_KEY, SPC_URL);
        MetaData result = spcClientMock.requestMetaData(MOCK_ACCESS_TOKEN);
        assertEquals(result, mockMetaData);
    }


    /*
    Testing the connection to the SPC. Note: run mongodb and grunt serve
     */
    @Test
    public void test3_SpcConnection() throws IOException {
        Socket socket = new Socket(SOCKET, PORT);
        assertTrue(socket.isConnected());
    }

    /*
    Testing the MetaData Object.
     */
    @Test
    public void test4_SpcMetaData() throws IOException {
        MetaData metaData = spcClient.requestMetaData(MOCK_ACCESS_TOKEN);
        assertNotNull(metaData);
        assertEquals(metaData.getAuthenticatedEntityId(), TEST_ID);
        assertEquals(metaData.getAuthenticatedEntityName(), TEST_USER);
    }


    /*
    Testing CREMA specific basic information.
     */
    @Test
    public void test5_SpcMetaDataCrema() throws IOException {
        MetaDataCrema metaData = (MetaDataCrema) spcClient.requestMetaData(MOCK_ACCESS_TOKEN);
        assertEquals(metaData.getEntity(), mockMetaData.getEntity());
        assertEquals(metaData.getPublicData(), mockMetaData.getPublicData());
    }

    /*
    Create, update, delete Private Data for Testuser
     */
    @Test
    public void test6_CreatePrivateData() throws IOException {
        PrivateData privateData = new PrivateData(TEST_PREFERENCES);
        assertEquals(privateData, mockMetaData.getPrivateData());
        MetaData metaData = new MetaDataCrema(null, null, privateData);
        spcClient.createMetaData(MOCK_ACCESS_TOKEN, metaData);
    }

    @Test
    public void test7_UpdatePrivateData() throws IOException {
        MetaDataCrema metaData = (MetaDataCrema) spcClient.requestMetaData(MOCK_ACCESS_TOKEN);

        assertTrue(metaData.updatePrivateData(new Preference<>(UPDATE_KEY1, UPDATE_VAL1)));
        assertTrue(metaData.updatePrivateData(new Preference<>(UPDATE_KEY2, UPDATE_VAL2)));

        spcClient.updateMetaData(MOCK_ACCESS_TOKEN, metaData);
    }

    @Test
    public void test8_PrivateDataPermissionCheck() throws IOException {
        MetaDataCrema metaData = (MetaDataCrema) spcClient.requestMetaData(MOCK_ACCESS_TOKEN);
        Map permissionMap = metaData.getPrivateData().getPreferenceAsMap();
        assertTrue(permissionMap.get(UPDATE_KEY1).equals(UPDATE_VAL1));
        assertTrue(permissionMap.get(UPDATE_KEY2).equals(UPDATE_VAL2));
    }

    @Test
    public void test9_DeletePrivateData() throws IOException {
        spcClient.deleteMetaData(MOCK_ACCESS_TOKEN);
    }

}
