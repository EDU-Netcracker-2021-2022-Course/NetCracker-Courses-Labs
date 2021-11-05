package contract;

import person.Person;

public class ContractMobile extends Contract {

    private short minutesTotal;
    private short messagesTotal;
    private short trafficTotal;

    public ContractMobile(String startingDate, String endingDate, int number, Person owner, short minutesTotal, short messagesTotal, short trafficTotal) {
        super(startingDate, endingDate, number, owner);
        this.minutesTotal = minutesTotal;
        this.messagesTotal = messagesTotal;
        this.trafficTotal = trafficTotal;
    }

    public short getMinutesTotal() {
        return minutesTotal;
    }

    public void setMinutesTotal(short minutesTotal) {
        this.minutesTotal = minutesTotal;
    }

    public short getMessagesTotal() {
        return messagesTotal;
    }

    public void setMessagesTotal(short messagesTotal) {
        this.messagesTotal = messagesTotal;
    }

    public short getTrafficTotal() {
        return trafficTotal;
    }

    public void setTrafficTotal(short trafficTotal) {
        this.trafficTotal = trafficTotal;
    }

    public void addMinutes(short minutes) {
        minutesTotal += minutes;
    }

    public void addMessages(short messages) {
        messagesTotal += messages;
    }

    public void addTraffic(short traffic) {
        trafficTotal += traffic;
    }

    public void subMinutes(short minutes) {
        minutesTotal -= minutes;
    }

    public void subMessages(short messages) {
        messagesTotal -= messages;
    }

    public void subTraffic(short traffic) {
        trafficTotal -= traffic;
    }
}
