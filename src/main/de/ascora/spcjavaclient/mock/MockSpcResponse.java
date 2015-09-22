package de.ascora.spcjavaclient.mock;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tobi on 15.09.2015.
 */
public class MockSpcResponse {
    private String _id = "55cdab6f5a23b1b012de55da";
    private String name = "Testuser";
    private String company = "BMW";
    private String factory = "München Werk 1";
    private String key1 = "email";
    private String value1 = "testuser@ascora.de";
    private String key2 = "options";
    private String[] value2 = {"option1", "option2", "option3"};
    private String key3 = "isCustomer";
    private Boolean value3 = true;
    private List preferences;

    public MockSpcResponse() {
        preferences = new ArrayList<>();
        preferences.add(key1);
        preferences.add(value1);
        preferences.add(key2);
        preferences.add(value2);
        preferences.add(key3);
        preferences.add(value3);
    }

    @Override
    public String toString() {
        return "{" +
                "_id:'" + _id + '\'' +
                ", name:'" + name + '\'' +
                ", company:'" + company + '\'' +
                ", factory:'" + factory + '\'' +
                '}';
    }

    private String formatPreferences() {
        String result = "[";

        result += "{" + preferences.get(0) + ":" + preferences.get(1) + "}, ";
        result += "{" + preferences.get(2) + ":" + preferences.get(3) + "}, ";
        result += "{" + preferences.get(4) + ":" + preferences.get(5) + "} ";

        result += "]";

        return result;
    }
}
