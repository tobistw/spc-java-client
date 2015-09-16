import com.google.gson.Gson;

import java.io.IOException;

/**
 * Created by tobi on 15.09.2015.
 */
public interface SpcConnector {

    //todo: return type must be List<EntityDocument>
    public String requestEndpointDocuments(String apiKey, String accessToken) throws IOException;

}
