package Contract;

import Pesron.Person;

import java.util.ArrayList;

public class ContractTV extends Contract {
    private ArrayList<Channel> channelsPackage;

    public ContractTV(String startingDate, String endingDate, int number, Person owner, ArrayList<Channel> channelsPackage) {
        super(startingDate, endingDate, number, owner);
        this.channelsPackage = channelsPackage;
    }

    public ArrayList<Channel> getChannelsPackage() {
        return channelsPackage;
    }

    public void setChannelsPackage(ArrayList<Channel> channelsPackage) {
        this.channelsPackage = channelsPackage;
    }

    public void addChannels (ArrayList<Channel> channelsPackage) {
        this.channelsPackage.addAll(channelsPackage);
    }

    public void addChannels (Channel channel) {
        this.channelsPackage.add(channel);
    }

    public void removeChannels (ArrayList<Channel> channelsPackage) {
        this.channelsPackage.removeAll(channelsPackage);
    }

    public void removeChannels (Channel channel) {
        this.channelsPackage.remove(channel);
    }
}
