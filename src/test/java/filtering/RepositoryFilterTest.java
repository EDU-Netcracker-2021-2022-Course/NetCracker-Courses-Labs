package filtering;

import channel.Channel;
import contract.Contract;
import contract.ContractMobile;
import contract.ContractTV;
import contract.ContractWireLine;
import contractRepository.ContractRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import person.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RepositoryFilterTest {
    ContractTV contractTV;
    ContractTV contractTV2;
    ContractMobile contractMobile;
    ContractMobile contractMobile2;
    ContractWireLine contractWireLine;
    Person person1;
    Person person2;
    Person person3;
    Person person4;
    List<Channel> channelList = new ArrayList<>();
    ContractRepository<Contract> localRepository = new ContractRepository<>();

    @BeforeEach
    void setup(){
        channelList.add(new Channel("Первый"));
        channelList.add(new Channel("НТВ"));
        channelList.add(new Channel("National Geographic"));
        channelList.add(new Channel("СТС"));

        person1 = new Person("Иван",
                "Иванович",
                "Иванов",
                1960,
                10,
                22,
                (short) 2208,
                123456
        );

        person2 = new Person("Константинов",
                "Петр",
                "Игоревич",
                1975,
                11,
                01,
                (short) 1975,
                456789
        );

        person3 = new Person("Михайлов",
                "Игорь",
                "Игоревич",
                1935,
                01,
                30,
                (short) 1975,
                456789
        );

        person4 = new Person("Борисов",
                "Михаил",
                "Петрови",
                1950,
                01,
                30,
                (short) 1987,
                123874
        );

        contractMobile = new ContractMobile("2020-10-10",
                "2021-10-10",
                1789652,
                person1,
                (short) 100,
                (short) 50,
                (short) 5
        );

        contractTV = new ContractTV("2019-05-10",
                "2021-11-08",
                56464842,
                person2,
                channelList
        );

        contractWireLine = new ContractWireLine("2019-05-10",
                "2020-05-10",
                1234567,
                person3,
                500
        );

        contractMobile2 = new ContractMobile("2020-10-10",
                "2021-10-10",
                178349652,
                person1,
                (short) 100,
                (short) 50,
                (short) 5
        );

        contractTV2 = new ContractTV("2021-06-11",
                "2021-11-08",
                1231235534,
                person3,
                channelList
        );

        localRepository.add(contractTV);
        localRepository.add(contractMobile);
        localRepository.add(contractWireLine);
        localRepository.add(contractTV2);
        localRepository.add(contractMobile2);
    }

    @Test
    void filter() {
        Predicate<Contract> filterPredicate = c -> c.getNumber() == 1231235534;
        Predicate<Contract> filterPredicate2 = c -> c.getNumber() == 1234567;

        Optional<Contract> filteredRepo;
        filteredRepo = localRepository.searchContract(filterPredicate);
        assertEquals(filteredRepo.get(), contractTV2);

        filteredRepo = localRepository.searchContract(filterPredicate2);
        assertEquals(filteredRepo.get(), contractWireLine);
    }
}
