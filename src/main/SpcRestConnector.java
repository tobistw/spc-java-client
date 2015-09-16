import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;

import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by tobi on 16.09.2015.
 */
public class SpcRestConnector implements SpcConnector {

    private ClientResource clientResource;
    private String uri;


    public SpcRestConnector(String uri) {
        this.uri = uri;
    }

    @Override
    public void setAuthParameter(String apiKey, String accessToken) {
        this.uri += "?api_key=" + apiKey + "&access_token=" + accessToken;
    }

    //TODO: return a String from Json Document
    @Override
    public String requestEndpointToJson() throws IOException {
        Gson gson = new Gson();
        this.clientResource = new ClientResource(uri);
        Representation representation = clientResource.get();
        JsonReader jsonReader = new JsonReader(new InputStreamReader(representation.getStream(), "UTF-8"));
        jsonReader.beginArray();
        while (jsonReader.hasNext()) {
            System.out.println(jsonReader.nextString());
        }
        return null;
    }
}
