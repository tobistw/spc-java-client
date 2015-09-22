package de.ascora.spcjavaclient.mock;

import de.ascora.spcjavaclient.metadata.Entity;
import de.ascora.spcjavaclient.metadata.MetaData;
import de.ascora.spcjavaclient.metadata.crema.PrivateData;
import de.ascora.spcjavaclient.metadata.crema.PublicData;
import de.ascora.spcjavaclient.metadata.crema.generic.Key;
import de.ascora.spcjavaclient.metadata.crema.generic.Value;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tobi on 22.09.2015.
 */
public class MockMetaData extends MetaData {

    private PublicData publicData;
    private PrivateData privateData;
    private Map<Key, Value> preferences;


    private String[] optionsValues = {"option1", "option2", "option3"};

    public MockMetaData() {
        this.entity = new Entity("55cdab6f5a23b1b012de55da", "Testuser");
        this.publicData = new PublicData("BMW", "München Werk 1");
        this.preferences = new HashMap<>();
        Key<String> key1 = new Key<>("email");
        Value<String> val1 = new Value<>("testuser@ascora.de");
        Key<String> key2 = new Key<>("options");
        Value<String[]> val2 = new Value<>(optionsValues);
        Key<String> key3 = new Key<>("isCustomer");
        Value<Boolean> val3 = new Value<>(true);
        preferences.put(key1, val1);
        preferences.put(key2, val2);
        preferences.put(key3, val3);
        this.privateData = new PrivateData(preferences);
    }
}
