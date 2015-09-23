package de.ascora.spcjavaclient.mock;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import de.ascora.spcjavaclient.SpcConnector;
import de.ascora.spcjavaclient.metadata.Entity;
import de.ascora.spcjavaclient.metadata.MetaData;
import de.ascora.spcjavaclient.metadata.crema.MetaDataCrema;
import de.ascora.spcjavaclient.metadata.crema.PrivateData;
import de.ascora.spcjavaclient.metadata.crema.PublicData;
import de.ascora.spcjavaclient.metadata.crema.generic.Key;
import de.ascora.spcjavaclient.metadata.crema.generic.Value;
import jdk.nashorn.internal.parser.JSONParser;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public MetaData requestEndpointDocument(String apiKey, String accessToken) throws IOException {
        this.uri += "?api_key=" + apiKey + "&access_token=" + accessToken;
        String response;
        Gson gson = new Gson();

        //todo: Parsing json object to PrivateData
        if (executeRequest(uri)) {
            response = mockSpcResponse.toString();
            JsonParser jsonParser = new JsonParser();
            JsonArray jsonArray = jsonParser.parse(response).getAsJsonArray();
            Entity entity = gson.fromJson(jsonArray.get(0), Entity.class);
            PublicData publicData = gson.fromJson(jsonArray.get(1), PublicData.class);
            JsonObject jsonObject = jsonArray.get(2).getAsJsonObject();
            JsonArray array = jsonObject.getAsJsonArray("preferences");
            Type type = new TypeToken<Map<Key, Value>>(){}.getType();
            Map<Key, Value> map = gson.fromJson(array, type);


            PrivateData privateData = gson.fromJson(array, PrivateData.class);
            //Reader sr = new StringReader(response);
            //return readJsonString(sr);
            return new MetaDataCrema(entity, publicData, privateData);
        }

        //return "401";
        return null;
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
        String company = null;
        String factory = null;
        PrivateData privateData = null;

        reader.beginArray();
        reader.beginObject();

        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("_id")) {
                id = reader.nextString();
            } else if (name.equals("name")) {
                entityName = reader.nextString();
            } else if (name.equals("company")) {
                company = reader.nextString();
            } else if (name.equals("factory")) {
                factory = reader.nextString();
            } else if (name.equals("preferences")) {
                privateData = readPreferences(reader);
            } else {
                reader.skipValue();
            }
        }

        reader.endObject();
        reader.endArray();

        Entity entity = new Entity(id, entityName);
        PublicData publicData = new PublicData(company, factory);

        return new MetaDataCrema(entity, publicData, privateData);
    }

    private PrivateData readPreferences(JsonReader reader) throws IOException {
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
