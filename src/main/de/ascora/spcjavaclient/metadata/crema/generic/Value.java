package de.ascora.spcjavaclient.metadata.crema.generic;

/**
 * Created by tobi on 22.09.2015.
 */
public class Value<V> {

    private V value;

    public Value(V value) {
        this.value = value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public V getValue() {
        return value;
    }
}
