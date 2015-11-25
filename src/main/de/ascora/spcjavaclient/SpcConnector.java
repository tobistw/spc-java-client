package de.ascora.spcjavaclient;

import de.ascora.spcjavaclient.metadata.MetaData;
import de.ascora.spcjavaclient.metadata.SpcToken;

import java.io.IOException;

/**
 * Created by tobi on 15.09.2015.
 */
public interface SpcConnector {

    public SpcToken requestTokensForId(String apiKey, String id) throws IOException;

    public MetaData requestMetaData(String apiKey, String accessToken) throws IOException;

    public void updatePublicPayload(String apiKey, String accessToken, MetaData data) throws IOException;

    public void createPublicPayload(String apiKey, String accessToken, MetaData data) throws IOException;

    public void deletePublicPayload(String apiKey, String accessToken) throws IOException;

    public void updatePrivatePayload(String apiKey, String accessToken, MetaData data) throws IOException;

    public void createPrivatePayload(String apiKey, String accessToken, MetaData data) throws IOException;

    public void deletePrivatePayload(String apiKey, String accessToken) throws IOException;

}
