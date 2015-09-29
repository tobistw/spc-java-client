package de.ascora.spcjavaclient;

import de.ascora.spcjavaclient.metadata.MetaData;
import de.ascora.spcjavaclient.mock.MockSpcConnectorImpl;

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

    public SpcJavaClient(String clientName, String apiKey, String spcUrl, SpcConnector connector) {
        this.spcClientId = clientName;
        this.spcClientSecret = apiKey;
        this.spcUrl = spcUrl;
        this.connector = connector;
    }

    /*
    If no connector is defined, use the default one.
     */
    public SpcJavaClient(String spcClientId, String spcClientSecret, String spcUrl) {
        this.spcClientId = spcClientId;
        this.spcClientSecret = spcClientSecret;
        this.spcUrl = spcUrl;

        this.connector = new MockSpcConnectorImpl(spcUrl);
    }

    /*
    Rest Methods
     */
    public MetaData requestMetaData(String accessToken) throws IOException {

        return connector.requestEndpointDocument(spcClientSecret, accessToken);
    }

    public void createMetaData(String accessToken, MetaData metaData) throws IOException {
        connector.createEndpointDocument(spcClientSecret, accessToken, metaData);
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
