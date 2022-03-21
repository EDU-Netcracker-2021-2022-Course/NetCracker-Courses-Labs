package utils.validator.modelChecker.PersonChecker;

import Enums.Sex;
import person.Person;

import java.time.LocalDate;

public class PersonChecker {

    public boolean checkId(Person person) {
        if (person.getId() != null) return true;
        return false;
    }

    public boolean checkFirstName(Person person) {
        if (person.getFirstName().matches("[а-яА-Яa-zA-Z]+")) return true;
        return false;
    }

    public boolean checkMiddleName(Person person) {
        if (person.getMiddleName().matches("[а-яА-Яa-zA-Z]+")) return true;
        return false;
    }

    public boolean checkLastName(Person person) {
        if (person.getLastName().matches("[а-яА-Яa-zA-Z]+")) return true;
        return false;
    }

    public boolean checkBirthDate(Person person) {
        LocalDate startDate = LocalDate.of(1920, 1, 1);
        LocalDate endDate = LocalDate.now();

        if (person.getBirthDate() != null &&
            person.getBirthDate().isAfter(startDate) &&
            person.getBirthDate().isBefore(endDate) &&
            person.getBirthDate() instanceof LocalDate
        ) return true;
        return false;
    }

    public boolean checkPassportSeries(Person person) {
        if(Integer.toString(person.getPassportSeries()).matches("\\d{6}")){
            return true;
        }
        return false;
    }

    public boolean checkPassportNumber(Person person) {
        if(Integer.toString(person.getPassportNumber()).matches("\\d{4}")){
            return true;
        }
        return false;
    }

    public boolean checkAge(Person person) {
        if (person.getAge() > 18 && person.getAge() <= 110) return true;
        return false;
    }

    public boolean checkSex(Person person) {
        if (person.getSex() != null &&
            person.getSex() instanceof Sex
        ) return true;
        return false;
    }
}
