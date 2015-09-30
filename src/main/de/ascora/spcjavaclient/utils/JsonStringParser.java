package de.ascora.spcjavaclient.utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import de.ascora.spcjavaclient.metadata.Entity;
import de.ascora.spcjavaclient.metadata.MetaData;
import de.ascora.spcjavaclient.metadata.crema.MetaDataCrema;
import de.ascora.spcjavaclient.metadata.crema.PrivateData;
import de.ascora.spcjavaclient.metadata.crema.PublicData;

/**
 * Created by tobi on 29.09.2015.
 */
public class JsonStringParser {

    public static MetaData getMetaDataObject(String response) {
        Gson gson = new Gson();
        JsonArray jsonArray = new JsonParser().parse(response).getAsJsonArray();
        if (jsonArray.size() == 3) {
            Entity entity = gson.fromJson(jsonArray.get(0), Entity.class);
            PublicData publicData = gson.fromJson(jsonArray.get(1), PublicData.class);
            PrivateData privateData = gson.fromJson(jsonArray.get(2), PrivateData.class);

            return new MetaDataCrema(entity, publicData, privateData);
        } else if (jsonArray.size() == 2) {
            Entity entity = gson.fromJson(jsonArray.get(0), Entity.class);
            PublicData publicData = gson.fromJson(jsonArray.get(1), PublicData.class);

            return new MetaDataCrema(entity, publicData);
        } else if (jsonArray.size() == 1) {
            Entity entity = gson.fromJson(jsonArray.get(0), Entity.class);

            return new MetaDataCrema(entity);
        }

        return null;
    }

    public static String getMetaDataJsonString(Object metaData) {
        Gson gson = new Gson();
        String jsonMetaData = gson.toJson(metaData);

        return jsonMetaData;
    }
}
