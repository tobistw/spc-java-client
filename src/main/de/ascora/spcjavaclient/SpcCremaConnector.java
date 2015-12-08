package de.ascora.spcjavaclient;

import de.ascora.spcjavaclient.metadata.*;
import de.ascora.spcjavaclient.metadata.generic.Preference;
import de.ascora.spcjavaclient.utils.JsonStringParser;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.ClientResource;

import java.io.*;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by tobi on 29.09.2015.
 */
public class SpcCremaConnector implements SpcConnector {

    private String uri;
    private String projectId;

    public SpcCremaConnector(String uri, String projectId) {

        this.uri = uri;
        this.projectId = projectId;
    }


    @Override
    public SpcToken requestTokensForId(String apiKey, String id) throws IOException {
        String authUri = this.uri + "/token/" + id + "?apikey=" + apiKey;
        InputStream inputStream = null;
        SpcToken tokens = null;

        try {
            URL url = new URL(authUri);
            inputStream = url.openStream();
            String response = (new Scanner(inputStream, "UTF-8").useDelimiter("\\Z").next());
            tokens = JsonStringParser.getTokenObject(response);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return tokens;
    }

    @Override
    public MetaData requestMetaData(String apiKey, String accessToken) throws IOException {
        String authUri = this.uri + this.projectId + "?apikey=" + apiKey + "&access_token=" + accessToken;
        InputStream inputStream = null;
        MetaData metaData = null;

        try {
            URL url = new URL(authUri);
            inputStream = url.openStream();
            String response = (new Scanner(inputStream, "UTF-8").useDelimiter("\\Z").next());
            metaData = JsonStringParser.getMetaDataObject(response);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return metaData;
    }

    /*
    The following methods only handling Private Data
     */
    @Override
    public void updatePublicPayload(String apiKey, String accessToken, MetaData data) throws IOException {
        if (data != null) {

            String authUri = this.uri + "/public?apikey=" + apiKey + "&access_token=" + accessToken;
            ClientResource client = new ClientResource(authUri);
            PublicPayload publicPayload = ((MetaDataProject) data).getPublicPayload();

            String json = JsonStringParser.getMetaDataJsonString(publicPayload);
            JsonRepresentation jsonRepresentation = new JsonRepresentation(json);
            client.put(jsonRepresentation);
        }
    }

    @Override
    public void createPublicPayload(String apiKey, String accessToken, MetaData data) throws IOException {
        if (data != null) {
            String authUri = this.uri + "/public?apikey=" + apiKey + "&access_token=" + accessToken;
            ClientResource client = new ClientResource(authUri);
            PublicPayload publicPayload = ((MetaDataProject) data).getPublicPayload();

            String json = JsonStringParser.getMetaDataJsonString(publicPayload);
            JsonRepresentation jsonRepresentation = new JsonRepresentation(json);
            client.post(jsonRepresentation);
        }
    }

    @Override
    public void deletePublicPayload(String apiKey, String accessToken) throws IOException {
        String authUri = this.uri + "/public?apikey=" + apiKey + "&access_token=" + accessToken;
        ClientResource client = new ClientResource(authUri);

        client.delete();
    }

    @Override
    public void updatePrivatePayload(String apiKey, String accessToken, MetaData data) throws IOException {
        if (data != null) {

            String authUri = this.uri + "/private?apikey=" + apiKey + "&access_token=" + accessToken;
            ClientResource client = new ClientResource(authUri);
            Preference[] preferences = ((MetaDataProject) data).getPrivatePayload().getPreferences();

            String json = JsonStringParser.getMetaDataJsonString(preferences);
            JsonRepresentation jsonRepresentation = new JsonRepresentation(json);
            client.put(jsonRepresentation);
        }
    }

    @Override
    public void createPrivatePayload(String apiKey, String accessToken, MetaData data) throws IOException {
        if (data != null) {
            String authUri = this.uri + "/private?apikey=" + apiKey + "&access_token=" + accessToken;
            ClientResource client = new ClientResource(authUri);
            PrivatePayload privatePayload = ((MetaDataProject) data).getPrivatePayload();

            String json = JsonStringParser.getMetaDataJsonString(privatePayload);
            JsonRepresentation jsonRepresentation = new JsonRepresentation(json);
            client.post(jsonRepresentation);
        }
    }

    @Override
    public void deletePrivatePayload(String apiKey, String accessToken) throws IOException {
        String authUri = this.uri + "/private?apikey=" + apiKey + "&access_token=" + accessToken;
        ClientResource client = new ClientResource(authUri);

        client.delete();
    }
}
