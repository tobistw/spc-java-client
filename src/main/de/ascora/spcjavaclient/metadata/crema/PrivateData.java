package de.ascora.spcjavaclient.metadata.crema;

import de.ascora.spcjavaclient.metadata.crema.generic.Key;
import de.ascora.spcjavaclient.metadata.crema.generic.Value;

import java.util.Map;

/**
 * Created by tobi on 22.09.2015.
 */
public class PrivateData {

    private Preference[] preferences;

    public PrivateData(Preference[] preferences) {
        this.preferences = preferences;
    }

    public void addPreferences(Key key, Value value) {

    }

    public Map<Key, Value> getPreferences() {
        return null;
    }
}
