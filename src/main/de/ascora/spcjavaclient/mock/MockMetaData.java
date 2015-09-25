package de.ascora.spcjavaclient.mock;

import de.ascora.spcjavaclient.metadata.Entity;
import de.ascora.spcjavaclient.metadata.MetaData;
import de.ascora.spcjavaclient.metadata.crema.MetaDataCrema;
import de.ascora.spcjavaclient.metadata.crema.generic.Preference;
import de.ascora.spcjavaclient.metadata.crema.PrivateData;
import de.ascora.spcjavaclient.metadata.crema.PublicData;

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
        this.publicData = new PublicData("BMW", "M�nchen Werk 1");
        List<Preference> prefList = new ArrayList<>();
        String key1 = "email";
        String val1 = "testuser@ascora.de";
        String key2 = "options";
        List val2 = Arrays.asList(optionsValues);
        String key3 = "isCustomer";
        Boolean val3 = true;
        prefList.add(new Preference(key1, val1));
        prefList.add(new Preference(key2, val2));
        prefList.add(new Preference(key3, val3));

        preferences = new Preference[prefList.size()];

        for (int i = 0; i < prefList.size(); i++) {
            preferences[i] = prefList.get(i);
        }

        this.privateData = new PrivateData(preferences);
    }

    public MetaDataCrema getMetaDataCremaInstace() {
        return new MetaDataCrema(entity, publicData, privateData);
    }
}
