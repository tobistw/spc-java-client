package de.ascora.spcjavaclient.metadata.crema;

/**
 * Created by tobi on 22.09.2015.
 */
public class PublicData {
    private String company;
    private String factory;

    public PublicData(String company, String factory) {
        this.company = company;
        this.factory = factory;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PublicData that = (PublicData) o;

        if (company != null ? !company.equals(that.company) : that.company != null) return false;
        return !(factory != null ? !factory.equals(that.factory) : that.factory != null);

    }

    @Override
    public int hashCode() {
        int result = company != null ? company.hashCode() : 0;
        result = 31 * result + (factory != null ? factory.hashCode() : 0);
        return result;
    }
}
