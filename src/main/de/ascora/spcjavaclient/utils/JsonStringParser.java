package de.ascora.spcjavaclient.utils;

import com.google.gson.*;
import de.ascora.spcjavaclient.metadata.*;
import de.ascora.spcjavaclient.metadata.MetaDataProject;
import de.ascora.spcjavaclient.metadata.PrivatePayload;
import de.ascora.spcjavaclient.metadata.PublicPayload;

/**
 * Created by tobi on 29.09.2015.
 */
public class JsonStringParser {

    public static MetaData getMetaDataObject(String response) {
        Gson gson = new Gson();
        Entity entity = null;
        PublicPayload publicPayload = null;
        PrivatePayload privatePayload = null;
        JsonObject jsonObject = new JsonParser().parse(response).getAsJsonObject();
        if (!jsonObject.isJsonNull()) {
            if (jsonObject.has("entity") && jsonObject.getAsJsonObject("entity") != null) {
                JsonObject entityJson = jsonObject.getAsJsonObject("entity");
                entity = gson.fromJson(entityJson.toString(), Entity.class);
            }
            if (jsonObject.has("publicPayload") && !jsonObject.get("publicPayload").isJsonNull()) {
                JsonObject publicJson = jsonObject.getAsJsonObject("publicPayload");
                publicPayload = gson.fromJson(publicJson.toString(), PublicPayload.class);
            }
            if (jsonObject.has("privatePayload") && !jsonObject.get("privatePayload").isJsonNull()) {
                JsonArray privateJson = jsonObject.getAsJsonArray("privatePayload");
                privatePayload = gson.fromJson(privateJson.get(0), PrivatePayload.class);
            }
            return new MetaDataProject(entity, publicPayload, privatePayload);
        }

        return null;
    }

    public static String getMetaDataJsonString(Object metaData) {
        Gson gson = new Gson();
        String jsonMetaData = gson.toJson(metaData);

        return jsonMetaData;
    }

    public static SpcToken getTokenObject(String response) {
        JsonObject jsonObject = new JsonParser().parse(response).getAsJsonObject();
        if (jsonObject != null) {
            AccessToken accessToken = new AccessToken(jsonObject.getAsJsonPrimitive("access_token").getAsString());
            RefreshToken refreshToken = new RefreshToken(jsonObject.getAsJsonPrimitive("refresh_token").getAsString());

            SpcToken spcToken = new SpcToken(accessToken, refreshToken);

            return spcToken;
        }
        return null;
    }
}
