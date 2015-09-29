package de.ascora.spcjavaclient;

import com.google.gson.Gson;
import de.ascora.spcjavaclient.metadata.MetaData;
import de.ascora.spcjavaclient.metadata.crema.MetaDataCrema;
import de.ascora.spcjavaclient.metadata.crema.PrivateData;
import de.ascora.spcjavaclient.utils.JsonStringParser;

import java.io.IOException;
import java.io.InputStream;
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
        String authUri = this.uri + "private?api_key=" + apiKey + "&access_token=" + accessToken;
        //only private data can be created
        PrivateData privateData = ((MetaDataCrema) data).getPrivateData();
        Gson gson = new Gson();
        String json = gson.toJson(privateData);
        //todo: connect to SPC an send a POST request with json data.
    }

    @Override
    public void deleteEndpointDocument(String apiKey, String accessToken, MetaData data) throws IOException {

    }
}
