package contract;

import Enums.ContractType;
import channel.Channel;
import person.Person;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
public class ContractTV extends Contract {

    @XmlElement(name = "channel")
    @XmlElementWrapper(name = "channels")
    private List<Channel> channelsPackage = new ArrayList<>();

    /**
     * Constructs a new object.
     * @param startingDate
     * @param endingDate
     * @param number
     * @param owner
     * @param channelsPackage
     */
    public ContractTV(String startingDate, String endingDate, int number, Person owner, List<Channel> channelsPackage, ContractType type) {
        super(startingDate, endingDate, number, owner, type);
        this.channelsPackage = channelsPackage;
    }

    public ContractTV() {

    }

    /**
     * getget channel list available for this contract.
     * @return channel list available for this contract.
     */
    public List<Channel> getChannelsPackage() {
        return channelsPackage;
    }

    /**
     * Set channel list available for this contract.
     * @param channelsPackage
     */
    public void setChannelsPackage(List<Channel> channelsPackage) {
        this.channelsPackage = channelsPackage;
    }

    /**
     * Add list of channel for this contract.
     * @param channelsPackage
     */
    public void addChannel (List<Channel> channelsPackage) {
        this.channelsPackage.addAll(channelsPackage);
    }

    /**
     * Add a channel for this contract.
     * @param channel
     */
    public void addChannel (Channel channel) {
        this.channelsPackage.add(channel);
    }

    /**
     * Remove list of channel for this contract.
     * @param channelsPackage
     */
    public void removeChannel (List<Channel> channelsPackage) {
        this.channelsPackage.removeAll(channelsPackage);
    }

    /**
     * Remove a channel for this contract.
     * @param channel
     */
    public void removeChannel (Channel channel) {
        this.channelsPackage.remove(channel);
    }

    @Override
    public String toString() {
        return "ContractTV{" +
                "id=" + id +
                ", startingDate=" + startingDate +
                ", endingDate=" + endingDate +
                ", number=" + number +
                ", owner=" + owner +
                ", type=" + type +
                ", channelsPackage=" + channelsPackage +
                '}';
    }
}
