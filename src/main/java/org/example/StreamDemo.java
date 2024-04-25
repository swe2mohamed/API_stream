package org.example;

import org.example.model.Gender;
import org.example.model.Person;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StreamDemo {
    public static List<Person> createPersonList(){
        return List.of(
                new Person("Alice", "Johnsson", Gender.F, LocalDate.parse("1990-05-15"),true),
                new Person("Bob", "Smith", Gender.M, LocalDate.parse("1988-10-20"), false),
                new Person("Charlie", "Brown", Gender.M, LocalDate.parse("1992-03-25"), true),
                new Person("Diana", "Johnson", Gender.F, LocalDate.parse("1985-07-10"), false),
                new Person("Eva", "Lee", Gender.O, LocalDate.parse("1998-12-03"), true),
                new Person("Frank", "Taylor", Gender.M, LocalDate.parse("1993-09-18"), false),
                new Person("Grace", "Miller", Gender.F, LocalDate.parse("1987-04-29"), true)
        );
    }
    public static void main(String[] args) {
        Predicate<Person> leapYear = p -> p.getBirthDate().isLeapYear();
        Consumer<Person> printPerson = p -> System.out.println(p.getFirstName() + " " + p.getLastName());
        List<Person> people = createPersonList();
        people
                .stream()
                .filter(leapYear)
                //.forEach(System.out::println);
                .forEach(printPerson);


        List<Person> filteredListByLeapYear = people
                .stream()
                .filter(leapYear)
                .collect(Collectors.toList());

        // System.out.println(filteredListByLeapYear);
    }
}
