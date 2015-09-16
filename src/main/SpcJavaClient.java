import java.io.IOException;

/**
 * Created by tobi on 15.09.2015.
 */
public class SpcJavaClient {

    private String spcClientId;
    private String spcClientSecret;
    private String spcUrl;

    private SpcConnector connector;

    public SpcJavaClient(){}

    public SpcJavaClient(String clientName, String apiKey, String spcUrl) {
        this.spcClientId = clientName;
        this.spcClientSecret = apiKey;
        this.spcUrl = spcUrl;
    }


    public String requestMetaData(String accessToken) throws IOException {
        connector = new MockSpcConnectorImpl(spcUrl);

        return connector.requestEndpointDocuments(spcClientSecret, accessToken);
    }

    public String requestMetaData(String accessToken, SpcConnector connector) throws IOException {
        this.connector = connector;

        return this.connector.requestEndpointDocuments(spcClientSecret, accessToken);
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

}
