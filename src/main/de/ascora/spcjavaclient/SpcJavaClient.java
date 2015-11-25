package de.ascora.spcjavaclient;

import de.ascora.spcjavaclient.metadata.MetaData;
import de.ascora.spcjavaclient.metadata.MetaDataProject;
import de.ascora.spcjavaclient.metadata.SpcToken;
import de.ascora.spcjavaclient.mock.MockSpcConnectorImpl;

import java.io.IOException;

/**
 * Created by tobi on 15.09.2015.
 */
public class SpcJavaClient {

    private String spcClientId;
    private String spcClientSecret;
    private String spcUrl;
    private String projectId;

    private SpcConnector connector;

    public SpcJavaClient(){}

    public SpcJavaClient(String clientName, String apiKey, String spcUrl, String projectId, SpcConnector connector) {
        this.spcClientId = clientName;
        this.spcClientSecret = apiKey;
        this.spcUrl = spcUrl;
        this.projectId = projectId;
        this.connector = connector;
    }

    /*
    If no connector is defined, use the default one.
     */
    public SpcJavaClient(String spcClientId, String spcClientSecret, String spcUrl, String projectId) {
        this.spcClientId = spcClientId;
        this.spcClientSecret = spcClientSecret;
        this.spcUrl = spcUrl;
        this.projectId = projectId;

        this.connector = new MockSpcConnectorImpl(spcUrl);
    }

    /*
    Client Authentication
     */
    public SpcToken getTokensForId(String id) throws IOException {
        return connector.requestTokensForId(spcClientSecret, id);
    }
    /*
    Rest Methods
     */
    public MetaData requestMetaData(String accessToken) throws IOException {

        return connector.requestMetaData(spcClientSecret, accessToken);
    }

    public void createMetaData(String accessToken, MetaData metaData) throws IOException {
        if (metaData instanceof MetaDataProject) {
            MetaDataProject metaDataProject = (MetaDataProject) metaData;

            if (metaDataProject.getPublicPayload() != null) {
                connector.createPublicPayload(spcClientSecret, accessToken, metaDataProject);
            }

            if (metaDataProject.getPrivatePayload() != null) {
                connector.createPrivatePayload(spcClientSecret, accessToken, metaDataProject);
            }
        }
    }

    public void updateMetaData(String accessToken, MetaData metaData) throws IOException {
        if (metaData instanceof MetaDataProject) {
            MetaDataProject metaDataProject = (MetaDataProject) metaData;

            if (metaDataProject.getPublicPayload() != null) {
                connector.updatePublicPayload(spcClientSecret, accessToken, metaData);
            }

            if (metaDataProject.getPrivatePayload() != null) {
                connector.updatePrivatePayload(spcClientSecret, accessToken, metaDataProject);
            }
        }
    }

    public void deletePublicMetaData(String accessToken) throws IOException {
        connector.deletePublicPayload(spcClientSecret, accessToken);
    }

    public void deletePrivateMetaData(String accessToken) throws IOException {
        connector.deletePrivatePayload(spcClientSecret, accessToken);
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
