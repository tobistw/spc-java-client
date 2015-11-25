package de.ascora.spcjavaclient.mock;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import de.ascora.spcjavaclient.SpcConnector;
import de.ascora.spcjavaclient.metadata.Entity;
import de.ascora.spcjavaclient.metadata.MetaData;
import de.ascora.spcjavaclient.metadata.SpcToken;
import de.ascora.spcjavaclient.metadata.MetaDataProject;
import de.ascora.spcjavaclient.metadata.PrivatePayload;
import de.ascora.spcjavaclient.metadata.PublicPayload;
import de.ascora.spcjavaclient.metadata.generic.Key;
import de.ascora.spcjavaclient.metadata.generic.Value;
import de.ascora.spcjavaclient.utils.JsonStringParser;

import java.io.IOException;
import java.io.Reader;

/**
 * Created by tobi on 15.09.2015.
 */
public class MockSpcConnectorImpl implements SpcConnector {

    private String uri;
    private MockSpcResponse mockSpcResponse;

    private static final String SPC_REQUEST_URL = "http://127.0.0.1:9000/info/api/001";
    private static final String SPC_TEST_AUTH = "?api_key=59559afbdae9e1075e68fa263057653b&access_token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJfaWQiOiI1NWNkYWI2ZjVhMjNiMWIwMTJkZTU1ZGEiLCJpYXQiOjE0NDExOTcxNTMzMTgsImV4cCI6MTQ0MTIxNTE1MzMxOH0.Gi1eu5OGMyojwMdONMEi7HZrmC90Wq_Q4SIx7MiUz18";


    public MockSpcConnectorImpl(String uri) {
        this.uri = uri;
        mockSpcResponse = new MockSpcResponse();
    }


    @Override
    public SpcToken requestTokensForId(String apiKey, String id) throws IOException {
        return null;
    }

    @Override
    public MetaData requestMetaData(String apiKey, String accessToken) throws IOException {
        this.uri += "?api_key=" + apiKey + "&access_token=" + accessToken;
        String response;
        Gson gson = new Gson();

        if (executeRequest(uri)) {
            response = mockSpcResponse.toString();
            MetaData metaData = JsonStringParser.getMetaDataObject(response);


            return metaData;
        }

        //return "401";
        return null;
    }

    @Override
    public void updatePublicPayload(String apiKey, String accessToken, MetaData data) throws IOException {

    }

    @Override
    public void createPublicPayload(String apiKey, String accessToken, MetaData data) throws IOException {

    }

    @Override
    public void deletePublicPayload(String apiKey, String accessToken) throws IOException {

    }

    @Override
    public void updatePrivatePayload(String apiKey, String accessToken, MetaData data) throws IOException {

    }

    @Override
    public void createPrivatePayload(String apiKey, String accessToken, MetaData data) throws IOException {

    }

    @Override
    public void deletePrivatePayload(String apiKey, String accessToken) throws IOException {

    }


    private boolean executeRequest(String url) {
        return url.equals(SPC_REQUEST_URL + SPC_TEST_AUTH);
    }

    private MetaData readJsonString(Reader reader) throws IOException {
        JsonReader jsonReader = new JsonReader(reader);
        try {
            return readMetaDocuments(jsonReader);
        } finally {
            jsonReader.close();
        }
    }


    private MetaData readMetaDocuments(JsonReader reader) throws IOException {
        String id = null;
        String entityName = null;
        String[] roles = null;
        String address = null;
        String company = null;
        String factory = null;
        PrivatePayload privatePayload = null;

        reader.beginArray();
        reader.beginObject();

        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("_id")) {
                id = reader.nextString();
            } else if (name.equals("name")) {
                entityName = reader.nextString();
            } else if (name.equals("roles")) {
                //roles = reader.nextString();
            } else if (name.equals("address")) {
                address = reader.nextString();
            } else if (name.equals("company")) {
                company = reader.nextString();
            } else if (name.equals("factory")) {
                factory = reader.nextString();
            } else if (name.equals("preferences")) {
                privatePayload = readPreferences(reader);
            } else {
                reader.skipValue();
            }
        }

        reader.endObject();
        reader.endArray();

        Entity entity = new Entity(id, entityName, roles);
        PublicPayload publicPayload = new PublicPayload(address, company, factory);

        return new MetaDataProject(entity, publicPayload, privatePayload);
    }

    private PrivatePayload readPreferences(JsonReader reader) throws IOException {
        Key<String> key = null;

        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("key")) {
                key = new Key<>(reader.nextString());
            } else if (name.equals("value")) {
                if (reader.nextString() != null) {
                    Value<String> value = new Value<>(reader.nextString());
                } else if (reader.hasNext()) {
                    reader.beginObject();

                    reader.endObject();
                }
            }
        }
        return null;
    }
}
