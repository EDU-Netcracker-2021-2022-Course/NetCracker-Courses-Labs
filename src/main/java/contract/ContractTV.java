package contract;

import channel.Channel;
import person.Person;

import java.util.ArrayList;
import java.util.List;
// TODO: JavaDoc
public class ContractTV extends Contract {
    private List<Channel> channelsPackage = new ArrayList<>();

    public ContractTV(String startingDate, String endingDate, int number, Person owner, ArrayList<Channel> channelsPackage) {
        super(startingDate, endingDate, number, owner);
        this.channelsPackage = channelsPackage;
    }

    public List<Channel> getChannelsPackage() {
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
