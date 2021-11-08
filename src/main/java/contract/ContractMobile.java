package contract;

import person.Person;
// TODO: JavaDoc
public class ContractMobile extends Contract {

    private short minutesTotal;
    private short messagesTotal;
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
    public ContractMobile(String startingDate, String endingDate, int number, Person owner, short minutesTotal, short messagesTotal, short trafficTotal) {
        super(startingDate, endingDate, number, owner);
        this.minutesTotal = minutesTotal;
        this.messagesTotal = messagesTotal;
        this.trafficTotal = trafficTotal;
    }

    /**
     *
     * @return
     */
    public short getMinutesTotal() {
        return minutesTotal;
    }

    /**
     *
     * @param minutesTotal
     */
    public void setMinutesTotal(short minutesTotal) {
        this.minutesTotal = minutesTotal;
    }

    /**
     *
     * @return
     */
    public short getMessagesTotal() {
        return messagesTotal;
    }

    /**
     *
     * @param messagesTotal
     */
    public void setMessagesTotal(short messagesTotal) {
        this.messagesTotal = messagesTotal;
    }

    /**
     *
     * @return
     */
    public short getTrafficTotal() {
        return trafficTotal;
    }

    /**
     *
     * @param trafficTotal
     */
    public void setTrafficTotal(short trafficTotal) {
        this.trafficTotal = trafficTotal;
    }

    /**
     *
     * @param minutes
     */
    public void addMinutes(short minutes) {
        minutesTotal += minutes;
    }

    /**
     *
     * @param messages
     */
    public void addMessages(short messages) {
        messagesTotal += messages;
    }

    /**
     *
     * @param traffic
     */
    public void addTraffic(short traffic) {
        trafficTotal += traffic;
    }

    /**
     *
     * @param minutes
     */
    public void subMinutes(short minutes) {
        minutesTotal -= minutes;
    }

    /**
     *
     * @param messages
     */
    public void subMessages(short messages) {
        messagesTotal -= messages;
    }

    /**
     *
     * @param traffic
     */
    public void subTraffic(short traffic) {
        trafficTotal -= traffic;
    }
}
