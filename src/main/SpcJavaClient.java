/**
 * Created by tobi on 15.09.2015.
 */
public class SpcJavaClient {

    private String spcClientId;
    private String spcClientSecret;
    private String spcUrl;
    private String currentAccessToken;

    private SpcConnector spcConnector;

    public SpcJavaClient(){}

    public SpcJavaClient(String spcClientId, String spcClientSecret, String spcUrl) {
        this.spcClientId = spcClientId;
        this.spcClientSecret = spcClientSecret;
        this.spcUrl = spcUrl;
    }


    public String[] requestMetaData(String accessToken) {
        spcConnector = new MockSpcConnectorImpl();
        spcConnector.setAuthParameter(spcClientSecret, accessToken);

        return spcConnector.requestEndpoint(spcUrl);
    }

    /*
    Setter
     */
    public void setSpcClientId(String spcClientId) {
        this.spcClientId = spcClientId;
    }

    public void setSpcClientSecret(String spcClientSecret) {
        this.spcClientSecret = spcClientSecret;
    }

    public void setSpcUrl(String spcUrl) {
        this.spcUrl = spcUrl;
    }

    public void setCurrentAccessToken(String currentAccessToken) {
        this.currentAccessToken = currentAccessToken;
    }

    /*
    Getter
     */
    public String getSpcClientId() {
        return spcClientId;
    }

    public String getSpcClientSecret() {
        return spcClientSecret;
    }

    public String getSpcUrl() {
        return spcUrl;
    }

    public String getCurrentAccessToken() {
        return currentAccessToken;
    }
}
