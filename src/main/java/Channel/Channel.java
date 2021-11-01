package Channel;

import java.util.Objects;

public class Channel {
    private String name;
    private String siteAddress;

    public Channel(String name, String siteAddress) {
        this.name = name;
        this.siteAddress = siteAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSiteAddress() {
        return siteAddress;
    }

    public void setSiteAddress(String siteAddress) {
        this.siteAddress = siteAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Channel)) return false;
        Channel channel = (Channel) o;
        return Objects.equals(getName(), channel.getName()) && Objects.equals(getSiteAddress(), channel.getSiteAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getSiteAddress());
    }

    @Override
    public String toString() {
        return name + " Site: " + siteAddress;
    }
}
