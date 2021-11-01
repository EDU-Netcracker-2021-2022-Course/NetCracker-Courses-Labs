import Pesron.Person;

public class Main {
    public static void main(String[] args) {
        Person person1 = new Person("Иван",
                "Иванович",
                "Иванов",
                "22",
                "10",
                "1960",
                (short) 2208,
                123456
                );

        Person person2 = new Person("Константинов",
                "Петр",
                "Игоревич",
                "01",
                "11",
                "1975",
                (short) 1975,
                456789
        );

        Person person3 = new Person("Иван",
                "Иванович",
                "Иванов",
                "22",
                "10",
                "1960",
                (short) 2208,
                123456
        );

        Person person4 = person2;


        System.out.println(person1.toString());
        System.out.println(person2.toString());
        System.out.println(person3.toString());
        System.out.println(person4.toString());

        System.out.println(person1.equals(person2));
        System.out.println(person1.hashCode());
        System.out.println(person2.hashCode());
        System.out.println("----------");
        System.out.println(person1.equals(person3));
        System.out.println(person1.hashCode());
        System.out.println(person3.hashCode());
        System.out.println("----------");
        System.out.println(person2.equals(person4));
        System.out.println(person2.hashCode());
        System.out.println(person4.hashCode());

    }
}
