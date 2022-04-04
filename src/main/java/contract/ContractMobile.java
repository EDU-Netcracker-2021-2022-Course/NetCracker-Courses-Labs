package contract;

import Enums.ContractType;
import person.Person;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"minutesTotal", "messagesTotal", "trafficTotal"})
@XmlAccessorType(XmlAccessType.FIELD)
public class ContractMobile extends Contract {

    @XmlElement(name = "contract_minutes_count")
    private short minutesTotal;

    @XmlElement(name = "contract_messages_count")
    private short messagesTotal;

    @XmlElement(name = "contract_traffic_total")
    private short trafficTotal;

    /**
     * Constructs a new object.
     * @param startingDate
     * @param endingDate
     * @param number
     * @param owner
     * @param minutesTotal
     * @param messagesTotal
     * @param trafficTotal
     */
    public ContractMobile(String startingDate, String endingDate, int number, Person owner, short minutesTotal, short messagesTotal, short trafficTotal, ContractType type) {
        super(startingDate, endingDate, number, owner, type);
        this.minutesTotal = minutesTotal;
        this.messagesTotal = messagesTotal;
        this.trafficTotal = trafficTotal;
    }

    public ContractMobile() {}

    /**
     * Get total minutes amount for this contract.
     * @return total minutes amount for this contract.
     */
    public short getMinutesTotal() {
        return minutesTotal;
    }

    /**
     * Change total minutes amount for this contract.
     * @param minutesTotal
     */
    public void setMinutesTotal(short minutesTotal) {
        this.minutesTotal = minutesTotal;
    }

    /**
     * Get total messages amount for this contract.
     * @return total messages amount for this contract.
     */
    public short getMessagesTotal() {
        return messagesTotal;
    }

    /**
     * Change total messages amount for this contract.
     * @param messagesTotal
     */
    public void setMessagesTotal(short messagesTotal) {
        this.messagesTotal = messagesTotal;
    }

    /**
     * Get total amount of Gb of traffic for this contract.
     * @return total amount of Gb of traffic for this contract.
     */
    public short getTrafficTotal() {
        return trafficTotal;
    }

    /**
     * Change total amount of Gb of traffic for this contract.
     * @param trafficTotal
     */
    public void setTrafficTotal(short trafficTotal) {
        this.trafficTotal = trafficTotal;
    }

    /**
     * Add package of minutes to this contract.
     * @param minutes
     */
    public void addMinutes(short minutes) {
        minutesTotal += minutes;
    }

    /**
     * Add package of messages to this contract.
     * @param messages
     */
    public void addMessages(short messages) {
        messagesTotal += messages;
    }

    /**
     * Add amount of Gb of traffic to this contract.
     * @param traffic
     */
    public void addTraffic(short traffic) {
        trafficTotal += traffic;
    }

    /**
     * Subtract package of minutes to this contract.
     * @param minutes
     */
    public void subMinutes(short minutes) {
        minutesTotal -= minutes;
    }

    /**
     * Subtract package of messages to this contract.
     * @param messages
     */
    public void subMessages(short messages) {
        messagesTotal -= messages;
    }

    /**
     * Subtract amount of Gb of traffic to this contract.
     * @param traffic
     */
    public void subTraffic(short traffic) {
        trafficTotal -= traffic;
    }

    @Override
    public String toString() {
        return "ContractMobile{" +
                "id=" + id +
                ", startingDate=" + startingDate +
                ", endingDate=" + endingDate +
                ", number=" + number +
                ", owner=" + owner +
                ", type=" + type +
                ", minutesTotal=" + minutesTotal +
                ", messagesTotal=" + messagesTotal +
                ", trafficTotal=" + trafficTotal +
                '}';
    }
}
