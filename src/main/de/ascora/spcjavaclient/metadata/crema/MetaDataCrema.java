package de.ascora.spcjavaclient.metadata.crema;

import de.ascora.spcjavaclient.metadata.Entity;
import de.ascora.spcjavaclient.metadata.MetaData;
import de.ascora.spcjavaclient.metadata.crema.generic.Key;
import de.ascora.spcjavaclient.metadata.crema.generic.Value;

/**
 * Created by tobi on 22.09.2015.
 */
public class MetaDataCrema extends MetaData {

    private PublicData publicData;
    private PrivateData privateData;

    public MetaDataCrema(Entity cremaEntity, PublicData publicData, PrivateData privateData) {
        super.entity = cremaEntity;
        this.publicData = publicData;
        this.privateData = privateData;
    }

    public PublicData getPublicData() {
        return null;
    }

    public PrivateData getPrivateData() {
        return null;
    }

    public void createPrivateData(PrivateData data) {

    }

    public void updatePrivateData(Key key, Value value) {

    }

    public void deletePrivateData(Key key, Value value) {

    }
}
