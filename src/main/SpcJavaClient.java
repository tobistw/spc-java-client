import java.io.IOException;

/**
 * Created by tobi on 15.09.2015.
 */
public class SpcJavaClient {

    private String spcClientId;
    private String spcClientSecret;
    private String spcUrl;

    private SpcConnector spcConnector;

    public SpcJavaClient(){}

    public SpcJavaClient(String spcClientId, String spcClientSecret, String spcUrl) {
        this.spcClientId = spcClientId;
        this.spcClientSecret = spcClientSecret;
        this.spcUrl = spcUrl;
    }


    public String requestMetaData(String accessToken, SpcConnector connector) throws IOException {
        spcConnector = connector;
        spcConnector.setAuthParameter(spcClientSecret, accessToken);

        return spcConnector.requestEndpointToJson();
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
