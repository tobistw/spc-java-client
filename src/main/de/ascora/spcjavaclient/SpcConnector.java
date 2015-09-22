package de.ascora.spcjavaclient;

import de.ascora.spcjavaclient.metadata.MetaData;

import java.io.IOException;

/**
 * Created by tobi on 15.09.2015.
 */
public interface SpcConnector {

    //todo: return type must be List<de.ascora.spcjavaclient.EntityDocument>
    public MetaData requestEndpointDocument(String apiKey, String accessToken) throws IOException;

}
