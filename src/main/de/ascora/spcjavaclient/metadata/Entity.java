package de.ascora.spcjavaclient.metadata;

/**
 * Created by tobi on 22.09.2015.
 */
public class Entity {
    private String _id;
    private String name;

    public Entity(String _id, String name) {
        this._id = _id;
        this.name = name;
    }

    public String get_id() {
        return _id;
    }

    public String getName() {
        return name;
    }
}
