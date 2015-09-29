package de.ascora.spcjavaclient;

import de.ascora.spcjavaclient.metadata.MetaData;

import java.io.IOException;

/**
 * Created by tobi on 15.09.2015.
 */
public interface SpcConnector {

    public MetaData requestEndpointDocument(String apiKey, String accessToken) throws IOException;

    public void updateEndpointDocument(String apiKey, String accessToken, MetaData data) throws IOException;

    public void createEndpointDocument(String apiKey, String accessToken, MetaData data) throws IOException;

    public void deleteEndpointDocument(String apiKey, String accessToken, MetaData data) throws IOException;

}
