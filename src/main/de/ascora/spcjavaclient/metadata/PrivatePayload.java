package de.ascora.spcjavaclient.metadata;

import de.ascora.spcjavaclient.metadata.generic.Preference;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tobi on 22.09.2015.
 */
public class PrivatePayload {

    private Preference[] preferences;

    public PrivatePayload(Preference[] preferences) {
        this.preferences = preferences;
    }


    public boolean addPreference(Preference preference) {
        if (this.preferences != null) {

            for (int i = 0; i < preferences.length; i++) {
                if (preferences[i].getKey().equals(preference.getKey())) {
                    this.preferences[i].setValue(preference.getValue());
                    return true;
                }
            }
            this.preferences = Arrays.copyOf(preferences, preferences.length+1);
            this.preferences[preferences.length - 1] = preference;
            return true;
        } else {
            return false;
        }
    }

    public Preference[] getPreferences() {
        return preferences;
    }

    public Map getPreferenceAsMap() {
        Map map = new HashMap<>();

        if (preferences != null) {
            for (Preference prefs : preferences) {
                map.put(prefs.getKey(), prefs.getValue());
            }
        }
        return map;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PrivatePayload that = (PrivatePayload) o;

        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(preferences, that.preferences);

    }

    @Override
    public int hashCode() {
        return preferences != null ? Arrays.hashCode(preferences) : 0;
    }

    @Override
    public String toString() {
        return "PrivatePayload{" +
                "preferences=" + Arrays.toString(preferences) +
                '}';
    }
}
