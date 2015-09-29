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

    public MetaDataCrema(Entity cremaEntity, PublicData publicData) {
        this.entity = cremaEntity;
        this.publicData = publicData;
        this.privateData = null;
    }

    public MetaDataCrema(Entity cremaEntity) {
        this.entity = cremaEntity;
        this.publicData = null;
        this.privateData = null;
    }

    public PublicData getPublicData() {

        return this.publicData;
    }

    public PrivateData getPrivateData() {

        return this.privateData;
    }

    public void createPrivateData(PrivateData data) {

    }

    public void updatePrivateData(Key key, Value value) {

    }

    public void deletePrivateData(Key key, Value value) {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        MetaDataCrema that = (MetaDataCrema) o;

        if (publicData != null ? !publicData.equals(that.publicData) : that.publicData != null) return false;
        return !(privateData != null ? !privateData.equals(that.privateData) : that.privateData != null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (publicData != null ? publicData.hashCode() : 0);
        result = 31 * result + (privateData != null ? privateData.hashCode() : 0);
        return result;
    }
}
