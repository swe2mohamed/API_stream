package org.example;

import org.example.model.Gender;
import org.example.model.Person;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class StreamExample {
    public static void main(String[] args) {
        //ex1();
        //ex2();
        //ex3();
        //ex4();
        //ex5();
        //ex6();
        //ex7();
        ex8();

    }

    // Terminal operation
    // count
    public static void ex1() {
        Stream<String> skills = Stream.of("Java", "C#", "ReactJS", "Python");
        long count = skills.count();
        System.out.println("count = " + count);
    }

    public static void ex2() {
        /* 1.1
        First method:
        List<Integer> numbers = new ArrayList<>();
        numbers.add(2);
        numbers.add(100);
        numbers.add(12);
        numbers.add(100000);
        numbers.add(20000);
         */

        // 1.1 Another method:
        List<Integer> numbers = Arrays.asList(2, 100, 12, 100000, 20000);
        System.out.println("numbers = " + numbers);


        System.out.println("____Max____");
        Comparator<Integer> comparator = Integer::compareTo;
        Optional<Integer> optionalMax = numbers.stream().max(comparator);
        // 1.2 First Method
        if (optionalMax.isPresent()) {
            System.out.println("Max number: " + optionalMax.get());
            //System.out.println("Max number: " + optionalMax);
        } else {
            System.out.println("No Max Number found.");
        }
        // 1.2 Second Method
        System.out.println("optionalMax = " + optionalMax);
        System.out.println("Max = " + optionalMax.get());

        System.out.println("____Min____");
        Optional<Integer> optionalMin = numbers.stream().min(comparator);
        System.out.println("optionalMin = " + optionalMin);
        System.out.println("Min = " + optionalMin.get());

    }

    public static void ex3() {
        List<String> names = Arrays.asList("Alice", "Bob", " Charles", "David", "Eve");
        Optional<String> optionalName = names.stream().findFirst();
        // Optional<String> optionalName= names.stream().findAny();  // in parallel "more advance"

        if (optionalName.isPresent()) {
            System.out.println("Name = " + optionalName.get());
        } else {
            System.out.println("No element found");
        }
    }

    public static void ex4() {
        List<Integer> numbers = Arrays.asList(2, 4, 6, 8, 10, 12, 13);
        Predicate<Integer> even = n -> n % 2 == 0;
        boolean isAllEven = numbers.stream().allMatch(even); // It should all numbers in the list of numbers even to be TRUE
        System.out.println("isAllEven = " + isAllEven);
        boolean isAnyEven = numbers.stream().anyMatch(even);
        System.out.println("isAnyEven = " + isAnyEven);
        boolean isNoneEven = numbers.stream().noneMatch(even);
        System.out.println("isNoneEven = " + isNoneEven);
    }

    // Terminal Operation
    // forEach()
    public static void ex5() {
        List<String> names = Arrays.asList("Alice", "Bob", "Charles", "David", "Eve");
        Consumer<String> printName = System.out::println;
        // names.stream().forEach(p -> System.out.println("p = " + p));
        names.stream().forEach(printName);
    }

    public static void ex6() {
        List<String> names = Arrays.asList("Alice", "Bob", "Bob", "Charles", "David", "Eve");
        // toSet
        Set<String> stringsSet = names.stream().collect(Collectors.toSet());
        System.out.println("stringsSet = " + stringsSet);
        System.out.println("stringsSetSize = " + stringsSet.size());

        // toList
        List<String> stringsList = names.stream().collect(Collectors.toList());
        System.out.println("stringsList = " + stringsList);
        System.out.println("stringsListSize = " + stringsList.size());
    }

    // Intermediate Operation
    // filter
    public static void ex7() {
        List<Integer> numbers = Arrays.asList(1, -7, 10, 26, -123, 32, 11, 7, 19, -188);
        // numbers.stream().filter(n -> n%2 == 0).forEach(n -> System.out.println(n));
        System.out.println("_____isEven_____");
        Predicate<Integer> isEven = n -> n % 2 == 0;
        numbers.stream().filter(isEven).forEach(System.out::println);

        System.out.println("____Negation 'Opposite of Even Number = Udd number'_____");
        numbers.stream().filter(isEven.negate()).forEach(System.out::println);

        System.out.println("___ Negation 'Opposite of Even Number 'Udd number' + 'Positive number' ___");
        numbers
                .stream()
                .filter(p -> p > 0)
                .filter(isEven.negate())
                .forEach(System.out::println);

    }

    public static List<Person> createPersonList(){
        return List.of(
                new Person("Alice", "Johnsson", Gender.F, LocalDate.parse("1990-05-15"),true),
                new Person("Bob", "Smith", Gender.M, LocalDate.parse("1988-10-20"), false),
                new Person("Aob", "Smith", Gender.M, LocalDate.parse("1988-10-20"), false),
                new Person("Aob", "amin", Gender.M, LocalDate.parse("1988-10-20"), false),
                new Person("Frank", "Taylor", Gender.M, LocalDate.parse("1993-09-18"), false),
                new Person("Charlie", "Brown", Gender.M, LocalDate.parse("1993-03-25"), true),
                new Person("Diana", "Johnson", Gender.F, LocalDate.parse("1985-07-10"), false),
                new Person("Eva", "Lee", Gender.O, LocalDate.parse("1998-12-03"), true),
                new Person("Grace", "Miller", Gender.F, LocalDate.parse("1987-04-29"), true)
        );
    }

    public static void ex8(){
        List<Person> people = createPersonList();
        System.out.println("__date__");

        List<LocalDate> dates = people
                .stream()
                .map(person -> person.getBirthDate())
                .filter(person -> person.isLeapYear())
                .collect(Collectors.toList());

                dates.forEach(p -> System.out.println(p));

        System.out.println("__dateMethodReference__");
        List<LocalDate> dateMethodReference = people
                .stream()
                .map(Person::getBirthDate)
                .filter(LocalDate::isLeapYear)
                .collect(Collectors.toList());

                dateMethodReference.forEach(System.out::println);

        System.out.println("__If we want to print them direct without put them in the list __");
        people
                .stream()
                .map(Person::getBirthDate)
                .filter(LocalDate::isLeapYear)
                .forEach(System.out::println);

        System.out.println("___ Sort by birthdate and map to name. __");
        // Sort by birthdate and map to name.
        //Comparator<Person> dateComparator = (p1,p2) -> p1.getBirthDate().compareTo(p2.getBirthDate());
        Comparator<Person> dateComparator = Comparator.comparing(Person::getBirthDate);

        people
                .stream()
                .sorted(dateComparator)
                .map(p -> p.getFirstName() + " " +p.getLastName() + " " + p.getBirthDate())
                .forEach(System.out::println);

        System.out.println("___ Sort by FirstName __");

        // Comparator<Person> personComparatorByFirstName = (p1,p2) -> (p1.getFirstName().compareTo(p2.getFirstName());
        //Comparator<Person> personComparatorByLastName = Comparator.comparing(Person::getLastName);
        // Comparator<Person> personComparatorByFirstName = Comparator.comparing(Person::getFirstName);
        Comparator<Person> personComparatorByFirstName = (p1,p2) -> p1.getFirstName().compareToIgnoreCase(p2.getFirstName());
        Comparator<Person> personComparatorByLastName = (p1,p2) -> p1.getLastName().compareToIgnoreCase(p2.getLastName());
        people
                .stream()
                .sorted(dateComparator.thenComparing(personComparatorByFirstName).thenComparing(personComparatorByLastName))
                .map(p -> p.getFirstName() + " " +p.getLastName() + " " + p.getBirthDate())
                .forEach(System.out::println);
    }

}
