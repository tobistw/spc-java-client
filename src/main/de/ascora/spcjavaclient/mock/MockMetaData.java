package de.ascora.spcjavaclient.mock;

import de.ascora.spcjavaclient.metadata.Entity;
import de.ascora.spcjavaclient.metadata.MetaData;
import de.ascora.spcjavaclient.metadata.crema.Preference;
import de.ascora.spcjavaclient.metadata.crema.PrivateData;
import de.ascora.spcjavaclient.metadata.crema.PublicData;
import de.ascora.spcjavaclient.metadata.crema.generic.Key;
import de.ascora.spcjavaclient.metadata.crema.generic.Value;

import java.util.*;

/**
 * Created by tobi on 22.09.2015.
 */
public class MockMetaData extends MetaData {

    private PublicData publicData;
    private PrivateData privateData;
    private Preference[] preferences;


    private String[] optionsValues = {"option1", "option2", "option3"};

    public MockMetaData() {
        this.entity = new Entity("55cdab6f5a23b1b012de55da", "Testuser");
        this.publicData = new PublicData("BMW", "München Werk 1");
        List<Preference> prefList = new ArrayList<>();
        Key<String> key1 = new Key<>("email");
        Value<String> val1 = new Value<>("testuser@ascora.de");
        Key<String> key2 = new Key<>("options");
        Value<String[]> val2 = new Value<>(optionsValues);
        Key<String> key3 = new Key<>("isCustomer");
        Value<Boolean> val3 = new Value<>(true);
        prefList.add(new Preference(key1, val1));
        prefList.add(new Preference(key2, val2));
        prefList.add(new Preference(key3, val3));
        preferences = new Preference[prefList.size()];

        for (int i = 0; i < prefList.size(); i++) {
            preferences[i] = prefList.get(i);
        }

        this.privateData = new PrivateData(preferences);
    }
}
