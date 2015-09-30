package de.ascora.spcjavaclient;

import de.ascora.spcjavaclient.metadata.MetaData;
import de.ascora.spcjavaclient.metadata.crema.MetaDataCrema;
import de.ascora.spcjavaclient.metadata.crema.PrivateData;
import de.ascora.spcjavaclient.utils.JsonStringParser;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.ClientResource;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

/**
 * Created by tobi on 29.09.2015.
 */
public class SpcCremaConnector implements SpcConnector {

    private String uri;

    public SpcCremaConnector(String uri) {
        this.uri = uri;
    }


    @Override
    public MetaData requestEndpointDocument(String apiKey, String accessToken) throws IOException {
        String authUri = this.uri + "?api_key=" + apiKey + "&access_token=" + accessToken;
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

    @Override
    public void updateEndpointDocument(String apiKey, String accessToken, MetaData data) throws IOException {

    }

    @Override
    public void createEndpointDocument(String apiKey, String accessToken, MetaData data) throws IOException {
        String authUri = this.uri + "/private?api_key=" + apiKey + "&access_token=" + accessToken;
        ClientResource client = new ClientResource(authUri);
        //only private data can be created
        PrivateData privateData = ((MetaDataCrema) data).getPrivateData();
        String json = JsonStringParser.getMetaDataJsonString(privateData);
        JsonRepresentation jsonRepresentation = new JsonRepresentation(json);
        client.post(jsonRepresentation);

//        URL url = new URL(authUri);
//        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//        connection.setRequestMethod("POST");
//        connection.setDoInput(true);
//        connection.setDoOutput(true);
//        connection.setUseCaches(false);
//        connection.setRequestProperty("Content-Type", "application/json");
//        OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
//        writer.write(json);
//        writer.flush();
//
//        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//
//        for (String line; (line = reader.readLine()) != null;) {
//            System.out.println(line);
//        }
//        writer.close();
//        reader.close();
    }

    @Override
    public void deleteEndpointDocument(String apiKey, String accessToken, MetaData data) throws IOException {

    }
}
