package de.ascora.spcjavaclient.metadata.crema;

import de.ascora.spcjavaclient.metadata.crema.generic.Preference;

import java.util.Arrays;

/**
 * Created by tobi on 22.09.2015.
 */
public class PrivateData {

    private Preference[] preferences;

    public PrivateData(Preference[] preferences) {
        this.preferences = preferences;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PrivateData that = (PrivateData) o;

        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(preferences, that.preferences);

    }

    @Override
    public int hashCode() {
        return preferences != null ? Arrays.hashCode(preferences) : 0;
    }
}
