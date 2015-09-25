package de.ascora.spcjavaclient.metadata.crema.generic;

/**
 * Created by tobi on 22.09.2015.
 */
public class Value<T> {

    private T value;

    public Value(T value) {
        this.value = value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }
}
