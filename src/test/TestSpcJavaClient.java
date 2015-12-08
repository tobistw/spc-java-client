import static org.junit.Assert.*;

import com.google.gson.Gson;
import de.ascora.spcjavaclient.SpcConnector;
import de.ascora.spcjavaclient.SpcCremaConnector;
import de.ascora.spcjavaclient.SpcJavaClient;
import de.ascora.spcjavaclient.metadata.*;
import de.ascora.spcjavaclient.metadata.generic.Preference;
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
    private static final String API_KEY = "Ub3NZVo0OEzDAh";
    private static final String SPC_URL = "http://127.0.0.1:9000/api/auth";
    private static final String PROJECT_ID = "/001";
    private static String ACCESS_TOKEN = null;
    private static String REFRESH_TOKEN = null;
    private static final String TEST_ID = "5655760828e5262c1e483bca";
    private static final String TEST_USER = "JavaUser";
    private static final String[] TEST_ROLES = {"guest", "user"};
    private static final String PUBLIC_ADDRESS = "Java-Street 1";
    private static final String PUBLIC_COMPANY = "Oracle";
    private static final String PUBLIC_FACTORY = "USA, California";
    private Entity entity;
    private PublicPayload publicPayload;
    private PrivatePayload privatePayload;
    private MetaDataProject currentMetaData;
    private Preference[] TEST_PREFERENCES = {};
    private String[] optionsValues = {"option1", "option2", "option3"};
    private final String PREF_KEY1 = "email";
    private final String PREF_VAL1 = "java.user@ascora.de";
    private final String PREF_KEY2 = "options";
    private final List PREF_VAL2 = Arrays.asList(optionsValues);
    private final String PREF_KEY3 = "isCustomer";
    private final boolean PREF_VAL3 = true;
    private final String UPDATE_PRIVATE_KEY1 = "color";
    private final String UPDATE_PRIVATE_VAL1 = "red";
    private final String UPDATE_PRIVATE_KEY2 = "email";
    private final String UPDATE_PRIVATE_VAL2 = "java.update@ascora.de";
    private final String UPDATE_PUBLIC_FIELD = "address";
    private final String UPDATE_PUBLIC_VALUE = "Update-Street 1";

    /*
    For local testing.
     */
    private MockSpcResponse mockSpcResponse;
    private MetaDataProject mockMetaData;
    private Gson gson;


    @Before
    public void setUp() throws Exception {
        connector = new SpcCremaConnector(SPC_URL, PROJECT_ID);
        spcClient = new SpcJavaClient(CLIENT_NAME, API_KEY, SPC_URL, PROJECT_ID, connector);

        mockSpcResponse = new MockSpcResponse();
        mockMetaData = new MockMetaData().getMetaDataCremaInstace();
        gson = new Gson();

        entity = new Entity(TEST_ID, TEST_USER, TEST_ROLES);
        publicPayload = new PublicPayload(PUBLIC_ADDRESS, PUBLIC_COMPANY, PUBLIC_FACTORY);
        TEST_PREFERENCES = new Preference[]{new Preference<>(PREF_KEY1, PREF_VAL1),
                                            new Preference<>(PREF_KEY2, PREF_VAL2),
                                            new Preference<>(PREF_KEY3, PREF_VAL3)};
        privatePayload = new PrivatePayload(TEST_PREFERENCES);
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
    //@Test
    //public void test99_MockRequestMetaData() throws IOException {
    //    SpcJavaClient spcClientMock = new SpcJavaClient(CLIENT_NAME, API_KEY, SPC_URL);
    //    MetaData result = spcClientMock.requestMetaData(MOCK_ACCESS_TOKEN);
    //    assertEquals(result, mockMetaData);
    //}


    /*
    Testing the connection to the SPC. Note: run mongodb and grunt serve
     */
    @Test
    public void test2_SpcConnection() throws IOException {
        Socket socket = new Socket(SOCKET, PORT);
        assertTrue(socket.isConnected());
    }

    /*
    Testing authentication in behalf of the Test User.
     */
    @Test
    public void test3_SpcAuthenticateForId() throws IOException {
        SpcToken tokens = spcClient.getTokensForId(TEST_ID);
        ACCESS_TOKEN = tokens.getAccessTokenString();
        REFRESH_TOKEN = tokens.getRefreshTokenString();
        assertNotNull(ACCESS_TOKEN);
        assertNotNull(REFRESH_TOKEN);
        System.out.println("GOT NEW ACCESS-TOKEN: " + ACCESS_TOKEN);
        System.out.println("GOT NEW REFRESH-TOKEN: " + REFRESH_TOKEN);
        //todo: Validation of JWT
    }

    /*
    Create, update, delete Private Data for Testuser
     */
    @Test
    public void test4_CreatePublicPayload() throws IOException {
        MetaData metaData = new MetaDataProject(null, publicPayload, null);
        assertEquals(((MetaDataProject) metaData).getPublicPayload(), publicPayload);
        spcClient.createMetaData(ACCESS_TOKEN, metaData);
        System.out.println("NEW PUBLIC PAYLOAD CREATED: " + ((MetaDataProject) metaData).getPublicPayload());
    }

    @Test
    public void test5_CreatePrivatePayload() throws IOException {
        MetaData metaData = new MetaDataProject(null, null, privatePayload);
        assertEquals(((MetaDataProject) metaData).getPrivatePayload(), privatePayload);
        spcClient.createMetaData(ACCESS_TOKEN, metaData);

        System.out.println("NEW PRIVATE PAYLOAD CREATED: " + ((MetaDataProject) metaData).getPrivatePayload());
    }

    /*
    Testing CREMA specific basic information.
     */
    @Test
    public void test6_SpcMetaDataProject() throws IOException {
        currentMetaData = (MetaDataProject) spcClient.requestMetaData(ACCESS_TOKEN);
        System.out.println("REQUEST META DATA FOR JAVA-USER");
        assertEquals(entity, currentMetaData.getEntity());
        System.out.println(currentMetaData.getEntity().toString());
        assertEquals(publicPayload, currentMetaData.getPublicPayload());
        System.out.println(currentMetaData.getPublicPayload().toString());
        assertEquals(privatePayload, currentMetaData.getPrivatePayload());
        System.out.println(currentMetaData.getPrivatePayload().toString());
    }

    @Test
    public void test7_UpdatePublicPayload() throws IOException {
        MetaDataProject metaDataProject;
        currentMetaData = (MetaDataProject) spcClient.requestMetaData(ACCESS_TOKEN);
        assertNotNull(currentMetaData);
        assertTrue(currentMetaData.updatePublicPayload(UPDATE_PUBLIC_FIELD, UPDATE_PUBLIC_VALUE));
        metaDataProject = new MetaDataProject(null, currentMetaData.getPublicPayload(), null);
        spcClient.updateMetaData(ACCESS_TOKEN, metaDataProject);
        System.out.println("PUBLIC PAYLOAD UPDATED: " + metaDataProject.getPublicPayload());
    }

    @Test
    public void test8_UpdatePrivateData() throws IOException {
        MetaDataProject metaDataProject;
        currentMetaData = (MetaDataProject) spcClient.requestMetaData(ACCESS_TOKEN);
        assertNotNull(currentMetaData);
        assertTrue(currentMetaData.updatePrivatePayload(new Preference<>(UPDATE_PRIVATE_KEY1, UPDATE_PRIVATE_VAL1)));
        assertTrue(currentMetaData.updatePrivatePayload(new Preference<>(UPDATE_PRIVATE_KEY2, UPDATE_PRIVATE_VAL2)));
        metaDataProject = new MetaDataProject(null, null, currentMetaData.getPrivatePayload());
        spcClient.updateMetaData(ACCESS_TOKEN, metaDataProject);
        System.out.println("PRIVATE PAYLOAD UPDATED: " + metaDataProject.getPrivatePayload());
    }

    @Test
    public void test90_PrivatePayloadPermissionCheck() throws IOException {
        currentMetaData = (MetaDataProject) spcClient.requestMetaData(ACCESS_TOKEN);
        Map permissionMap = currentMetaData.getPrivatePayload().getPreferenceAsMap();
        assertNotNull(permissionMap);
        System.out.println(permissionMap);
        assertTrue(permissionMap.get(UPDATE_PRIVATE_KEY1).equals(UPDATE_PRIVATE_VAL1));
        assertFalse(permissionMap.get(UPDATE_PRIVATE_KEY2).equals(PREF_VAL1));
        assertTrue(permissionMap.get(UPDATE_PRIVATE_KEY2).equals(UPDATE_PRIVATE_VAL2));

        System.out.println("PERMISSION CHECK SUCCESSFUL FOR: " + currentMetaData.getAuthenticatedEntityName());
    }

    @Test
    public void test91_DeleteMetaData() throws IOException {
        spcClient.deletePublicMetaData(ACCESS_TOKEN);
        spcClient.deletePrivateMetaData(ACCESS_TOKEN);

        currentMetaData = (MetaDataProject) spcClient.requestMetaData(ACCESS_TOKEN);
        assertEquals(entity, currentMetaData.getEntity());
        assertNull(currentMetaData.getPublicPayload());
        assertNull(currentMetaData.getPrivatePayload());

        System.out.println("META DATA DELETED");
    }
}
