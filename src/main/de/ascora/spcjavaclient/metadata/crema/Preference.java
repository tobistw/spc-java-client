package de.ascora.spcjavaclient.metadata.crema;

import de.ascora.spcjavaclient.metadata.crema.generic.Key;
import de.ascora.spcjavaclient.metadata.crema.generic.Value;

/**
 * Created by tobi on 23.09.2015.
 */
public class Preference {
    private Key key;
    private Value value;

    public Preference(Key key, Value value) {
        this.key = key;
        this.value = value;
    }
}
