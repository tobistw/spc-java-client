import com.google.gson.Gson;

/**
 * Created by tobi on 15.09.2015.
 */
public interface SpcConnector {

    public void setAuthParameter(String apiKey, String accessToken);

    public String[] requestEndpointToString(String endpoint);

    public String requestEndpointToJson(String endpoint);

}
