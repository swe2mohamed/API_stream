package org.example;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class StreamExample {
    public static void main(String[] args) {
        //ex1();
        //ex2();
        //ex3();
        ex4();

    }
    // Terminal operation
    // count
    public static void ex1(){
        Stream<String> skills= Stream.of("Java", "C#", "ReactJS", "Python");
        long count = skills.count();
        System.out.println("count = " + count);
    }
    
    public static void ex2(){
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
        Optional<Integer> optionalMax= numbers.stream().max(comparator);
        // 1.2 First Method
        if (optionalMax.isPresent()){
            System.out.println("Max number: " + optionalMax.get());
            //System.out.println("Max number: " + optionalMax);
        }else{
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

    public static void ex3(){
        List<String> names = Arrays.asList("Alice", "Bob"," Charles", "David", "Eve");
        Optional<String> optionalName= names.stream().findFirst();
        // Optional<String> optionalName= names.stream().findAny();  // in parallel "more advance"

        if (optionalName.isPresent()){
            System.out.println("Name = " + optionalName.get());
        }else {
            System.out.println("No element found");
        }
    }

    public static void ex4(){
        List<Integer> numbers = Arrays.asList(2,4,6,8,10,12,13);
        Predicate<Integer> even = n -> n%2 == 0;
        boolean isAllEven = numbers.stream().allMatch(even); // It should all numbers in the list of numbers even to be TRUE
        System.out.println("isAllEven = " + isAllEven);
        boolean isAnyEven = numbers.stream().anyMatch(even);
        System.out.println("isAnyEven = " + isAnyEven);
        boolean isNoneEven = numbers.stream().noneMatch(even);
        System.out.println("isNoneEven = " + isNoneEven);
    }
}
