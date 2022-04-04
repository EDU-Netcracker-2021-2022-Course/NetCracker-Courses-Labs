package channel;

import javax.xml.bind.annotation.*;
import java.util.Objects;
import java.util.UUID;

@XmlType(name = "channel")
@XmlAccessorType(XmlAccessType.FIELD)
public class Channel {
    @XmlAttribute
    private UUID id;
    @XmlElement
    private String name;
    @XmlElement
    private String siteAddress;

    /**
     * Constructs a new Channel object.
     */
    public Channel(String name, String siteAddress) {
        this.name = name;
        this.siteAddress = siteAddress;
        id = UUID.randomUUID();
    }

    public Channel(String name) {
        this.name = name;
        this.siteAddress = "";
        id = UUID.randomUUID();
    }

    public Channel() {

    }

    /**
     * Get channel's name.
     * @return String object that represents channel's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Change channel's name.
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get channel's website address.
     * @return String object that represents channel's website address.
     */
    public String getSiteAddress() {
        return siteAddress;
    }

    /**
     * Change channel's website address.
     * @param siteAddress
     */
    public void setSiteAddress(String siteAddress) {
        this.siteAddress = siteAddress;
    }

    /**
     * Get channel's id.
     * @return UUID class object that represents channel's id.
     */
    public UUID getId() {
        return id;
    }

    /**
     * Indicates whether some other channel is "equal to" this one.
     * @param   o   the reference object with which to compare.
     * @return  {@code true} if this channel is the same as the o argument;
     *          {@code false} otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Channel)) return false;
        Channel channel = (Channel) o;
        return getId().equals(channel.getId()) && getName().equals(channel.getName()) && Objects.equals(getSiteAddress(), channel.getSiteAddress());
    }

    /**
     * Returns a hash code value for the channel.
     * @return  a hash code value for this channel.
     */
    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getSiteAddress());
    }

    /**
     * Returns a string representation of the channel.
     * @return a string representation of the channel.
     */
    @Override
    public String toString() {
        return "Channel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}