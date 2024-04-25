package org.example;

import java.util.*;
import java.util.stream.Stream;

public class StreamExample {
    public static void main(String[] args) {
        //ex1();
        ex2();

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
}
