package de.ascora.spcjavaclient.metadata.crema;

import de.ascora.spcjavaclient.metadata.crema.generic.Preference;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tobi on 22.09.2015.
 */
public class PrivateData {

    private Preference[] preferences;

    public PrivateData(Preference[] preferences) {
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

        PrivateData that = (PrivateData) o;

        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(preferences, that.preferences);

    }

    @Override
    public int hashCode() {
        return preferences != null ? Arrays.hashCode(preferences) : 0;
    }
}
