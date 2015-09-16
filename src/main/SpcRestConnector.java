import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tobi on 16.09.2015.
 */
public class SpcRestConnector implements SpcConnector {

    private ClientResource clientResource;
    private String uri;


    public SpcRestConnector(String uri) {
        this.uri = uri;
    }

    //TODO: return a List of SpcDocuments from SPC response (Json Documents)
    @Override
    public String requestEndpointDocuments(String apiKey, String accessToken) throws IOException {
        this.uri += "?api_key=" + apiKey + "&access_token=" + accessToken;
        Gson gson = new Gson();
        this.clientResource = new ClientResource(uri);
        Representation representation = clientResource.get();
        List<EntityDocument> documents = new ArrayList<>();
        JsonReader jsonReader = new JsonReader(new InputStreamReader(representation.getStream(), "UTF-8"));
        jsonReader.beginArray();
        while (jsonReader.hasNext()) {
            jsonReader.beginObject();
            EntityDocument document = gson.fromJson(jsonReader, EntityDocument.class);
            documents.add(document);
            jsonReader.endObject();
        }
        jsonReader.endArray();
        return null;
    }

}
