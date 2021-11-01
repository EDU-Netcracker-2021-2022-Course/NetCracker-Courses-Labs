import Contract.ContractMobile;
import Pesron.Person;

public class Main {
    public static void main(String[] args) {
        Person person1 = new Person("Иван",
                "Иванович",
                "Иванов",
                1960,
                10,
                22,
                (short) 2208,
                123456
                );

        Person person2 = new Person("Константинов",
                "Петр",
                "Игоревич",
                1975,
                11,
                01,
                (short) 1975,
                456789
        );

        Person person3 = new Person("Иван",
                "Иванович",
                "Иванов",
                1960,
                10,
                22,
                (short) 2208,
                123456
        );

        Person person4 = person2;


//        System.out.println(person1.toString() + "\n\n");
//        System.out.println(person2.toString() + "\n\n");
//        System.out.println(person3.toString() + "\n\n");
//        System.out.println(person4.toString() + "\n\n");
//
//        System.out.println(person1.equals(person2));
//        System.out.println(person1.hashCode());
//        System.out.println(person2.hashCode());
//        System.out.println("----------");
//        System.out.println(person1.equals(person3));
//        System.out.println(person1.hashCode());
//        System.out.println(person3.hashCode());
//        System.out.println("----------");
//        System.out.println(person2.equals(person4));
//        System.out.println(person2.hashCode());
//        System.out.println(person4.hashCode());

        ContractMobile mobile = new ContractMobile("2020-10-10", "2021-10-10", 1789652, person1, (short) 100, (short) 50, (short) 5);
        ContractMobile mobile2 = new ContractMobile("2019-05-10", "2020-05-10", 1234567, person1, (short) 300, (short) 100, (short) 5000);
        ContractMobile mobile3 = mobile;

//        System.out.println(mobile.toString() + "\n\n");
//        System.out.println(mobile2.toString() + "\n\n");
//
//        System.out.println(mobile.equals(mobile2));
//
//        System.out.println(mobile.hashCode());
//        System.out.println(mobile2.hashCode());
//
//        System.out.println(mobile.equals(mobile3));
//
//        System.out.println(mobile.hashCode());
//        System.out.println(mobile3.hashCode());
    }
}
