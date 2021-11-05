package channel;

import java.util.Objects;
import java.util.UUID;

public class Channel {
    private UUID id;
    private String name;
    private String siteAddress;

    public Channel(String name, String siteAddress) {
        this.name = name;
        this.siteAddress = siteAddress;
        id = UUID.randomUUID();
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

    public UUID getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Channel)) return false;
        Channel channel = (Channel) o;
        return getId().equals(channel.getId()) && getName().equals(channel.getName()) && Objects.equals(getSiteAddress(), channel.getSiteAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getSiteAddress());
    }

    @Override
    public String toString() {
        return "Channel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
