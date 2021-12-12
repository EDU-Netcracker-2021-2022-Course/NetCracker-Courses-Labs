package contract;

import Enums.ContractType;
import channel.Channel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import person.Person;
import person.PersonTest;

import java.util.ArrayList;
import java.util.List;

public class ContractTVTest {
    public static ContractTV contract;
    public static Person person;
    public static List<Channel> channelList = new ArrayList<>();


    @BeforeAll
    public static void setUp() {
        PersonTest.setUp();
        person = PersonTest.person2;

        channelList.add(new Channel("Первый"));
        channelList.add(new Channel("НТВ"));
        channelList.add(new Channel("National Geographic"));
        channelList.add(new Channel("СТС"));

        contract = new ContractTV("2019-05-10",
                "2021-11-08",
                56464842,
                person,
                channelList,
                ContractType.TV);
    }

    @Test
    void getChannelsPackageNotNullTest() {
        Assertions.assertNotNull(contract.getChannelsPackage());
    }
}