package de.ascora.spcjavaclient.mock;

import de.ascora.spcjavaclient.metadata.Entity;
import de.ascora.spcjavaclient.metadata.MetaData;
import de.ascora.spcjavaclient.metadata.MetaDataProject;
import de.ascora.spcjavaclient.metadata.PrivatePayload;
import de.ascora.spcjavaclient.metadata.generic.Preference;
import de.ascora.spcjavaclient.metadata.PublicPayload;

import java.util.*;

/**
 * Created by tobi on 22.09.2015.
 */
public class MockMetaData extends MetaData {

    private PublicPayload publicPayload;
    private PrivatePayload privatePayload;
    private Preference[] preferences;


    private String[] roles = {"user"};
    private String[] optionsValues = {"option1", "option2", "option3"};

    public MockMetaData() {
        this.entity = new Entity("5655760828e5262c1e483bca", "JavaUser", roles);
        this.publicPayload = new PublicPayload("Java-Street 1", "Oracle", "USA, California");
        List<Preference> prefList = new ArrayList<>();
        String key1 = "email";
        String val1 = "javatest@ascora.de";
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

        this.privatePayload = new PrivatePayload(preferences);
    }

    public MetaDataProject getMetaDataCremaInstace() {
        return new MetaDataProject(entity, publicPayload, privatePayload);
    }
}
